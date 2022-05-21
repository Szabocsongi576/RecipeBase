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
 * Recipe
 */
class Recipe {
    /**
     * Get country
     * @return country
     */
    @get:ApiModelProperty(example = "US", value = "")
    @SerializedName("country")
    var country: String? = null

    @SerializedName("instructions")
    private var instructions: MutableList<Instruction>? = null

    @SerializedName("facebook_posts")
    private var facebookPosts: MutableList<Any>? = null

    /**
     * Get userRatings
     * @return userRatings
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("user_ratings")
    var userRatings: UserRating? = null

    /**
     * Get servingsNounSingular
     * @return servingsNounSingular
     */
    @get:ApiModelProperty(example = "1", value = "")
    @SerializedName("servings_noun_singular")
    var servingsNounSingular: String? = null

    /**
     * Get prepTimeMinutes
     * @return prepTimeMinutes
     */
    @get:ApiModelProperty(example = "5.0", value = "")
    @SerializedName("prep_time_minutes")
    var prepTimeMinutes: Long? = null

    @SerializedName("sections")
    private var sections: MutableList<Section>? = null

    @SerializedName("credits")
    private var credits: MutableList<Credit>? = null

    /**
     * Get nutritionVisibility
     * @return nutritionVisibility
     */
    @get:ApiModelProperty(example = "auto", value = "")
    @SerializedName("nutrition_visibility")
    var nutritionVisibility: String? = null

    /**
     * Get language
     * @return language
     */
    @get:ApiModelProperty(example = "eng", value = "")
    @SerializedName("language")
    var language: String? = null

    /**
     * Get description
     * @return description
     */
    @get:ApiModelProperty(
        example = "Looking for a great way to spice up your normal bagel order? Try our honey Sriracha spread! Pair the sweet and spicy flavors with creamy avocado for a breakfast that’s sure to hit all of your tastebuds.",
        value = ""
    )
    @SerializedName("description")
    var description: String? = null

    /**
     * Get draftStatus
     * @return draftStatus
     */
    @get:ApiModelProperty(example = "published", value = "")
    @SerializedName("draft_status")
    var draftStatus: String? = null

    /**
     * Get updatedAt
     * @return updatedAt
     */
    @get:ApiModelProperty(example = "1.651250458E9", value = "")
    @SerializedName("updated_at")
    var updatedAt: Long? = null

    /**
     * Get videoId
     * @return videoId
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("video_id")
    var videoId: String? = null

    /**
     * Get totalTimeTier
     * @return totalTimeTier
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("total_time_tier")
    var totalTimeTier: TotalTimeTier? = null

    /**
     * Get originalVideoUrl
     * @return originalVideoUrl
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("original_video_url")
    var originalVideoUrl: String? = null

    /**
     * Get canonicalId
     * @return canonicalId
     */
    @get:ApiModelProperty(example = "recipe:8211", value = "")
    @SerializedName("canonical_id")
    var canonicalId: String? = null

    /**
     * Get keywords
     * @return keywords
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("keywords")
    var keywords: String? = null

    /**
     * Get showId
     * @return showId
     */
    @get:ApiModelProperty(example = "17.0", value = "")
    @SerializedName("show_id")
    var showId: Long? = null

    /**
     * Get numServings
     * @return numServings
     */
    @get:ApiModelProperty(example = "1.0", value = "")
    @SerializedName("num_servings")
    var numServings: Long? = null

    /**
     * Get thumbnailUrl
     * @return thumbnailUrl
     */
    @get:ApiModelProperty(
        example = "https://img.buzzfeed.com/tasty-app-user-assets-prod-us-east-1/recipes/af3183bd17d94810b7c66424208ec289.png",
        value = ""
    )
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String? = null

    /**
     * Get yields
     * @return yields
     */
    @get:ApiModelProperty(example = "Servings: 1", value = "")
    @SerializedName("yields")
    var yields: String? = null

    /**
     * Get slug
     * @return slug
     */
    @get:ApiModelProperty(example = "honey-sriracha-bagel", value = "")
    @SerializedName("slug")
    var slug: String? = null

