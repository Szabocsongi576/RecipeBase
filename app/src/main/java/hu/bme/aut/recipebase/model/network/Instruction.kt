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
 * Instruction
 */
class Instruction {
    /**
     * Get startTime
     * @return startTime
     */
    @get:ApiModelProperty(example = "0.0", value = "")
    @SerializedName("start_time")
    var startTime: BigDecimal? = null

    /**
     * Get appliance
     * @return appliance
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("appliance")
    var appliance: String? = null

    /**
     * Get endTime
     * @return endTime
     */
    @get:ApiModelProperty(example = "0.0", value = "")
    @SerializedName("end_time")
    var endTime: BigDecimal? = null

    /**
     * Get temperature
     * @return temperature
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("temperature")
    var temperature: String? = null

    /**
     * Get id
     * @return id
     */
    @get:ApiModelProperty(example = "71444.0", value = "")
    @SerializedName("id")
    var id: BigDecimal? = null

    /**
     * Get position
     * @return position
     */
    @get:ApiModelProperty(example = "1.0", value = "")
    @SerializedName("position")
    var position: BigDecimal? = null

    /**
     * Get displayText
     * @return displayText
     */
    @get:ApiModelProperty(
        example = "Preheat the toaster oven according to the manufacturer’s instructions.",
        value = ""
    )
    @SerializedName("display_text")
    var displayText: String? = null
    fun startTime(startTime: BigDecimal?): Instruction {
        this.startTime = startTime
        return this
    }

    fun appliance(appliance: String?): Instruction {
        this.appliance = appliance
        return this
    }

    fun endTime(endTime: BigDecimal?): Instruction {
        this.endTime = endTime
        return this
    }

    fun temperature(temperature: String?): Instruction {
        this.temperature = temperature
        return this
    }

    fun id(id: BigDecimal?): Instruction {
        this.id = id
        return this
    }

    fun position(position: BigDecimal?): Instruction {
        this.position = position
        return this
    }

    fun displayText(displayText: String?): Instruction {
        this.displayText = displayText
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val instruction = o as Instruction
        return startTime == instruction.startTime &&
                appliance == instruction.appliance &&
                endTime == instruction.endTime &&
                temperature == instruction.temperature &&
                id == instruction.id &&
                position == instruction.position &&
                displayText == instruction.displayText
    }

    override fun hashCode(): Int {
        return Objects.hash(startTime, appliance, endTime, temperature, id, position, displayText)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Instruction {\n")
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n")
        sb.append("    appliance: ").append(toIndentedString(appliance)).append("\n")
        sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n")
        sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    position: ").append(toIndentedString(position)).append("\n")
        sb.append("    displayText: ").append(toIndentedString(displayText)).append("\n")
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