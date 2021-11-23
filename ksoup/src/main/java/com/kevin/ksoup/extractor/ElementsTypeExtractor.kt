package com.kevin.ksoup.extractor

import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.reflect.Field

internal object ElementsTypeExtractor : TypeExtractor<Elements>() {

    override fun extract(node: Element, field: Field, defVal: Elements?, ksoup: Ksoup): Elements? {
        val pickClazz = field.getAnnotation(Pick::class.java) ?: return defVal
        return extractElements(node, pickClazz.value)
    }
}