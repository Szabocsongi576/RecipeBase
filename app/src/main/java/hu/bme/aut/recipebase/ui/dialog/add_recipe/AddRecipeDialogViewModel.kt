package hu.bme.aut.recipebase.ui.dialog.add_recipe

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.recipebase.network.model.*
import hu.bme.aut.recipebase.ui.state.ErrorState
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddRecipeDialogViewModel @Inject constructor(
    application: Application,
    private val repository: AddRecipeDialogRepository
) :
    AndroidViewModel(application) {

    private val _nameTextState: MutableState<String> = mutableStateOf(value = "")
    val nameTextState: State<String> = _nameTextState

    private val _componentsTextState: MutableState<String> = mutableStateOf(value = "")
    val componentsTextState: State<String> = _componentsTextState

    private val _instructionsTextState: MutableState<String> = mutableStateOf(value = "")
    val instructionsTextState: State<String> = _instructionsTextState

    private val _sugarTextState: MutableState<String> = mutableStateOf(value = "")
    val sugarTextState: State<String> = _sugarTextState

    private val _fatTextState: MutableState<String> = mutableStateOf(value = "")
    val fatTextState: State<String> = _fatTextState

    private val _proteinTextState: MutableState<String> = mutableStateOf(value = "")
    val proteinTextState: State<String> = _proteinTextState

    private val _fiberTextState: MutableState<String> = mutableStateOf(value = "")
    val fiberTextState: State<String> = _fiberTextState

    private val _carbohydratesTextState: MutableState<String> = mutableStateOf(value = "")
    val carbohydratesTextState: State<String> = _carbohydratesTextState

    private val _caloriesTextState: MutableState<String> = mutableStateOf(value = "")
    val caloriesTextState: State<String> = _caloriesTextState

    private val _errorState: MutableState<ErrorState> = mutableStateOf(value = ErrorState.Empty)
    val errorState: State<ErrorState> = _errorState

    private val _loadingState: MutableState<Boolean> = mutableStateOf(value = false)
    val loadingState: State<Boolean> = _loadingState

    fun updateNameTextState(newValue: String) {
        _nameTextState.value = newValue
    }

    fun updateComponentsTextState(newValue: String) {
        _componentsTextState.value = newValue
    }

    fun updateInstructionsTextState(newValue: String) {
        _instructionsTextState.value = newValue
    }

    fun updateSugarTextState(newValue: String) {
        _sugarTextState.value = newValue
    }

    fun updateFatTextState(newValue: String) {
        _fatTextState.value = newValue
    }

    fun updateProteinTextState(newValue: String) {
        _proteinTextState.value = newValue
    }

    fun updateFiberTextState(newValue: String) {
        _fiberTextState.value = newValue
    }

    fun updateCarbohydratesTextState(newValue: String) {
        _carbohydratesTextState.value = newValue
    }

    fun updateCaloriesTextState(newValue: String) {
        _caloriesTextState.value = newValue
    }

    fun updateErrorState(newValue: ErrorState) {
        _errorState.value = newValue
    }

    private fun validate(): String {
        var error = ""

        if (_componentsTextState.value.isEmpty()) {
            error += "Components field is required"
        }

        if (_instructionsTextState.value.isEmpty()) {
            if (error.isNotEmpty()) {
                error += "\n"
            }
            error += "Instruction field is required"
        }

        if(_sugarTextState.value.isNotEmpty()) {
            try {
                BigDecimal(_sugarTextState.value)
            } catch (e: NumberFormatException) {
                if (error.isNotEmpty()) {
                    error += "\n"
                }
                error += "Sugar is not a number"
            }
        }
        if(_fatTextState.value.isNotEmpty()) {
            try {
                BigDecimal(_fatTextState.value)
            } catch (e: NumberFormatException) {
                if (error.isNotEmpty()) {
                    error += "\n"
                }
                error += "Fat is not a number"
            }
        }
        if(_proteinTextState.value.isNotEmpty()) {
            try {
                BigDecimal(_proteinTextState.value)
            } catch (e: NumberFormatException) {
                if (error.isNotEmpty()) {
                    error += "\n"
                }
                error += "Protein is not a number"
            }
        }
        if(_fiberTextState.value.isNotEmpty()) {
            try {
                BigDecimal(_fiberTextState.value)
            } catch (e: NumberFormatException) {
                if (error.isNotEmpty()) {
                    error += "\n"
                }
                error += "Fiber is not a number"
            }
        }
        if(_carbohydratesTextState.value.isNotEmpty()) {
            try {
                BigDecimal(_carbohydratesTextState.value)
            } catch (e: NumberFormatException) {
                if (error.isNotEmpty()) {
                    error += "\n"
                }
                error += "Carbohydrates is not a number"
            }
        }
        if(_caloriesTextState.value.isNotEmpty()) {
            try {
                BigDecimal(_caloriesTextState.value)
            } catch (e: NumberFormatException) {
                if (error.isNotEmpty()) {
                    error += "\n"
                }
                error += "Calories is not a number"
            }
        }

        return error
    }

    fun createRecipe(
        onSuccess: (recipe: Recipe) -> Unit,
    ) {
        val error = validate()
        if (error.isNotEmpty()) {
            _errorState.value = ErrorState.Error(error)
            return
        }

        viewModelScope.launch {
            _loadingState.value = true

            try {
                val recipe = Recipe()
                recipe.id = BigDecimal(Random.nextInt(from = 100000, until = Int.MAX_VALUE))
                recipe.name = _nameTextState.value

                val section = Section()
                val components: MutableList<Component> = mutableListOf()
                _componentsTextState.value
                    .split("\n")
                    .forEach {
                        val component = Component()
                        component.rawText = it.substring("\u2022 ".length)
                        components.add(component)
                    }

                section.setComponents(components)
                recipe.setSections(mutableListOf(section))

                val instructions: MutableList<Instruction> = mutableListOf()
                _instructionsTextState.value
                    .split("\n")
                    .forEach {
                        val instruction = Instruction()
                        instruction.displayText = it.substring("\u2022 ".length)
                        instructions.add(instruction)
                    }

                recipe.setInstructions(instructions)

                val nutrition = Nutrition()
                nutrition.sugar = BigDecimal(_sugarTextState.value)
                nutrition.fat = BigDecimal(_fatTextState.value)
                nutrition.protein = BigDecimal(_proteinTextState.value)
                nutrition.fiber = BigDecimal(_fiberTextState.value)
                nutrition.carbohydrates = BigDecimal(_carbohydratesTextState.value)
                nutrition.calories = BigDecimal(_caloriesTextState.value)

                recipe.nutrition = nutrition

                val response = repository.createRecipe(recipe)

                onSuccess(recipe)

                clear()
            } catch (e: Exception) {
                _errorState.value = ErrorState.Error(e.message.toString())
            }

            _loadingState.value = false
        }
    }

    private fun clear() {
        _nameTextState.value = ""
        _componentsTextState.value = ""
        _instructionsTextState.value = ""
        _sugarTextState.value = ""
        _fatTextState.value = ""
        _proteinTextState.value = ""
        _fiberTextState.value = ""
        _carbohydratesTextState.value = ""
        _caloriesTextState.value = ""
    }
}