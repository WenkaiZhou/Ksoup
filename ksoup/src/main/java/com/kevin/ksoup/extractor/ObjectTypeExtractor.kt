package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.KsoupException
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * ObjectTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-06-27 20:22:01
 *         Major Function：<b>Object type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object ObjectTypeExtractor : TypeExtractor<Any>() {

    override fun extract(node: Element, field: Field, defVal: Any?, ksoup: Ksoup): Any? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val cssQuery = pickClazz.value
        val firstNode = node.selectFirst(cssQuery)

        val obj: Any
        try {
            obj = field.type.getConstructor().newInstance()
        } catch (e: NoSuchMethodException) {
            throw KsoupException("No-args constructor for class ${field.type} does not exist.", e)
        } catch (e: Exception) {
            throw KsoupException(e)
        }
        firstNode?.let {
            field.type.declaredFields.forEach { field ->
                ksoup.setFieldValue(firstNode, obj, field)
            }
        }
        return obj
    }
}