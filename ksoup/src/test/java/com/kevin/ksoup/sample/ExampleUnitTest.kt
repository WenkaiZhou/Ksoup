package com.kevin.ksoup.sample

import com.kevin.ksoup.Ksoup
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val testFilename = """C:\Users\Peter\work\other\Ksoup\ksoup\src\main\res\site.html"""
    private var articleText: String? = null

    @Before
    fun setup() {
        articleText = File(testFilename).readText()
    }

    @Test
    fun addition_isCorrect() {
        val k = Ksoup()
        val article = k.parse(articleText!!, HomeEntity::class.java)
        assertNotNull(article.channelList)
        assertNotNull(article.channelList?.first())
        assert(article.timeline?.isNotEmpty() ?: false)
        writeToFile(article.timeline!!)
    }

    private fun writeToFile(content: String) {
        File("""C:\Users\Peter\work\other\Ksoup\out\article.html""").writeText(content)
    }
}