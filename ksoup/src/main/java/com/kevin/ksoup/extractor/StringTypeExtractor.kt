package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * StringTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 17:14:15
 *         Major Function：<b>String type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object StringTypeExtractor : TypeExtractor<String>() {

    override fun extract(node: Element, field: Field, defVal: String?, ksoup: Ksoup): String? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        return getTargetText(node, pickClazz)
    }
}