package com.example.uts

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uts.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.register.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.register.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val nama = binding.namapengguna.text.toString()
            val nim = binding.NIM.text.toString()
            val github = binding.GitHub.text.toString()

            //Validasi email
            if (email.isEmpty()) {
                binding.editTextTextEmailAddress.error = "Email Harus Diisi"
                binding.editTextTextEmailAddress.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()) {
                binding.editTextTextPassword.error = "Password Harus Diisi"
                binding.editTextTextPassword.requestFocus()
                return@setOnClickListener
            }

            //Validasi nama pengguna
            if (nama.isEmpty()) {
                binding.namapengguna.error = "Nama Harus Diisi"
                binding.namapengguna.requestFocus()
                return@setOnClickListener
            }

            //Validasi nama pengguna
            if (nim.isEmpty()) {
                binding.NIM.error = "NIM HARUS DIISI"
                binding.NIM.requestFocus()
                return@setOnClickListener
            }

            //Validasi nama pengguna
            if (github.isEmpty()) {
                binding.GitHub.error = "Nama GitHub Harus Diisi"
                binding.GitHub.requestFocus()
                return@setOnClickListener
            }



            RegisterFirebase(email, password, nama, nim, github)
        }
    }

    private fun RegisterFirebase(email: String, password: String, nama: String, nim: String, github: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}