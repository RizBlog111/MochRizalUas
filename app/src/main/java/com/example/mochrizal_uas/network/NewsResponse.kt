package com.example.mochrizal_uas.network

import com.example.mochrizal_uas.model.NewsModel
import com.google.gson.annotations.SerializedName
// Data class objek NewsResponse
data class NewsResponse(
    @SerializedName("totalResults") var totalResults:Int,
    @SerializedName("articles") var articles:MutableList<NewsModel>
)
