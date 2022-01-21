package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * ElementTypeExtractor
 *
 * @author peter.nosko@gmail.com, Created on 2021-12-01 17:36:56
 *         Major Function：<b>Element type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object ElementTypeExtractor : TypeExtractor<Element>() {

    override fun extract(node: Element, field: Field, defVal: Element?, ksoup: Ksoup): Element? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val value = extractElements(node, pickClazz.value)
        return value.first()
    }
}


