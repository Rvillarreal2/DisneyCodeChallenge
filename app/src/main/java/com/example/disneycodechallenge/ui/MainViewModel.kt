package com.example.disneycodechallenge.ui

import androidx.lifecycle.*
import com.example.disneycodechallenge.data.Customer
import com.example.disneycodechallenge.data.CustomerRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CustomerRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
//    private val allCustomers: MutableLiveData<List<Customer>> = repository.allCustomers.asLiveData() as MutableLiveData<List<Customer>>
//
//    fun getLiveData(): MutableLiveData<List<Customer>> {
//        return allCustomers
//    }

    val getLiveData: LiveData<List<Customer>> = repository.allCustomers.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(customer: Customer) = viewModelScope.launch {
        repository.insert(customer)
    }
}

class MainViewModelFactory(private val repository: CustomerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}