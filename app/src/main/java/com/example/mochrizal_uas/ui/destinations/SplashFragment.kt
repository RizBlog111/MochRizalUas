package com.example.mochrizal_uas.ui.destinations

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mochrizal_uas.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // menunda navigasi ke HeadlinesFragment satu detik
        var handler: Handler = Handler()
        var runnable = Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_headlinesFragment) }
        handler.postDelayed(runnable,1000)
    }
}