    /**
     * Get buzzId
     * @return buzzId
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("buzz_id")
    var buzzId: String? = null

    /**
     * Get tipsAndRatingsEnabled
     * @return tipsAndRatingsEnabled
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("tips_and_ratings_enabled")
    var isTipsAndRatingsEnabled: Boolean? = null

    /**
     * Get show
     * @return show
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("show")
    var show: Show? = null

    /**
     * Get totalTimeMinutes
     * @return totalTimeMinutes
     */
    @get:ApiModelProperty(example = "10.0", value = "")
    @SerializedName("total_time_minutes")
    var totalTimeMinutes: Long? = null

    /**
     * Get approvedAt
     * @return approvedAt
     */
    @get:ApiModelProperty(example = "1.651250457E9", value = "")
    @SerializedName("approved_at")
    var approvedAt: Long? = null

    /**
     * Get servingsNounPlural
     * @return servingsNounPlural
     */
    @get:ApiModelProperty(example = "1", value = "")
    @SerializedName("servings_noun_plural")
    var servingsNounPlural: String? = null

    /**
     * Get promotion
     * @return promotion
     */
    @get:ApiModelProperty(example = "full", value = "")
    @SerializedName("promotion")
    var promotion: String? = null

    /**
     * Get id
     * @return id
     */
    @get:ApiModelProperty(example = "8211.0", value = "")
    @SerializedName("id")
    var id: Long? = null

    /**
     * Get name
     * @return name
     */
    @get:ApiModelProperty(example = "Honey Sriracha Bagel", value = "")
    @SerializedName("name")
    var name: String? = null

    /**
     * Get thumbnailAltText
     * @return thumbnailAltText
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("thumbnail_alt_text")
    var thumbnailAltText: String? = null

    @SerializedName("renditions")
    private var renditions: MutableList<Any>? = null

    /**
     * Get beautyUrl
     * @return beautyUrl
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("beauty_url")
    var beautyUrl: String? = null

    /**
     * Get videoAdContent
     * @return videoAdContent
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("video_ad_content")
    var videoAdContent: String? = null

    /**
     * Get cookTimeMinutes
     * @return cookTimeMinutes
     */
    @get:ApiModelProperty(example = "5.0", value = "")
    @SerializedName("cook_time_minutes")
    var cookTimeMinutes: Long? = null

    /**
     * Get createdAt
     * @return createdAt
     */
    @get:ApiModelProperty(example = "1.651247675E9", value = "")
    @SerializedName("created_at")
    var createdAt: Long? = null

    /**
     * Get videoUrl
     * @return videoUrl
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("video_url")
    var videoUrl: String? = null

    /**
     * Get isOneTop
     * @return isOneTop
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("is_one_top")
    var isIsOneTop: Boolean? = null
        private set

    /**
     * Get isShoppable
     * @return isShoppable
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("is_shoppable")
    var isIsShoppable: Boolean? = null
        private set

    @SerializedName("topics")
    private var topics: MutableList<Topics>? = null

    /**
     * Get seoTitle
     * @return seoTitle
     */
    @get:ApiModelProperty(example = "Honey Sriracha Bagel", value = "")
    @SerializedName("seo_title")
    var seoTitle: String? = null

    @SerializedName("tags")
    private var tags: MutableList<Tag>? = null

    /**
     * Get nutrition
     * @return nutrition
     */
    @get:ApiModelProperty(value = "")
    @SerializedName("nutrition")
    var nutrition: Nutrition? = null

    @SerializedName("compilations")
    private var compilations: MutableList<Any>? = null

    /**
     * Get aspectRatio
     * @return aspectRatio
     */
    @get:ApiModelProperty(example = "16:9", value = "")
    @SerializedName("aspect_ratio")
    var aspectRatio: String? = null

    /**
     * Get inspiredByUrl
     * @return inspiredByUrl
     */
    @get:ApiModelProperty(
        example = "https://www.tiktok.com/@ashka_vaidya/video/7047147183816117509?is_copy_url=1&is_from_webapp=v1&q=honey%20siracha%20bagel%20&t=1650561775418",
        value = ""
    )
    @SerializedName("inspired_by_url")
    var inspiredByUrl: String? = null
    fun country(country: String?): Recipe {
        this.country = country
        return this
    }

