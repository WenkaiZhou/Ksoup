package com.kevin.ksoup.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kevin.delegationadapter.extras.span.SpanAdapterDelegate
import com.kevin.ksoup.sample.net.entity.ChannelItem

/**
 * HomeChannelDelegate
 *
 * @author zwenkai@foxmail.com, Created on 2022-06-09 09:04:35
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

class HomeChannelDelegate : SpanAdapterDelegate<ChannelItem, HomeChannelDelegate.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_channel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: ChannelItem) {
        super.onBindViewHolder(holder, position, item)
        Glide.with(holder.itemView.context).load(item.url).into(holder.ivImg)
        holder.tvTitle.text = item.title
    }

    override fun onItemClick(view: View, item: ChannelItem, position: Int) {
        Toast.makeText(view.context, item.title, Toast.LENGTH_SHORT).show()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView
        var ivImg: ImageView

        init {
            tvTitle = view.findViewById(R.id.tv_title)
            ivImg = view.findViewById(R.id.iv_pic)
        }
    }
}