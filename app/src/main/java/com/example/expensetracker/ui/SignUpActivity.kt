// com.example.expensetracker.ui.SignUpActivity.kt
package com.example.expensetracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.data.MyDatabase
import com.example.expensetracker.data.repository.UserRepository
import com.example.expensetracker.data.viewmodel.AuthViewModel
import com.example.expensetracker.data.viewmodel.AuthViewModelFactory
import com.example.expensetracker.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi database dan repository
        val database = MyDatabase.getInstance(applicationContext)
        val userRepository = UserRepository(database.userDao())
        val factory = AuthViewModelFactory(userRepository)
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.btnSignUp.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not dmatch", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tambahkan firstName dan lastName di model User jika ingin disimpan
            authViewModel.signUp(username, password)

            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
