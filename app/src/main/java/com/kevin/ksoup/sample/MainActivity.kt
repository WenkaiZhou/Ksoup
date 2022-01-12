package com.kevin.ksoup.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kevin.ksoup.sample.net.HtmlConverterFactory
import com.kevin.ksoup.sample.net.apiService
import com.kevin.net.HttpHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化HttpHelper
        HttpHelper.init("https://m.meishichina.com", isReleased = false) {
            it.addConverterFactory(HtmlConverterFactory.create())
        }

        MainScope().launch(Dispatchers.Main) {
            val homeInfo = apiService.home()
            print(homeInfo.searchPlaceholder)
        }
    }
}