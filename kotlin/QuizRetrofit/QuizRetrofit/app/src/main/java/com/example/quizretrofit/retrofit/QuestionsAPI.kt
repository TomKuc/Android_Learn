package com.example.quizretrofit.retrofit

import com.example.quizretrofit.model.QuestionList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsAPI {

    @GET("API.php")
    suspend fun getQuestions(): Response<QuestionList>

}