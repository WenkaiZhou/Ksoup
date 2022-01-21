package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * ByteTypeExtractor
 *
 * @author zhouwenkai@baidu.com, Created on 2021-06-27 18:49:39
 *         Major Function：<b>Byte type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object ByteTypeExtractor : TypeExtractor<Byte>() {

    override fun extract(node: Element, field: Field, defVal: Byte?, ksoup: Ksoup): Byte? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val value = IntTypeExtractor.getTargetText(node, pickClazz)
        return try {
            value?.toByte()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            defVal
        }
    }
}