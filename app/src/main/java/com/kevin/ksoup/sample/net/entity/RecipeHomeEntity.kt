package com.kevin.ksoup.sample.net.entity

import com.kevin.ksoup.Attrs
import com.kevin.ksoup.annontation.Pick

/**
 * RecipeHomeEntity
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 21:19:55
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

@Pick("body")
class RecipeHomeEntity {
    @Pick("div.wrap div.w.clear div.home_index_slider.mt10 div#home_index_slider ul li")
    var recipeList: List<RecipeEntity>? = null

    @Pick("div.wrap div.w.clear div.recipe_index_n10.mt20.clear.jtl a")
    var lll: List<Entrance>? = null
}

class RecipeEntity {
    @Pick("a", attr = Attrs.TITLE)
    var title: String = ""

    @Pick("a", attr = Attrs.HREF)
    var url: String = ""

    @Pick("a p.line2 span", regex = "\\d+")
    var totalCount: Int = 0
}

class Entrance {
    @Pick("a img", attr = Attrs.SRC)
    var icon: String = ""

    @Pick("a", attr = Attrs.TITLE)
    var title: String = ""

}
