package hu.bme.aut.recipebase.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class DbRecipe(
    @PrimaryKey(autoGenerate = true) var recipeId: Long,
    var country: String?,
    var servingsNounSingular: String?,
    var prepTimeMinutes: BigDecimal?,
    var brandId: String?,
    var nutritionVisibility: String?,
    var language: String?,
    var brand: String?,
    var description: String?,
    var draftStatus: String?,
    var updatedAt: BigDecimal?,
    var videoId: String?,
    var originalVideoUrl: String?,
    var canonicalId: String?,
    var keywords: String?,
    var showId: BigDecimal?,
    var numServings: BigDecimal?,
    var thumbnailUrl: String?,
    var yields: String?,
    var slug: String?,
    var buzzId: String?,
    var isTipsAndRatingsEnabled: Boolean?,
    var totalTimeMinutes: BigDecimal?,
    var approvedAt: BigDecimal?,
    var servingsNounPlural: String?,
    var promotion: String?,
    var id: BigDecimal?,
    var name: String?,
    var thumbnailAltText: String?,
    var beautyUrl: String?,
    var videoAdContent: String?,
    var cookTimeMinutes: BigDecimal?,
    var createdAt: BigDecimal?,
    var videoUrl: String?,
    var isIsOneTop: Boolean?,
    var isIsShoppable: Boolean?,
    var seoTitle: String?,
    var aspectRatio: String?,
    var inspiredByUrl: String?,
)