package com.kevin.ksoup

import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * Extensions
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 16:35:01
 *         Major Function：<b>Extensions</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal fun Element.selectLast(cssQuery: String): Element? {
    return this.select(cssQuery).last()
}

internal fun <T> Class<T>.validFields() : List<Field> {
    return this.declaredFields.filterNot { it.name.contains("\$stable") }
}