package com.kevin.ksoup

import org.jsoup.nodes.Element

/**
 * Extensions
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 16:35:01
 *         Major Function：<b>Extensions</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

fun Element.selectLast(cssQuery: String): Element? {
    return this.select(cssQuery).last() ?: null
}