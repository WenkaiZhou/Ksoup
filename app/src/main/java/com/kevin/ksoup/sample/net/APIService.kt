package com.kevin.ksoup.sample.net

import com.kevin.ksoup.sample.net.entity.RecipeHomeEntity
import retrofit2.http.*

/**
 * APIService
 *
 * @author zwenkai@foxmail.com, Created on 2020-11-20 10:12:21
 *         Major Function：<b></b>
 *         <p/>
 *         Note: If you modify this class please fill in the following content as a record.
 * @author mender，Modified Date Modify Content:
 */

interface APIService {

    @GET("/recipe.html")
    suspend fun recipeHome(): RecipeHomeEntity
}