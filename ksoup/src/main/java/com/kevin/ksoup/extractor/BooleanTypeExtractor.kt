package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * BooleanTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 17:40:04
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object BooleanTypeExtractor : TypeExtractor<Boolean>() {

    override fun extract(node: Element, field: Field, defVal: Boolean?, ksoup: Ksoup): Boolean? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val value = IntTypeExtractor.getTargetText(node, pickClazz)
        return try {
            value.toBoolean()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            defVal
        }
    }
}