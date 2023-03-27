package com.nabil.sipala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.nabil.sipala.databinding.ActivityRegistrasiBinding

class RegistrasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrasiBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            register()
            Log.d("clicked", "tertekan")
        }
        txtLoginListener()
    }

    private fun register() {
        val name =  binding.editFullname.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPass.text.toString().trim()
        val confpass =  binding.editConfpass.text.toString().trim()

        if(name.isEmpty()){
            binding.editFullname.error = resources.getString(R.string.null_email)
            return
        }

        if(email.isEmpty()){
            binding.editEmail.error = resources.getString(R.string.null_email)
            return
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editEmail.error = resources.getString(R.string.not_valid_email)
                binding.editEmail.requestFocus()
                binding.editEmail.background = ContextCompat.getDrawable(this,R.drawable.rounded_text_red)
                return
            }
        }

        if(password.isEmpty()){
            binding.editPass.error = resources.getString(R.string.null_password)
            binding.editPass.requestFocus()
            binding.editPass.background = ContextCompat.getDrawable(this,R.drawable.rounded_text_red)
            return
        }
        if(confpass.isEmpty()){
            binding.editConfpass.error = resources.getString(R.string.null_password)
            binding.editConfpass.requestFocus()
            binding.editConfpass.background = ContextCompat.getDrawable(this,R.drawable.rounded_text_red)
            return
        }


        if(password.isNotEmpty() && confpass.isNotEmpty()){
            if(password.length < 6){
                binding.editPass.error = resources.getString(R.string.should_6_password)
                binding.editPass.requestFocus()
                binding.editPass.background = ContextCompat.getDrawable(this,R.drawable.rounded_text_red)

                return
            }
            if(confpass.length < 6){
                binding.editConfpass.requestFocus()
                binding.editConfpass.background = ContextCompat.getDrawable(this,R.drawable.rounded_text_red)
                return
            }
            if(confpass!= password){
                binding.editConfpass.error = resources.getString(R.string.pass_not_same)
                return
            } else {
                binding.progressBar.visibility = View.VISIBLE
                val nameUpdate = userProfileChangeRequest {
                    displayName = name
                }

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        binding.progressBar.visibility = View.INVISIBLE
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                            val user = auth.currentUser
                            user?.updateProfile(nameUpdate)
                                ?.addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Log.d("firebase", "nama berhasil ditambahkan")
                                    }
                                }
                            startActivity(Intent(this, SignInActivity::class.java))
                        } else {
                            Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }
    }

    private fun txtLoginListener() {
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}