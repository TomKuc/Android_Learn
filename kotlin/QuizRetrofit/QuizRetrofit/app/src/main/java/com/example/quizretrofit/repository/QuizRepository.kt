package com.example.quizretrofit.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizretrofit.model.QuestionList
import com.example.quizretrofit.retrofit.QuestionsAPI
import com.example.quizretrofit.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {

    var questionsAPI: QuestionsAPI

    init {
        questionsAPI = RetrofitInstance().getRetrofitInstance().create(QuestionsAPI::class.java)
    }

    fun getQuestionsFromAPI(): LiveData<QuestionList>{
        var data = MutableLiveData<QuestionList>()

        var questionsList: QuestionList

        GlobalScope.launch(Dispatchers.IO) {
            val response = questionsAPI.getQuestions()
            if(response != null) {
                questionsList = response.body()!!
                data.postValue(questionsList)
                Log.i("TAGY", "" + data.value)
            }
        }
        return data
        }
}