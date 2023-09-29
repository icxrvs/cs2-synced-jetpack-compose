package com.icstech.csgomatchescompose.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.data.repository.MatchRepository
import com.icstech.csgomatchescompose.util.getCurrentDate
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val repository = MatchRepository()
    private val _matches = MutableLiveData<List<MatchItem>>()
    val matches: LiveData<List<MatchItem>> = _matches
    val isLoading = MutableLiveData(false)

    fun onRefreshRequested() {
        fetchMatches(getCurrentDate())
    }

    fun fetchMatches(currentDate: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val matches = repository.getMatches(currentDate)
                _matches.value = matches.filter { it.opponents?.isNotEmpty() == true && it.videogame.id == 3}.sortedBy {it.begin_at}.sortedBy { it.status == "running" || it.status == "fsafsa" }
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
               println(e)
            }
        }
    }
}