package com.example.mochrizal_uas.model

import com.google.gson.annotations.SerializedName

// objek dalam NewsModel
data class SourceModel (
   @SerializedName("id") var id:String?,
   @SerializedName("name") var name:String?
)