    fun instructions(instructions: MutableList<Instruction>?): Recipe {
        this.instructions = instructions
        return this
    }

    fun addInstructionsItem(instructionsItem: Instruction): Recipe {
        if (instructions == null) {
            instructions = ArrayList()
        }
        instructions!!.add(instructionsItem)
        return this
    }

    /**
     * Get instructions
     * @return instructions
     */
    @ApiModelProperty(value = "")
    fun getInstructions(): List<Instruction>? {
        return instructions
    }

    fun setInstructions(instructions: MutableList<Instruction>?) {
        this.instructions = instructions
    }

    fun facebookPosts(facebookPosts: MutableList<Any>?): Recipe {
        this.facebookPosts = facebookPosts
        return this
    }

    fun addFacebookPostsItem(facebookPostsItem: Any): Recipe {
        if (facebookPosts == null) {
            facebookPosts = ArrayList()
        }
        facebookPosts!!.add(facebookPostsItem)
        return this
    }

    /**
     * Get facebookPosts
     * @return facebookPosts
     */
    @ApiModelProperty(value = "")
    fun getFacebookPosts(): List<Any>? {
        return facebookPosts
    }

    fun setFacebookPosts(facebookPosts: MutableList<Any>?) {
        this.facebookPosts = facebookPosts
    }

    fun userRatings(userRatings: UserRating?): Recipe {
        this.userRatings = userRatings
        return this
    }

    fun servingsNounSingular(servingsNounSingular: String?): Recipe {
        this.servingsNounSingular = servingsNounSingular
        return this
    }

    fun prepTimeMinutes(prepTimeMinutes: Long?): Recipe {
        this.prepTimeMinutes = prepTimeMinutes
        return this
    }

    fun sections(sections: MutableList<Section>?): Recipe {
        this.sections = sections
        return this
    }

    fun addSectionsItem(sectionsItem: Section): Recipe {
        if (sections == null) {
            sections = ArrayList()
        }
        sections!!.add(sectionsItem)
        return this
    }

    /**
     * Get sections
     * @return sections
     */
    @ApiModelProperty(value = "")
    fun getSections(): List<Section>? {
        return sections
    }

    fun setSections(sections: MutableList<Section>?) {
        this.sections = sections
    }

    fun credits(credits: MutableList<Credit>?): Recipe {
        this.credits = credits
        return this
    }

    fun addCreditsItem(creditsItem: Credit): Recipe {
        if (credits == null) {
            credits = ArrayList()
        }
        credits!!.add(creditsItem)
        return this
    }

    /**
     * Get credits
     * @return credits
     */
    @ApiModelProperty(value = "")
    fun getCredits(): List<Credit>? {
        return credits
    }

    fun setCredits(credits: MutableList<Credit>?) {
        this.credits = credits
    }

    fun nutritionVisibility(nutritionVisibility: String?): Recipe {
        this.nutritionVisibility = nutritionVisibility
        return this
    }

    fun language(language: String?): Recipe {
        this.language = language
        return this
    }

    fun description(description: String?): Recipe {
        this.description = description
        return this
    }

    fun draftStatus(draftStatus: String?): Recipe {
        this.draftStatus = draftStatus
        return this
    }

    fun updatedAt(updatedAt: Long?): Recipe {
        this.updatedAt = updatedAt
        return this
    }

    fun videoId(videoId: String?): Recipe {
        this.videoId = videoId
        return this
    }

    fun totalTimeTier(totalTimeTier: TotalTimeTier?): Recipe {
        this.totalTimeTier = totalTimeTier
        return this
    }

    fun originalVideoUrl(originalVideoUrl: String?): Recipe {
        this.originalVideoUrl = originalVideoUrl
        return this
    }

    fun canonicalId(canonicalId: String?): Recipe {
        this.canonicalId = canonicalId
        return this
    }

    fun keywords(keywords: String?): Recipe {
        this.keywords = keywords
        return this
    }

    fun showId(showId: Long?): Recipe {
        this.showId = showId
        return this
    }

    fun numServings(numServings: Long?): Recipe {
        this.numServings = numServings
        return this
    }

