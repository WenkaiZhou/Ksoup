package com.kevin.ksoup

import com.kevin.ksoup.annontation.Pick
import com.kevin.ksoup.extractor.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.nio.charset.Charset

/**
 * Ksoup
 *
 * @author zwenkai@foxmail.com, Created on 2020-11-20 17:37:57
 *         Major Function：<b>Parsing HTML to object</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class Ksoup {

    /**
     * This method deserializes the specified html into an object of the specified class.
     *
     * @param T     the type of the desired object
     * @param html  the string from which the object is to be deserialized
     * @return  an object of type T from the string.
     */
    inline fun <reified T : Any> parse(html: String, charsetName: String = Charsets.UTF_8.name()): T {
        return parse(html, T::class.java, charsetName)
    }

    /**
     * This method deserializes the specified html into an object of the specified class.
     *
     * @param T     the type of the desired object
     * @param html  the string from which the object is to be deserialized
     * @param clazz the class of T
     * @return  an object of type T from the string.
     */
    fun <T : Any> parse(html: String, clazz: Class<T>, charsetName: String = Charsets.UTF_8.name()): T {
        val stream = html.byteInputStream(Charset.forName(charsetName))
        return parse(Jsoup.parse(stream, charsetName, ""), clazz)
    }

    /**
     * This method deserializes the specified html into an object of the specified class.
     *
     * @param T         the type of the desired object
     * @param document  the document from which the object is to be deserialized
     * @param clazz     the class of T
     * @return  an object of type T from the string.
     */
    fun <T : Any> parse(document: Document, clazz: Class<T>): T {
        val rootNode = getRootNode(document, clazz)
        val obj: T
        try {
            obj = clazz.getConstructor().newInstance()
        } catch (e: NoSuchMethodException) {
            throw KsoupException("No-args constructor for class $clazz does not exist.", e)
        } catch (e: Exception) {
            throw KsoupException(e)
        }
        rootNode?.let {
            clazz.validFields().forEach { field ->
                setFieldValue(rootNode, obj, field)
            }
        }
        return obj
    }

    /**
     * Find root element that match the CSS query which declared in the [Pick].
     *
     * @param document  document to parse
     * @param clazz     the target class
     * @return the matching element, or <b>null</b> if none.
     */
    private fun getRootNode(document: Document, clazz: Class<*>): Element? {
        val pickClazz = clazz.getAnnotation(Pick::class.java)
        val cssQuery = pickClazz.value
        return document.selectFirst(cssQuery)
    }

    /**
     * Parsing HTML to assign values to the specified object field.
     *
     * @param node  the element
     * @param obj   the object
     * @param field the target field
     */
    internal fun setFieldValue(node: Element, obj: Any, field: Field) {
        field.isAccessible = true
        val defVal = field[obj]

        field[obj] = getFieldValue(field, node, defVal)
    }

    private fun getFieldValue(
        field: Field,
        node: Element,
        defVal: Any?
    ): Any? =
        when (val genericType = field.genericType) {
            is Class<*> -> {
                when (genericType) {
                    String::class.java -> StringTypeExtractor.extract(node, field, defVal as String?, this)
                    Int::class.java -> IntTypeExtractor.extract(node, field, defVal as Int?, this)
                    Boolean::class.java -> BooleanTypeExtractor.extract(node, field, defVal as Boolean?, this)
                    Byte::class.java -> ByteTypeExtractor.extract(node, field, defVal as Byte?, this)
                    Short::class.java -> ShortTypeExtractor.extract(node, field, defVal as Short?, this)
                    Long::class.java -> LongTypeExtractor.extract(node, field, defVal as Long?, this)
                    Double::class.java -> DoubleTypeExtractor.extract(node, field, defVal as Double?, this)
                    Float::class.java -> FloatTypeExtractor.extract(node, field, defVal as Float?, this)
                    Element::class.java -> ElementTypeExtractor.extract(node, field, defVal as Element?, this)
                    Elements::class.java -> ElementsTypeExtractor.extract(node, field, defVal as Elements?, this)
                    else -> ObjectTypeExtractor.extract(node, field, defVal, this)
                }
            }
            is ParameterizedType -> {
                when (genericType.rawType) {
                    List::class.java -> ArrayTypeExtractor.extract(node, field, null, this)
                    else -> throw KsoupException("Type ${field.type} is not supported.")
                }
            }
            else -> throw KsoupException("Type ${field.type} is not supported.")
        }
}