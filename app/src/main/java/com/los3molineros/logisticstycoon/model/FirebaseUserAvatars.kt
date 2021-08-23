import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.buyUserAvatar
import com.los3molineros.logisticstycoon.model.data.UserAvatars
import com.los3molineros.logisticstycoon.model.repository.AvatarsRepository
import com.los3molineros.logisticstycoon.model.returnFirebaseUser
import com.los3molineros.logisticstycoon.model.selectFirebaseUser
import com.los3molineros.logisticstycoon.model.updateAvatarUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

@ExperimentalCoroutinesApi
suspend fun selectFirebaseUserAvatars(): Flow<List<UserAvatars>?> = callbackFlow {

    val uuid = returnFirebaseUser()?.uid

    uuid?.let {
        val resultData = FirebaseFirestore
            .getInstance()
            .collection("userAvatars")
            .whereEqualTo("userID", it)

        val subscription = resultData.addSnapshotListener { querySnapshot, _ ->
            val userAvatarList: MutableList<UserAvatars> = mutableListOf()
            querySnapshot?.let { query ->
                if (query.size() != 0) {
                    for (document in query) {
                        userAvatarList.add(document.toObject(UserAvatars::class.java))
                    }
                    offer(userAvatarList)
                } else offer(null)
            }
        }
        awaitClose { subscription.remove() }
    }
}


suspend fun changeFirebaseUserAvatar(id: Int) {

    val uuid = returnFirebaseUser()?.uid
    val user = selectFirebaseUser()
    val avatar = AvatarsRepository().selectAvatar(id)

    val resultAvatar = FirebaseFirestore
        .getInstance()
        .collection("userAvatars")
        .whereEqualTo("userID", uuid)
        .whereEqualTo("avatarID", avatar.id)
        .get()
        .await()


    if (resultAvatar.isEmpty) {
        // Check money and gems before to buy this avatar
        if (user?.money ?: 0 >= avatar.money && user?.gems ?: 0 >= avatar.gems) {
            // insert Avatar
            val userAvatars = UserAvatars(uuid!!, avatar.id)

            FirebaseFirestore
                .getInstance()
                .collection("userAvatars")
                .document()
                .set(userAvatars)
                .addOnSuccessListener {
                    buyUserAvatar(
                        uuid,
                        avatar.id,
                        user!!.money - avatar.money,
                        user.gems - avatar.gems
                    )
                }
        }
    } else {
            // if user has this avatar, we update user field
            updateAvatarUser(uuid!!, avatar.id)
        }
    }

