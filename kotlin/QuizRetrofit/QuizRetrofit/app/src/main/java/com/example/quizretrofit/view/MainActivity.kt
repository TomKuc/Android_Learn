package com.example.quizretrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizretrofit.R
import com.example.quizretrofit.databinding.ActivityMainBinding
import com.example.quizretrofit.model.Question
import com.example.quizretrofit.model.QuestionList
import com.example.quizretrofit.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var quizViewModel: QuizViewModel
    lateinit var questionList: List<Question>

    companion object{
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        result = 0
        totalQuestions = 0

        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {
                if (it.size>0){
                    questionList = it
                    Log.i("TAGY", "This the 1st question: ${questionList[0]}")

                    binding.apply {
                        txtQuestion.text = questionList!![0].question
                        radio1.text = questionList!![0].option1
                        radio2.text = questionList!![0].option2
                        radio3.text = questionList!![0].option3
                        radio4.text = questionList!![0].option4
                    }
                }
            })
        }
        var i = 1
        binding.apply {
            btnNext.setOnClickListener(View.OnClickListener {
                val selectedOption = radioGroup?.checkedRadioButtonId

                if(selectedOption != 1){
                    val radButton = findViewById<View>(selectedOption!!) as RadioButton

                    questionList.let {
                        if(i<it.size){
                            totalQuestions = it.size
                            if(radButton.text.toString().equals(it[i-1].correct_option)){
                                result++
                                txtResult?.text = "Correct Answer: $result"
                            }

                            txtQuestion.text = "Question ${i+1}: " + questionList[i].question
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4

                            if(i == it.size!!.minus(1)){
                                btnNext.text = "FINISH"
                            }

                            radioGroup?.clearCheck()
                            i++

                        }else{
                            if(radButton.text.toString().equals(it[i-1].correct_option)){
                                result++
                                txtResult?.text = "Correct Answer : $result"
                            } else {

                            }

                            val intent = Intent(this@MainActivity, ResultActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                    }
                }else{
                    Toast.makeText(this@MainActivity, "Please select one option", Toast.LENGTH_LONG).show()
                }
            })
        }



    }
}