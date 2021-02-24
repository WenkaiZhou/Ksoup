package com.kevin.ksoup.annontation

import com.kevin.ksoup.Attrs

/**
 * Pick
 *
 * @author zwenkai@foxmail.com, Created on 2020-11-20 11:25:38
 *         Major Function：<b>Pick config annotation.</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class Pick(

    val value: String = "",
    val attr: String = Attrs.TEXT,
    val regex: String = ""
)