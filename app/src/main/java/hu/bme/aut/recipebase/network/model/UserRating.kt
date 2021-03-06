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
import java.math.BigDecimal
import java.util.*

/**
 * UserRating
 */
class UserRating {
    /**
     * Get countPositive
     * @return countPositive
     */
    @get:ApiModelProperty(example = "0.0", value = "")
    @SerializedName("count_positive")
    var countPositive: Long? = null

    /**
     * Get score
     * @return score
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("score")
    var score: String? = null

    /**
     * Get countNegative
     * @return countNegative
     */
    @get:ApiModelProperty(example = "0.0", value = "")
    @SerializedName("count_negative")
    var countNegative: Long? = null
    fun countPositive(countPositive: Long?): UserRating {
        this.countPositive = countPositive
        return this
    }

    fun score(score: String?): UserRating {
        this.score = score
        return this
    }

    fun countNegative(countNegative: Long?): UserRating {
        this.countNegative = countNegative
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val userRating = o as UserRating
        return countPositive == userRating.countPositive &&
                score == userRating.score &&
                countNegative == userRating.countNegative
    }

    override fun hashCode(): Int {
        return Objects.hash(countPositive, score, countNegative)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class UserRating {\n")
        sb.append("    countPositive: ").append(toIndentedString(countPositive)).append("\n")
        sb.append("    score: ").append(toIndentedString(score)).append("\n")
        sb.append("    countNegative: ").append(toIndentedString(countNegative)).append("\n")
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