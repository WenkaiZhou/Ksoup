package com.kevin.ksoup.sample.net.entity

import com.kevin.ksoup.Attrs
import com.kevin.ksoup.annontation.Pick

/**
 * HomeEntity
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 21:19:55
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

@Pick("body")
class HomeEntity {

    @Pick("div.wap_searchbox form#wap_a_searchform div input#wap_a_search", attr = "placeholder")
    var searchPlaceholder: String = ""

    @Pick("div.box div.channel div.channellist")
    var channelList: List<ChannelItem>? = null
}

class ChannelItem {
    @Pick("a div.chamsg")
    var title: String = ""

    @Pick("a img", attr = Attrs.SRC)
    var url: String = ""
}