    fun thumbnailUrl(thumbnailUrl: String?): Recipe {
        this.thumbnailUrl = thumbnailUrl
        return this
    }

    fun yields(yields: String?): Recipe {
        this.yields = yields
        return this
    }

    fun slug(slug: String?): Recipe {
        this.slug = slug
        return this
    }

    fun buzzId(buzzId: String?): Recipe {
        this.buzzId = buzzId
        return this
    }

    fun tipsAndRatingsEnabled(tipsAndRatingsEnabled: Boolean?): Recipe {
        isTipsAndRatingsEnabled = tipsAndRatingsEnabled
        return this
    }

    fun show(show: Show?): Recipe {
        this.show = show
        return this
    }

    fun totalTimeMinutes(totalTimeMinutes: Long?): Recipe {
        this.totalTimeMinutes = totalTimeMinutes
        return this
    }

    fun approvedAt(approvedAt: Long?): Recipe {
        this.approvedAt = approvedAt
        return this
    }

    fun servingsNounPlural(servingsNounPlural: String?): Recipe {
        this.servingsNounPlural = servingsNounPlural
        return this
    }

    fun promotion(promotion: String?): Recipe {
        this.promotion = promotion
        return this
    }

    fun id(id: Long?): Recipe {
        this.id = id
        return this
    }

    fun name(name: String?): Recipe {
        this.name = name
        return this
    }

    fun thumbnailAltText(thumbnailAltText: String?): Recipe {
        this.thumbnailAltText = thumbnailAltText
        return this
    }

    fun renditions(renditions: MutableList<Any>?): Recipe {
        this.renditions = renditions
        return this
    }

    fun addRenditionsItem(renditionsItem: Any): Recipe {
        if (renditions == null) {
            renditions = ArrayList()
        }
        renditions!!.add(renditionsItem)
        return this
    }

    /**
     * Get renditions
     * @return renditions
     */
    @ApiModelProperty(value = "")
    fun getRenditions(): List<Any>? {
        return renditions
    }

    fun setRenditions(renditions: MutableList<Any>?) {
        this.renditions = renditions
    }

    fun beautyUrl(beautyUrl: String?): Recipe {
        this.beautyUrl = beautyUrl
        return this
    }

    fun videoAdContent(videoAdContent: String?): Recipe {
        this.videoAdContent = videoAdContent
        return this
    }

    fun cookTimeMinutes(cookTimeMinutes: Long?): Recipe {
        this.cookTimeMinutes = cookTimeMinutes
        return this
    }

    fun createdAt(createdAt: Long?): Recipe {
        this.createdAt = createdAt
        return this
    }

    fun videoUrl(videoUrl: String?): Recipe {
        this.videoUrl = videoUrl
        return this
    }

    fun isOneTop(isOneTop: Boolean?): Recipe {
        isIsOneTop = isOneTop
        return this
    }

    fun setIsOneTop(isOneTop: Boolean?) {
        isIsOneTop = isOneTop
    }

    fun isShoppable(isShoppable: Boolean?): Recipe {
        isIsShoppable = isShoppable
        return this
    }

    fun setIsShoppable(isShoppable: Boolean?) {
        isIsShoppable = isShoppable
    }

    fun topics(topics: MutableList<Topics>?): Recipe {
        this.topics = topics
        return this
    }

    fun addTopicsItem(topicsItem: Topics): Recipe {
        if (topics == null) {
            topics = ArrayList()
        }
        topics!!.add(topicsItem)
        return this
    }

    /**
     * Get topics
     * @return topics
     */
    @ApiModelProperty(value = "")
    fun getTopics(): List<Topics>? {
        return topics
    }

    fun setTopics(topics: MutableList<Topics>?) {
        this.topics = topics
    }

    fun seoTitle(seoTitle: String?): Recipe {
        this.seoTitle = seoTitle
        return this
    }

    fun tags(tags: MutableList<Tag>?): Recipe {
        this.tags = tags
        return this
    }

    fun addTagsItem(tagsItem: Tag): Recipe {
        if (tags == null) {
            tags = ArrayList()
        }
        tags!!.add(tagsItem)
        return this
    }

    /**
     * Get tags
     * @return tags
     */
    @ApiModelProperty(value = "")
    fun getTags(): List<Tag>? {
        return tags
    }

