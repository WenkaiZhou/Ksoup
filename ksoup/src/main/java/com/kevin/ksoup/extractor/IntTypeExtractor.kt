package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * IntTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 17:25:06
 *         Major Function：<b>Int type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object IntTypeExtractor : TypeExtractor<Int>() {

    override fun extract(node: Element, field: Field, defVal: Int?, ksoup: Ksoup): Int? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val value = getTargetText(node, pickClazz)
        return try {
            value?.toInt()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            defVal
        }
    }
}