package com.kevin.ksoup.sample.net

import com.kevin.net.HttpHelper

/**
 * HttpService
 *
 * @author zwenkai@foxmail.com, Created on 2020-11-20 10:31:43
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
val apiService: APIService by lazy {
    HttpHelper.create(APIService::class.java)
}