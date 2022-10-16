package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.User
import com.example.myapplication.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //read and save com.example.myapplication.data
    private lateinit var database: DatabaseReference

    lateinit var bindingClass: ActivityMainBinding
    //use library for registered fields
  private val signInLauncher = registerForActivityResult(//созан объект авторизации экрана
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)//запуск самого экрана
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

      database = Firebase.database.reference//инициализация базы данных

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()//список регистраций - какие пути используем(мейл в данном случае)
        )

// Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()//создали интент для приёма данных от пользователя (е-мейл)
        signInLauncher.launch(signInIntent)//запусутили экран

    }
    //result getting and putting (saving) to database
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse//результат с экрана Firebase auth
        //Log.d("step","${response?.email}")
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val authUser = FirebaseAuth.getInstance().currentUser// создаем объект текущего пользователя
            //to extract info about user
            val user = User(authUser?.email.toString(), authUser?.uid.toString())
            //to transfer and save users info to child 'users' field in database
            database.child("users").setValue(user)


           val intent = Intent(this, MovieActivity::class.java)
           startActivity(intent)

        } else {
          Toast.makeText(this,"Что-то не так",Toast.LENGTH_SHORT)
        }
    }}


