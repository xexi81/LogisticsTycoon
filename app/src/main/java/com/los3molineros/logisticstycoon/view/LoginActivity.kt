package com.los3molineros.logisticstycoon.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.checkEmpty
import com.los3molineros.logisticstycoon.common.toast
import com.los3molineros.logisticstycoon.databinding.ActivityLoginBinding
import com.los3molineros.logisticstycoon.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val GOOGLE_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Register with firebase
        binding.btnRegister.setOnClickListener {
            createFirebaseUser(binding)
        }


        // Login with firebase
        binding.btnLogin.setOnClickListener {
            loginFirebaseUser(binding)
        }


        // Send password by email
        binding.txtRememberPassword.setOnClickListener {
            rememberFirebasePassword(binding)
        }


        // Login with Google
        binding.btnGoogle.setOnClickListener {
            loginGoogleUser(binding)
        }

    }

    override fun onStart() {
        super.onStart()
        getFirebaseUser()
    }



    // check if there is a active user logged
    private fun getFirebaseUser() {
        loginViewModel.userExists.observe(this, { alreadyExistsUser ->
            if (alreadyExistsUser) {
                startActivity(Intent(this, MenuActivity::class.java))
            }
        })
    }


    // register a new firebaseUser
    private fun createFirebaseUser(binding: ActivityLoginBinding) {
        if (binding.txtUsername.checkEmpty()) {
            this.toast("Email is empty")
        }

        else if (binding.txtPassword.checkEmpty()) {
            this.toast("Password is empty")
        }

        else {
            loginViewModel.firebaseRegisterUser(binding.txtUsername.text.toString(), binding.txtPassword.text.toString())
        }
    }



    // login firebase user
    private fun loginFirebaseUser(binding: ActivityLoginBinding) {
        if (binding.txtUsername.checkEmpty()) {
            this.toast("Email is empty")
        }

        else if (binding.txtPassword.checkEmpty()) {
            this.toast("Password is empty")
        }

        else {
            loginViewModel.firebaseLoginUser(binding.txtUsername.text.toString(), binding.txtPassword.text.toString())
        }

    }



    // obtain an email with new Password
    private fun rememberFirebasePassword(binding: ActivityLoginBinding) {
        if (binding.txtUsername.checkEmpty()) {
            this.toast("Email is empty")
        } else {
            loginViewModel.rememberFirebasePassword(binding.txtUsername.text.toString())
        }


        loginViewModel.emailSended.observe(this) { emailSended ->
            if (emailSended) {
                this.toast("Email sended")
            } else {
                this.toast("Someting was wrong")
            }
        }
    }


    private fun loginGoogleUser(binding: ActivityLoginBinding) {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)
        ActivityCompat.startActivityForResult(this, googleClient.signInIntent, GOOGLE_SIGN_IN, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result!!.isSuccess) {
                val account = result.signInAccount
                loginViewModel.signInWithGoogle(account!!)
            } else {
                this.toast("Google login -- Something went wrong!")
            }
        }

        loginViewModel.signedWithGoogle.observe(this) {signedWithGoogle ->
            if (signedWithGoogle) {
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }
    }



}




