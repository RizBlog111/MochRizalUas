package com.example.mochrizal_uas.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mochrizal_uas.model.NewsModel
import com.example.mochrizal_uas.network.NewsClient
import com.example.mochrizal_uas.network.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    var mutableNewsList : MutableLiveData<ArrayList<NewsModel>> = MutableLiveData()
    private val selectedNewsModel:MutableLiveData<NewsModel> = MutableLiveData<NewsModel>()
    private var isSuccess:MutableLiveData<Boolean> = MutableLiveData()


    fun fetchHeadlines(page:Int=1)
    {
        NewsClient.service.getAllHeadlines(pageNumber = page).enqueue(object : Callback<NewsResponse>
        {
            // jika panggilan balik tidak berhasil, fungsi urutan yang lebih tinggi akan dipanggil
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                isSuccess.value=false }

            // memasukkan data ke data langsung saat responsnya bukan nol
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>) {
                if (response.isSuccessful)
                {
                    if (response.body()!=null)
                    {
                        isSuccess.value=true
                        mutableNewsList.value = response?.body()!!.articles as ArrayList<NewsModel>
                    }
                    else
                    {
                        isSuccess.value=false
                    }
                }
            }
        })
    }

    fun getSelectedNewsModel() :LiveData<NewsModel>
    {
        return selectedNewsModel
    }

    fun onNewsModelSelected(newsModel: NewsModel)
    {
        selectedNewsModel.value=newsModel
    }

    fun getOnSuccess():LiveData<Boolean>
    {
        return isSuccess
    }
}