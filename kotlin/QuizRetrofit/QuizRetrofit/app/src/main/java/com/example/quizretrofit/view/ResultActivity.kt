package com.example.quizretrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizretrofit.R
import com.example.quizretrofit.databinding.ActivityMainBinding
import com.example.quizretrofit.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        binding.txtAnswer.text = "Your score is: " + MainActivity.result + "/" + MainActivity.totalQuestions

        binding.btnBack.setOnClickListener(){
            var intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}