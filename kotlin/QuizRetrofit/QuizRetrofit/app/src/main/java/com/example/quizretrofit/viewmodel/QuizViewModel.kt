package com.example.quizretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quizretrofit.model.QuestionList
import com.example.quizretrofit.repository.QuizRepository

class QuizViewModel: ViewModel() {
    var repository: QuizRepository = QuizRepository()
    lateinit var questionsLiveData: LiveData<QuestionList>

    init {
        questionsLiveData = repository.getQuestionsFromAPI()
    }

    fun getQuestionsFromLiveData(): LiveData<QuestionList> {
        return questionsLiveData
    }
}