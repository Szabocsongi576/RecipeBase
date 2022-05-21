/*
 * Recipebase REST API
 * Recipe database API description.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package hu.bme.aut.recipebase.network.model

import com.google.gson.annotations.SerializedName
import io.swagger.annotations.ApiModelProperty
import java.lang.StringBuilder
import java.util.*

/**
 * Nutrition
 */
class Nutrition {
    @SerializedName("updated_at")
    private var updatedAt: Date? = null

    /**
     * Get protein
     * @return protein
     */
    @get:ApiModelProperty(example = "12.0", value = "")
    @SerializedName("protein")
    var protein: Long? = null

    /**
     * Get fat
     * @return fat
     */
    @get:ApiModelProperty(example = "12.0", value = "")
    @SerializedName("fat")
    var fat: Long? = null

    /**
     * Get calories
     * @return calories
     */
    @get:ApiModelProperty(example = "557.0", value = "")
    @SerializedName("calories")
    var calories: Long? = null

    /**
     * Get sugar
     * @return sugar
     */
    @get:ApiModelProperty(example = "54.0", value = "")
    @SerializedName("sugar")
    var sugar: Long? = null

    /**
     * Get carbohydrates
     * @return carbohydrates
     */
    @get:ApiModelProperty(example = "103.0", value = "")
    @SerializedName("carbohydrates")
    var carbohydrates: Long? = null

    /**
     * Get fiber
     * @return fiber
     */
    @get:ApiModelProperty(example = "32.0", value = "")
    @SerializedName("fiber")
    var fiber: Long? = null
    fun updatedAt(updatedAt: Date?): Nutrition {
        this.updatedAt = updatedAt
        return this
    }

    /**
     * Get updatedAt
     * @return updatedAt
     */
    @ApiModelProperty(example = "2022-05-01T08:01:31+02:00", value = "")
    fun getUpdatedAt(): Date? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: Date?) {
        this.updatedAt = updatedAt
    }

    fun protein(protein: Long?): Nutrition {
        this.protein = protein
        return this
    }

    fun fat(fat: Long?): Nutrition {
        this.fat = fat
        return this
    }

    fun calories(calories: Long?): Nutrition {
        this.calories = calories
        return this
    }

    fun sugar(sugar: Long?): Nutrition {
        this.sugar = sugar
        return this
    }

    fun carbohydrates(carbohydrates: Long?): Nutrition {
        this.carbohydrates = carbohydrates
        return this
    }

    fun fiber(fiber: Long?): Nutrition {
        this.fiber = fiber
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val nutrition = o as Nutrition
        return updatedAt == nutrition.updatedAt &&
                protein == nutrition.protein &&
                fat == nutrition.fat &&
                calories == nutrition.calories &&
                sugar == nutrition.sugar &&
                carbohydrates == nutrition.carbohydrates &&
                fiber == nutrition.fiber
    }

    override fun hashCode(): Int {
        return Objects.hash(updatedAt, protein, fat, calories, sugar, carbohydrates, fiber)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Nutrition {\n")
        sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n")
        sb.append("    protein: ").append(toIndentedString(protein)).append("\n")
        sb.append("    fat: ").append(toIndentedString(fat)).append("\n")
        sb.append("    calories: ").append(toIndentedString(calories)).append("\n")
        sb.append("    sugar: ").append(toIndentedString(sugar)).append("\n")
        sb.append("    carbohydrates: ").append(toIndentedString(carbohydrates)).append("\n")
        sb.append("    fiber: ").append(toIndentedString(fiber)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}