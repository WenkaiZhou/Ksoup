package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.KsoupException
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import java.lang.reflect.Field

/**
 * ObjectTypeExtractor
 *
 * @author zhouwenkai@baidu.com, Created on 2021-06-27 20:22:01
 *         Major Function：<b>Object type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object ObjectTypeExtractor : TypeExtractor<Any>() {

    override fun extract(rootNode: Element, field: Field, defVal: Any?, ksoup: Ksoup): Any? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        val cssQuery = pickClazz.value
        val node = rootNode.selectFirst(cssQuery)

        val obj: Any
        try {
            obj = field.type.getConstructor().newInstance()
        } catch (e: NoSuchMethodException) {
            throw KsoupException("No-args constructor for class ${field.type} does not exist.", e)
        } catch (e: Exception) {
            throw KsoupException(e)
        }
        node.let {
            field.type.declaredFields.forEach { field ->
                ksoup.getFieldValue(node, obj, field)
            }
        }
        return obj
    }
}