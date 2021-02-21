package com.kevin.ksoup

/**
 * HtmlParseException
 *
 * @author zwenkai@foxmail.com, Created on 2020-11-20 17:26:59
 *         Major Function：<b>This exception is raised if there is a serious issue that occurs during parsing of a html string</b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */
class KsoupException : Exception {

    /**
     * Creates exception with the specified message. If you are wrapping another exception,
     * consider using [KsoupException] instead.
     *
     * @param msg error message describing a possible cause of this exception.
     */
    constructor(msg: String?) : super(msg)

    /**
     * Creates exception with the specified message and cause.
     *
     * @param msg error message describing what happened.
     * @param cause root exception that caused this exception to be thrown.
     */
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)

    /**
     * Creates exception with the specified cause. Consider using
     * [KsoupException] instead if you can describe what happened.
     *
     * @param cause root exception that caused this exception to be thrown.
     */
    constructor(cause: Throwable?) : super(cause)
}