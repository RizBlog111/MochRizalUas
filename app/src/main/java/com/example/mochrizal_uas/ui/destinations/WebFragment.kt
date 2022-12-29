package com.example.mochrizal_uas.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mochrizal_uas.R
import com.example.mochrizal_uas.model.NewsModel
import com.example.mochrizal_uas.ui.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_web, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lateinit var nModel:NewsModel

        var fmodel:NewsViewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        // mengamati perubahan di NewsViewModel
        fmodel.getSelectedNewsModel().observe(requireActivity(),
            Observer<NewsModel> { t -> nModel=t!! })

        super.onViewCreated(view, savedInstanceState)
        // mengambil tautan halaman web
        wb.settings.javaScriptEnabled = true
        wb.webViewClient = WebViewClient()
        wb.loadUrl(nModel.url.toString())
    }
}