    fun setTags(tags: MutableList<Tag>?) {
        this.tags = tags
    }

    fun nutrition(nutrition: Nutrition?): Recipe {
        this.nutrition = nutrition
        return this
    }

    fun compilations(compilations: MutableList<Any>?): Recipe {
        this.compilations = compilations
        return this
    }

    fun addCompilationsItem(compilationsItem: Any): Recipe {
        if (compilations == null) {
            compilations = ArrayList()
        }
        compilations!!.add(compilationsItem)
        return this
    }

    /**
     * Get compilations
     * @return compilations
     */
    @ApiModelProperty(value = "")
    fun getCompilations(): List<Any>? {
        return compilations
    }

    fun setCompilations(compilations: MutableList<Any>?) {
        this.compilations = compilations
    }

    fun aspectRatio(aspectRatio: String?): Recipe {
        this.aspectRatio = aspectRatio
        return this
    }

    fun inspiredByUrl(inspiredByUrl: String?): Recipe {
        this.inspiredByUrl = inspiredByUrl
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val recipe = o as Recipe
        return country == recipe.country &&
                instructions == recipe.instructions &&
                facebookPosts == recipe.facebookPosts &&
                userRatings == recipe.userRatings &&
                servingsNounSingular == recipe.servingsNounSingular &&
                prepTimeMinutes == recipe.prepTimeMinutes &&
                sections == recipe.sections &&
                credits == recipe.credits &&
                nutritionVisibility == recipe.nutritionVisibility &&
                language == recipe.language &&
                description == recipe.description &&
                draftStatus == recipe.draftStatus &&
                updatedAt == recipe.updatedAt &&
                videoId == recipe.videoId &&
                totalTimeTier == recipe.totalTimeTier &&
                originalVideoUrl == recipe.originalVideoUrl &&
                canonicalId == recipe.canonicalId &&
                keywords == recipe.keywords &&
                showId == recipe.showId &&
                numServings == recipe.numServings &&
                thumbnailUrl == recipe.thumbnailUrl &&
                yields == recipe.yields &&
                slug == recipe.slug &&
                buzzId == recipe.buzzId &&
                isTipsAndRatingsEnabled == recipe.isTipsAndRatingsEnabled &&
                show == recipe.show &&
                totalTimeMinutes == recipe.totalTimeMinutes &&
                approvedAt == recipe.approvedAt &&
                servingsNounPlural == recipe.servingsNounPlural &&
                promotion == recipe.promotion &&
                id == recipe.id &&
                name == recipe.name &&
                thumbnailAltText == recipe.thumbnailAltText &&
                renditions == recipe.renditions &&
                beautyUrl == recipe.beautyUrl &&
                videoAdContent == recipe.videoAdContent &&
                cookTimeMinutes == recipe.cookTimeMinutes &&
                createdAt == recipe.createdAt &&
                videoUrl == recipe.videoUrl &&
                isIsOneTop == recipe.isIsOneTop &&
                isIsShoppable == recipe.isIsShoppable &&
                topics == recipe.topics &&
                seoTitle == recipe.seoTitle &&
                tags == recipe.tags &&
                nutrition == recipe.nutrition &&
                compilations == recipe.compilations &&
                aspectRatio == recipe.aspectRatio &&
                inspiredByUrl == recipe.inspiredByUrl
    }

