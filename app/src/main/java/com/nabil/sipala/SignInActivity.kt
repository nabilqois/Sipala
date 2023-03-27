package com.nabil.sipala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nabil.sipala.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            login()
        }
        txtRegisterListener()
    }

    private fun login() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPass.text.toString().trim()

        if (email.isEmpty()) {
            binding.editEmail.error = resources.getString(R.string.null_email)
            return
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editEmail.error = resources.getString(R.string.not_valid_email)
                binding.editEmail.requestFocus()
                binding.editEmail.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_text_red)
                return
            }
        }

        if (password.isEmpty()) {
            binding.editPass.error = resources.getString(R.string.null_password)
            binding.editPass.requestFocus()
            binding.editPass.background =
                ContextCompat.getDrawable(this, R.drawable.rounded_text_red)
            return
        }
        if(password.length < 6){
            binding.editPass.error = resources.getString(R.string.should_6_password)
            binding.editPass.requestFocus()
            binding.editPass.background = ContextCompat.getDrawable(this,R.drawable.rounded_text_red)

            return
        } else {
            binding.progressBar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    binding.progressBar.visibility = View.INVISIBLE
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show()
                        val toHome = Intent(this, HomeActivity::class.java)
                        toHome.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(toHome)
                    } else {
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun txtRegisterListener() {
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this, RegistrasiActivity::class.java))
        }
    }
}