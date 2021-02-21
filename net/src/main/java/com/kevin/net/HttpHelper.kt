package com.kevin.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * HttpHelper
 *
 * @author zwenkai@foxmail.com, Created on 2020-11-20 10:17:08
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class HttpHelper(baseUrl: String, isReleased: Boolean,
                 okClientAction: ((builder: OkHttpClient.Builder) -> Unit)?,
                 retrofitAction: ((builder: Retrofit.Builder) -> Unit)?) {

    private val retrofit: Retrofit

    init {
        val httpClient = HttpBuilder.createOkClient(isReleased) { builder ->
            // 添加网络层配置，比如dns解析配置
            okClientAction?.invoke(builder)
        }

        retrofit = HttpBuilder.createRetrofit(baseUrl, httpClient) { builder ->
            retrofitAction?.invoke(builder)
        }
    }

    companion object {
        private var instance: HttpHelper? = null

        /**
         * 初始化HttpHelper
         *
         * @param baseUrl           服务base url
         * @param isReleased        是否Release
         * @param okClientAction    对外暴露的配置OkHttp方法
         * @param action            对外暴露的配置Retrofit方法
         */
        fun init(baseUrl: String, isReleased: Boolean = true,
                 okClientAction: ((builder: OkHttpClient.Builder) -> Unit)? = null,
                 retrofitAction: ((builder: Retrofit.Builder) -> Unit)?) {
            instance = HttpHelper(baseUrl, isReleased, okClientAction, retrofitAction)
        }

        fun <T> create(clazz: Class<T>): T {
            if (instance == null) {
                throw IllegalStateException("Call HttpHelper.init first.")
            }
            return instance!!.retrofit.create(clazz)
        }
    }

}