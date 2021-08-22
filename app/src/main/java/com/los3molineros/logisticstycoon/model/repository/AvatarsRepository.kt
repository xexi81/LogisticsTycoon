package com.los3molineros.logisticstycoon.model.repository

import com.los3molineros.logisticstycoon.model.data.Avatars

class AvatarsRepository {

    val avatarsList = listOf<Avatars>(
        Avatars(0, "Avatar1", "gs://logistics-tycoon.appspot.com/avatar_1.png", 0, 0),
        Avatars(1, "Avatar2", "gs://logistics-tycoon.appspot.com/avatar_2.png", 1000, 0 ),
        Avatars(2, "Avatar3", "gs://logistics-tycoon.appspot.com/avatar_3.png", 10000, 0 ),
        Avatars(3, "Avatar4", "gs://logistics-tycoon.appspot.com/avatar_4.png", 100000, 0 ),
        Avatars(4, "Avatar5", "gs://logistics-tycoon.appspot.com/avatar_5.png", 0, 10 ),
        Avatars(5, "Avatar6", "gs://logistics-tycoon.appspot.com/avatar_6.png", 0, 0 ),
        Avatars(6, "Avatar7", "gs://logistics-tycoon.appspot.com/avatar_7.png", 1000, 0 ),
        Avatars(7, "Avatar8", "gs://logistics-tycoon.appspot.com/avatar_8.png", 0, 10 ),
        Avatars(8, "Avatar9", "gs://logistics-tycoon.appspot.com/avatar_9.png", 10000, 0 ),
        Avatars(9, "Avatar10", "gs://logistics-tycoon.appspot.com/avatar_10.png", 100000, 0 )
    )

    fun returnAllAvatars(): List<Avatars> {
        return avatarsList
    }

}