package com.kevin.ksoup.sample.net

import com.kevin.ksoup.annontation.Pick
import com.kevin.ksoup.Ksoup
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * HtmlConverterFactory
 *
 * @author zwenkai@foxmail.com, Created on 2021-02-21 16:25:27
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

class HtmlConverterFactory private constructor(private val ksoup: Ksoup) : Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        return if (type is Class<*> && type.getAnnotation(Pick::class.java) != null) {
            Converter<ResponseBody, Any> { value ->
                val result = ksoup.parse(value.string(), type)
                value.close()
                result
            }
        } else super.responseBodyConverter(type, annotations, retrofit)
    }

    companion object {
        fun create(): HtmlConverterFactory {
            return HtmlConverterFactory(Ksoup())
        }
    }

}