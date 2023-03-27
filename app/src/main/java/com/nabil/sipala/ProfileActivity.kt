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
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.storage.ktx.storage

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val user = Firebase.auth.currentUser
    private val storageRef = Firebase.storage.reference;
    private var imgPickerActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result != null) {
                val imageUri: Uri? = result.data?.data

                val email = user?.email

                binding.progressBar.visibility = View.VISIBLE
                val uploadTask = storageRef.child("$email/photo").putFile(imageUri!!)
                uploadTask.addOnSuccessListener {
                    binding.progressBar.visibility = View.INVISIBLE
                    storageRef.child("$email/photo").downloadUrl.addOnSuccessListener {
                        Glide.with(this)
                            .load(it)
                            .circleCrop()
                            .into(binding.imgProfile)

                        Log.e("Firebase", "download passed")
                    }.addOnFailureListener {
                        Log.e("Firebase", "Failed in downloading")
                    }
                }.addOnFailureListener {
                    binding.progressBar.visibility = View.INVISIBLE
                    Log.e("Firebase", "Image Upload fail")
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val view: View? = this.currentFocus
        view?.clearFocus()


        if (user != null) {
            Log.d("Firebase", user.email.toString())
            val email = user.email
            binding.edtFullName.setText(user.displayName)
            binding.edtEmail.setText(user.email)

            storageRef.child("$email/photo").downloadUrl.addOnSuccessListener {
                Glide.with(this)
                    .load(it)
                    .circleCrop()
                    .into(binding.imgProfile)

                Log.e("Firebase", "download passed")
            }.addOnFailureListener {
                Log.e("Firebase", "Failed in downloading")
            }


            binding.btnSaveProfile.setOnClickListener {
                val nameUpdated = userProfileChangeRequest {
                    displayName = binding.edtFullName.text.toString().trim()
                    Log.d("ProfileActivity", binding.edtFullName.text.toString().trim())
                }
                binding.progressBar.visibility = View.VISIBLE
                user.updateProfile(nameUpdated)
                    .addOnCompleteListener {
                        binding.progressBar.visibility = View.INVISIBLE
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
            binding.imgProfile.isEnabled = false
        }

        binding.imgProfile.setOnClickListener {
            val imgPickerIntent = Intent(Intent.ACTION_PICK)
            imgPickerIntent.type = "image/*"
            imgPickerActivityResult.launch(imgPickerIntent)
        }


        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }


}