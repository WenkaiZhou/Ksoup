package com.kevin.ksoup.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevin.delegationadapter.DelegationAdapter
import com.kevin.ksoup.sample.net.HtmlConverterFactory
import com.kevin.ksoup.sample.net.apiService
import com.kevin.net.HttpHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private val delegationAdapter = DelegationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化HttpHelper
        HttpHelper.init("https://m.meishichina.com", isReleased = false) {
            it.addConverterFactory(HtmlConverterFactory.create())
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        delegationAdapter.addDelegate(HomeChannelDelegate())
        recyclerView.adapter = delegationAdapter
    }

    fun loadData(view: View) {
        MainScope().launch(Dispatchers.Main) {
            val homeInfo = apiService.home()
            homeInfo.channelList?.let {
                Collections.shuffle(it)
                delegationAdapter.setDataItems(it)
            }
        }
    }
}