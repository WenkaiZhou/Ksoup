package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.reflect.Field

/**
 * ElementsTypeExtractor
 *
 * @author peter.nosko@gmail.com, Created on 2021-12-02 17:36:56
 *         Major Function：<b>Elements type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object ElementsTypeExtractor : TypeExtractor<Elements>() {

    override fun extract(node: Element, field: Field, defVal: Elements?, ksoup: Ksoup): Elements? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        return extractElements(node, pickClazz.value)
    }
}