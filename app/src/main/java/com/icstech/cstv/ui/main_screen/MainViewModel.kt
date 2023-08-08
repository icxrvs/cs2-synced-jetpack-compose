package com.icstech.cstv.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icstech.cstv.data.MatchItem
import com.icstech.cstv.data.repository.MatchRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = MatchRepository()
    private val _matches = MutableLiveData<List<MatchItem>>()
    val matches: LiveData<List<MatchItem>> = _matches
    val isLoading = MutableLiveData(false)

    fun fetchMatches() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val matches = repository.getMatches()
                _matches.value = matches.filter { it.opponents?.isNotEmpty() == true}.sortedBy { it.begin_at }.sortedByDescending { it.status }
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
               println(e)
            }
        }
    }
}