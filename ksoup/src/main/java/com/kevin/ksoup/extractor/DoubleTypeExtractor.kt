package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * DoubleTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 17:38:30
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */


internal object DoubleTypeExtractor : TypeExtractor<Double>() {

    override fun extract(node: Element, field: Field, defVal: Double?, ksoup: Ksoup): Double? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val value = IntTypeExtractor.getTargetText(node, pickClazz)
        return try {
            value.toDouble()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            defVal
        }
    }
}