package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * ShortTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-06-27 18:42:42
 *         Major Function：<b>Short type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object ShortTypeExtractor : TypeExtractor<Short>() {

    override fun extract(node: Element, field: Field, defVal: Short?, ksoup: Ksoup): Short? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val value = IntTypeExtractor.getTargetText(node, pickClazz)
        return try {
            value.toShort()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            defVal
        }
    }
}