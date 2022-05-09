package hu.bme.aut.recipebase.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.recipebase.persistence.AppDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : MainRepository
    private var state = MutableLiveData<Main>()

    init {
        val recipeDao = AppDatabase.getInstance(application).recipeDao()
        repository = MainRepository()
    }
}