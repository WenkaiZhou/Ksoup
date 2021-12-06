package com.kevin.ksoup.sample

import ParsedArticle
import com.kevin.ksoup.Ksoup
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Paths

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val testFolder = """C:\Users\Peter\work\other\Ksoup\ksoup\src\main\res"""
    private var cookingArticle: String? = null
    private var tropesArticle: String? = null

    @Before
    fun setup() {
        cookingArticle = Paths.get(testFolder, "site.html").toFile().readText()
        tropesArticle = Paths.get(testFolder, "article.html").toFile().readBytes().toString(Charset.forName("Windows-1252"))
    }

    @Test
    fun home_works() {
        val k = Ksoup()
        val article = k.parse(cookingArticle!!, HomeEntity::class.java, Charsets.UTF_8.name())
        assertNotNull(article.channelList)
        assertEquals("Search recipes, ingredients...", article.searchPlaceholder)
        assertEquals("""<img src="./Gourmet World_Original recipes and gourmet life community, all my friends are foodies!_files/loading-32-32.gif" width="16" height="16"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&nbsp;Loading </font></font>""",
            article.timeline!!)
        writeToFile(article.timeline!!, "site.html")
    }

    @Test
    fun tropes_works() {
        val k = Ksoup()
        val article = k.parse<ParsedArticle>(tropesArticle!!, "cp1252")
        assertNotNull(article.content)
        assertEquals(2, article.folderlabels?.size)
        writeToFile(article.content!!, "article.html")
    }

    private fun writeToFile(content: String, fname: String) {
        File("C:\\Users\\Peter\\work\\other\\Ksoup\\out\\$fname").writeText(content)
    }
}