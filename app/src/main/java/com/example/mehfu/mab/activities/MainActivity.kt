package com.example.mehfu.mab.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mehfu.mab.R
import com.example.mehfu.mab.config.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button_login.setOnClickListener{

            val username = editTextName.text.toString().trim()
            val password = editTextPass.text.toString().trim()

            if(username.isEmpty()){
                editTextName.error = "Username is required"
                editTextName.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                editTextPass.error = "Password is required"
                editTextPass.requestFocus()
                return@setOnClickListener
            }

            if(username.equals(Constants.USER_NAME) && (password.equals(Constants.PASSWORD))){


                SessionManager(this).createLoginSession(username)
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }else{
                Toast.makeText(this, "Password is incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (SessionManager(this).isLoggedIn()){
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