    override fun hashCode(): Int {
        return Objects.hash(
            country,
            instructions,
            facebookPosts,
            userRatings,
            servingsNounSingular,
            prepTimeMinutes,
            sections,
            credits,
            nutritionVisibility,
            language,
            description,
            draftStatus,
            updatedAt,
            videoId,
            totalTimeTier,
            originalVideoUrl,
            canonicalId,
            keywords,
            showId,
            numServings,
            thumbnailUrl,
            yields,
            slug,
            buzzId,
            isTipsAndRatingsEnabled,
            show,
            totalTimeMinutes,
            approvedAt,
            servingsNounPlural,
            promotion,
            id,
            name,
            thumbnailAltText,
            renditions,
            beautyUrl,
            videoAdContent,
            cookTimeMinutes,
            createdAt,
            videoUrl,
            isIsOneTop,
            isIsShoppable,
            topics,
            seoTitle,
            tags,
            nutrition,
            compilations,
            aspectRatio,
            inspiredByUrl
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Recipe {\n")
        sb.append("    country: ").append(toIndentedString(country)).append("\n")
        sb.append("    instructions: ").append(toIndentedString(instructions)).append("\n")
        sb.append("    facebookPosts: ").append(toIndentedString(facebookPosts)).append("\n")
        sb.append("    userRatings: ").append(toIndentedString(userRatings)).append("\n")
        sb.append("    servingsNounSingular: ").append(toIndentedString(servingsNounSingular))
            .append("\n")
        sb.append("    prepTimeMinutes: ").append(toIndentedString(prepTimeMinutes)).append("\n")
        sb.append("    sections: ").append(toIndentedString(sections)).append("\n")
        sb.append("    credits: ").append(toIndentedString(credits)).append("\n")
        sb.append("    nutritionVisibility: ").append(toIndentedString(nutritionVisibility))
            .append("\n")
        sb.append("    language: ").append(toIndentedString(language)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
        sb.append("    draftStatus: ").append(toIndentedString(draftStatus)).append("\n")
        sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n")
        sb.append("    videoId: ").append(toIndentedString(videoId)).append("\n")
        sb.append("    totalTimeTier: ").append(toIndentedString(totalTimeTier)).append("\n")
        sb.append("    originalVideoUrl: ").append(toIndentedString(originalVideoUrl)).append("\n")
        sb.append("    canonicalId: ").append(toIndentedString(canonicalId)).append("\n")
        sb.append("    keywords: ").append(toIndentedString(keywords)).append("\n")
        sb.append("    showId: ").append(toIndentedString(showId)).append("\n")
        sb.append("    numServings: ").append(toIndentedString(numServings)).append("\n")
        sb.append("    thumbnailUrl: ").append(toIndentedString(thumbnailUrl)).append("\n")
        sb.append("    yields: ").append(toIndentedString(yields)).append("\n")
        sb.append("    slug: ").append(toIndentedString(slug)).append("\n")
        sb.append("    buzzId: ").append(toIndentedString(buzzId)).append("\n")
        sb.append("    tipsAndRatingsEnabled: ").append(toIndentedString(isTipsAndRatingsEnabled))
            .append("\n")
        sb.append("    show: ").append(toIndentedString(show)).append("\n")
        sb.append("    totalTimeMinutes: ").append(toIndentedString(totalTimeMinutes)).append("\n")
        sb.append("    approvedAt: ").append(toIndentedString(approvedAt)).append("\n")
        sb.append("    servingsNounPlural: ").append(toIndentedString(servingsNounPlural))
            .append("\n")
        sb.append("    promotion: ").append(toIndentedString(promotion)).append("\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    thumbnailAltText: ").append(toIndentedString(thumbnailAltText)).append("\n")
        sb.append("    renditions: ").append(toIndentedString(renditions)).append("\n")
        sb.append("    beautyUrl: ").append(toIndentedString(beautyUrl)).append("\n")
        sb.append("    videoAdContent: ").append(toIndentedString(videoAdContent)).append("\n")
        sb.append("    cookTimeMinutes: ").append(toIndentedString(cookTimeMinutes)).append("\n")
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n")
        sb.append("    videoUrl: ").append(toIndentedString(videoUrl)).append("\n")
        sb.append("    isOneTop: ").append(toIndentedString(isIsOneTop)).append("\n")
        sb.append("    isShoppable: ").append(toIndentedString(isIsShoppable)).append("\n")
        sb.append("    topics: ").append(toIndentedString(topics)).append("\n")
        sb.append("    seoTitle: ").append(toIndentedString(seoTitle)).append("\n")
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n")
        sb.append("    nutrition: ").append(toIndentedString(nutrition)).append("\n")
        sb.append("    compilations: ").append(toIndentedString(compilations)).append("\n")
        sb.append("    aspectRatio: ").append(toIndentedString(aspectRatio)).append("\n")
        sb.append("    inspiredByUrl: ").append(toIndentedString(inspiredByUrl)).append("\n")
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