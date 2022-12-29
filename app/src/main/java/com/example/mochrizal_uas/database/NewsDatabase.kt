package com.example.mochrizal_uas.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import com.example.mochrizal_uas.model.NewsModel

// mendefinisikan entitas basis data
@Database(entities = [NewsModel::class],version = 1,exportSchema = false)
abstract class NewsDatabase :RoomDatabase() {
    // memeriksa apakah basis data dibuat, jika tidak, basis data akan dibuat
    companion object
    {
        private var newsDatabase:NewsDatabase?=null

        fun getSavedItems(context: Context) :NewsDatabase
        {
            if (newsDatabase==null)
                newsDatabase=Room.databaseBuilder(context,NewsDatabase::class.java,"NewsDB").allowMainThreadQueries().build()
            return newsDatabase!!
        }
    }
// allows access to NewsDao
    abstract fun getNewsDao():NewsDao
}
