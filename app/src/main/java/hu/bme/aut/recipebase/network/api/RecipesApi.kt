package hu.bme.aut.recipebase.network.api

import android.database.Observable
import hu.bme.aut.recipebase.network.model.Recipe
import hu.bme.aut.recipebase.network.model.RecipeList
import retrofit2.http.*
import java.math.BigDecimal

interface RecipesApi {
    /**
     * Creates a new Recipe
     *
     * @param body The Recipe to create (required)
     * @return Observable&lt;Void&gt;
     */
    @Headers("Content-Type:application/json")
    @POST("recipes")
    fun createNewRecipe(
        @retrofit2.http.Body body: Recipe?
    ): Observable<Void?>?

    /**
     * Deletes a Recipe
     *
     * @param id Recipe id to delete (required)
     * @return Observable&lt;Void&gt;
     */
    @DELETE("recipes/{Id}")
    fun deleteRecipe(
        @retrofit2.http.Path("Id") id: Long?
    ): Observable<Void?>?

    /**
     * Returns all Recipes
     *
     * @param from The offset of items to be ignored in response for paging (required)
     * @param size Number of items returned per response (required)
     * @param tags Get suitable values from /tags/list API (optional, default to easy)
     * @param q Name of food or, ingredients to search by (optional)
     * @return Observable&lt;List&lt;RecipeList&gt;&gt;
     */
    @GET("recipes/list")
    fun getAllRecipes(
        @retrofit2.http.Query("from") from: BigDecimal?,
        @retrofit2.http.Query("size") size: BigDecimal?,
        @retrofit2.http.Query("tags") tags: String?,
        @retrofit2.http.Query("q") q: String?
    ): Observable<List<RecipeList?>?>?

    /**
     * Returns a Recipe by id
     *
     * @param id The id value of any recipe returned in recipes/list API (required)
     * @return Observable&lt;Recipe&gt;
     */
    @GET("recipes/get-more-info")
    fun getRecipe(
        @retrofit2.http.Query("id") id: Long?
    ): Observable<Recipe?>?

    /**
     * Update an existing Recipe
     *
     * @param id ID of the Recipe to return (required)
     * @param body The Recipe used to update the existing. (required)
     * @return Observable&lt;Void&gt;
     */
    @Headers("Content-Type:application/json")
    @PUT("recipes/{Id}")
    fun updateRecipe(
        @retrofit2.http.Path("Id") id: Long?, @retrofit2.http.Body body: Recipe?
    ): Observable<Void?>?
}