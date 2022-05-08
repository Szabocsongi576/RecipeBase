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
package hu.bme.aut.recipebase.model.network

import com.google.gson.annotations.SerializedName
import io.swagger.annotations.ApiModelProperty
import java.lang.StringBuilder
import java.math.BigDecimal
import java.util.*

/**
 * Tag
 */
class Tag {
    /**
     * Get name
     * @return name
     */
    @get:ApiModelProperty(example = "lunch", value = "")
    @SerializedName("name")
    var name: String? = null

    /**
     * Get id
     * @return id
     */
    @get:ApiModelProperty(example = "64489.0", value = "")
    @SerializedName("id")
    var id: BigDecimal? = null

    /**
     * Get displayName
     * @return displayName
     */
    @get:ApiModelProperty(example = "Lunch", value = "")
    @SerializedName("display_name")
    var displayName: String? = null

    /**
     * Get type
     * @return type
     */
    @get:ApiModelProperty(example = "meal", value = "")
    @SerializedName("type")
    var type: String? = null
    fun name(name: String?): Tag {
        this.name = name
        return this
    }

    fun id(id: BigDecimal?): Tag {
        this.id = id
        return this
    }

    fun displayName(displayName: String?): Tag {
        this.displayName = displayName
        return this
    }

    fun type(type: String?): Tag {
        this.type = type
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tag = o as Tag
        return name == tag.name &&
                id == tag.id &&
                displayName == tag.displayName &&
                type == tag.type
    }

    override fun hashCode(): Int {
        return Objects.hash(name, id, displayName, type)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Tag {\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
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