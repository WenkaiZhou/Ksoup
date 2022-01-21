package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * FloatTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 17:36:56
 *         Major Function：<b>Float type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object FloatTypeExtractor : TypeExtractor<Float>() {

    override fun extract(node: Element, field: Field, defVal: Float?, ksoup: Ksoup): Float? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val value = IntTypeExtractor.getTargetText(node, pickClazz)
        return try {
            value?.toFloat()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            defVal
        }
    }
}