package com.nabil.sipala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nabil.sipala.databinding.ActivityForgotPasswordBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.INVISIBLE

        auth = Firebase.auth

        binding.btnResetPassword.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()

            if (email.isEmpty()) {
                binding.edtEmail.error = resources.getString(R.string.null_email)
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.edtEmail.error = resources.getString(R.string.not_valid_email)
                    binding.edtEmail.requestFocus()
                    binding.edtEmail.background =
                        ContextCompat.getDrawable(this, R.drawable.rounded_text_red)
                } else {
                    binding.progressBar.visibility = View.VISIBLE

                    auth.sendPasswordResetEmail(email).addOnCompleteListener {
                        if (it.isSuccessful) {
                            binding.tvSuccess.visibility = View.VISIBLE
                        }
                    }
                    binding.progressBar.visibility = View.INVISIBLE

                    lifecycleScope.launch {
                        delay(3000L)
                        finish()
                    }
                }
            }
        }

        binding.toolbar.setNavigationOnClickListener { finish() }
    }

}