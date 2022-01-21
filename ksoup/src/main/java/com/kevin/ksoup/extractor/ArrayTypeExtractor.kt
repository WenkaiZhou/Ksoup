package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import com.kevin.ksoup.validFields
import org.jsoup.nodes.Element
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType

/**
 * ArrayTypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 17:49:48
 *         Major Function：<b>Array type extractor</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

internal object ArrayTypeExtractor : TypeExtractor<ArrayList<*>>() {

    override fun extract(node: Element, field: Field, defVal: ArrayList<*>?, ksoup: Ksoup): ArrayList<*>? {
        val fieldPick = field.getAnnotation(Pick::class.java) ?: return defVal
        val cssQuery = fieldPick.value
        val elements = extractElements(node, cssQuery)
        val list = ArrayList<Any>()
        for (childNode in elements) {
            val genericType = field.genericType
            if (genericType is ParameterizedType) {
                val args = genericType.actualTypeArguments
                val clazz = Class.forName((args[0] as Class<*>).name)
                val instance = clazz.newInstance()
                clazz.validFields().forEach { childField ->
                    ksoup.setFieldValue(childNode, instance, childField)
                }
                list.add(instance)
            }
        }
        return list
    }
}