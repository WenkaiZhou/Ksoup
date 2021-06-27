package com.kevin.net

import com.kevin.net.interceptor.UserAgentInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * HttpBuilder
 *
 * @author zwenkai@foxmail.com, Created on 2020-11-20 10:17:45
 *         Major Function：<b>Http访问构造，模块内可见，不对外暴露</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
internal object HttpBuilder {

    private const val DEFAULT_TIMEOUT = 10L

    /**
     * 构建OkClient
     *
     * @param isReleased        是否发布版本
     * @param connectTimeout    连接超时配置
     * @param readTimeout       读取超时配置
     * @param writeTimeout      写超时配置
     * @param okClientAction    对外暴露的配置方法
     */
    fun createOkClient(
        isReleased: Boolean,
        connectTimeout: Long = DEFAULT_TIMEOUT,
        readTimeout: Long = DEFAULT_TIMEOUT,
        writeTimeout: Long = DEFAULT_TIMEOUT,
        okClientAction: ((builder: OkHttpClient.Builder) -> Unit)? = null
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)

        okClientAction?.invoke(builder)

        if (!isReleased) {
            val loggingInterceptor = HttpLoggingInterceptor { message ->
                println("HttpBuilder: $message")
            }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        builder.addInterceptor(UserAgentInterceptor())
        return builder.build()
    }

    /**
     * 构建Retrofit
     *
     * @param
     */
    fun createRetrofit(baseUrl: String, client: OkHttpClient, retrofitAction: ((builder: Retrofit.Builder) -> Unit)? = null): Retrofit {
       val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)

        retrofitAction?.invoke(builder)

        return builder.build()
    }

}
