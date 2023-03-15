package com.nabil.sipala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.nabil.sipala.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


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

        }
    }

    private fun txtRegisterListener() {
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this, RegistrasiActivity::class.java))
        }
    }
}