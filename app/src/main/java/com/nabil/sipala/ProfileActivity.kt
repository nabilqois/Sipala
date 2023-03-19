package com.nabil.sipala


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.nabil.sipala.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser

        if (user != null) {
            binding.edtFullName.setText(user.displayName)
            binding.edtEmail.setText(user.email)


            binding.btnSaveProfile.setOnClickListener {
                val nameUpdated = userProfileChangeRequest {
                    displayName = binding.edtFullName.text.toString().trim()
                    Log.d("ProfileActivity", binding.edtFullName.text.toString().trim())
                }

                user.updateProfile(nameUpdated)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Nama Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            Log.d("ProfileActivity", user.displayName.toString())
                        }

                        val view: View? = this.currentFocus
                        if (view != null) {
                            val inputMethodManager =
                                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                            view.clearFocus()
                        }
                    }
            }
        } else {
            binding.btnSaveProfile.isEnabled = false
            binding.edtFullName.isEnabled = false
        }




        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }


}