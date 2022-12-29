package com.example.mochrizal_uas.database

import androidx.room.*
import com.example.mochrizal_uas.model.NewsModel

@Dao
interface NewsDao {
    // menyisipkan entitas ke dalam basis data
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insertNews(news:NewsModel)

    // mengambil entitas dari database
    @Query("SELECT * FROM news_table")
    fun getAllSavedNews():List<NewsModel>

    // memeriksa hitungan jika 0(tidak ada), 1(ada)
    @Query("SELECT COUNT(title) FROM news_table WHERE  title= :ti")
    fun getCount(ti:String):Int

    // menghapus entitas dari database
    @Delete
    fun delete(news: NewsModel)
}

