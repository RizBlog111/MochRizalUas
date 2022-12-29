package com.example.mochrizal_uas.ui.destinations

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.mochrizal_uas.R
import com.example.mochrizal_uas.database.NewsDatabase
import com.example.mochrizal_uas.model.NewsModel
import com.example.mochrizal_uas.ui.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment() : Fragment() {

    lateinit var nModel:NewsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_item_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var dbNews: NewsDatabase = NewsDatabase.getSavedItems(requireActivity().applicationContext)
        var fmodel:NewsViewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        // mengamati perubahan di NewsViewModel
        fmodel.getSelectedNewsModel().observe(requireActivity(),
            Observer<NewsModel> { it ->
                nModel = it })

        super.onViewCreated(view, savedInstanceState)
        addData()

        readFull.setOnClickListener{
            findNavController().navigate(R.id.action_itemDetailsFragment_to_webFragment3) }

        // memeriksa apakah ada entitas; jika ada itu mengubah warna ikon menjadi ungu
        if (dbNews.getNewsDao().getCount(nModel.title )==1 )
            bookmark_icon.setColorFilter(Color.parseColor("#6200EE"))

        // memeriksa apakah ada entitas; jika ada entitas akan disimpan, ikon berubah menjadi ungu dan bersulang akan ditampilkan
        bookmark_icon.setOnClickListener {
            if (dbNews.getNewsDao().getCount(nModel.title ) ==0 ) {
                dbNews.getNewsDao().insertNews(nModel)
                bookmark_icon.setColorFilter(Color.parseColor("#6200EE"))
                Toast.makeText(context, "News Saved", Toast.LENGTH_SHORT).show() }

            // memeriksa apakah ada entitas; jika tidak ada entitas akan dihapus, ikon berubah menjadi abu-abu dan bersulang akan ditampilkan
            else {
                dbNews.getNewsDao().delete(nModel)
                bookmark_icon.setColorFilter(Color.parseColor("#484a49"))
                Toast.makeText(context, "News Unsaved", Toast.LENGTH_SHORT).show() }
        }
    }

    // menambahkan data ke ItemViewDetailsFragment
    fun addData(){
        date_time.text = nModel.publishedAt
        des.text = nModel.desciption
        website2.text = nModel.source.name
        var image = nModel.urlToImage
        Glide.with(requireActivity())
            .load(image)
            .transform(CenterCrop())
            .into(imageView3)}
}
