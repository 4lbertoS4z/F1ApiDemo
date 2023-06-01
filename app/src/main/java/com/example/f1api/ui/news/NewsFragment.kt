package com.example.f1api.ui.news

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.f1api.databinding.FragmentNewsBinding
import com.example.f1api.settings.WEB_NEWS

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding ?: throw IllegalStateException("FragmentNewsBinding is not available.")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
//Refresh
        binding.swipeRefresh.setOnRefreshListener {
            binding.webView.reload()
        }

        //webView

        binding.webView.webChromeClient = object : WebChromeClient() {

        }
        binding.webView.webViewClient = object  : WebViewClient(){

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.swipeRefresh.isRefreshing = false
            }

        }

        val settings = binding.webView.settings
        settings.javaScriptEnabled = true

        binding.webView.settings.domStorageEnabled = true // sin esto no carga el juego ni el mando
        binding.webView.loadUrl(WEB_NEWS)



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}