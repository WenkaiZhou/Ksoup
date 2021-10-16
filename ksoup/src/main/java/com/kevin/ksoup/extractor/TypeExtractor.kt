package com.kevin.ksoup.extractor

import com.kevin.ksoup.Attrs
import com.kevin.ksoup.Ksoup
import com.kevin.ksoup.annontation.Pick
import com.kevin.ksoup.selectLast
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.reflect.Field
import java.util.regex.Pattern

/**
 * TypeExtractor
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-01 22:57:26
 * Major Function：<b>Base type extractor</b>
 * <p/>
 * Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
abstract class TypeExtractor<T> {

    abstract fun extract(node: Element, field: Field, defVal: T?, ksoup: Ksoup): T?

    fun getTargetText(node: Element, pick: Pick): String? {
        var content = extractText(node, pick.attr, pick.value)
        if (pick.regex.isNotEmpty()) {
            content = content?.let{getRegexText(it, pick.regex)}
        }
        return content
    }

    /**
     * Gets the combined text of target element and all its children that match the [Selector] CSS query.
     * Since jsoup does not support some syntax structures of jQuery, such as: First: last,
     * refer to the jQuery selector to expand its functions.
     *
     * @param element       the target element
     * @param attributeKey  the attribute key
     * @return              the target text
     */
    private fun extractText(element: Element, attributeKey: String, cssQuery: String): String? {
        val selected: Element? = when {
            cssQuery.contains(":first") -> {
                val realCssQuery = cssQuery.replace(":first", "")
                element.selectFirst(realCssQuery)
            }
            cssQuery.contains(":last") -> {
                val realCssQuery = cssQuery.replace(":last", "")
                element.selectLast(realCssQuery)
            }
            else -> element.select(cssQuery).first()
        }
        if (attributeKey === Attrs.HTML)
            throw Error() //return selected?.html()
        return when(attributeKey.toLowerCase()) {
            Attrs.TEXT -> selected?.text()
            Attrs.HTML, Attrs.INNER_HTML -> selected?.html()
            else -> selected?.attr(attributeKey)
        }
    }

    /**
     * Get the target regex text.
     *
     * @param content   the character sequence to be matched
     * @param regex     the regex
     * @return          the matched text
     */
    private fun getRegexText(content: String, regex: String): String {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(content)
        while (matcher.find()) {
            return matcher.group(0)
        }
        return ""
    }
}
