package hu.bme.aut.recipebase

import hu.bme.aut.recipebase.createDbRecipe
import hu.bme.aut.recipebase.createNetworkRecipe
import hu.bme.aut.recipebase.persistence.model.DbRecipe
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.shouldBe

@Test
class MappingsTest : StringSpec({

    "test Recipe to DbRecipe" {
        val recipe = createNetworkRecipe(1, "Test")

        val dbRecipe = DbRecipe.fromDomainObject(recipe)

        dbRecipe shouldBe createDbRecipe(1, "Test")
    }

    "test DbRecipe to Recipe" {
        val dbRecipe = createDbRecipe(1, "Test")

        val recipe = dbRecipe.toDomainObject()

        recipe shouldBe createNetworkRecipe(1, "Test")
    }
})
