package com.icstech.cstv.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icstech.cstv.data.Match
import com.icstech.cstv.data.MatchItem
import com.icstech.cstv.data.repository.MatchRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = MatchRepository()

    private val _matches = MutableLiveData<List<MatchItem>>()
    val matches: LiveData<List<MatchItem>> = _matches

    fun fetchMatches() {
        viewModelScope.launch {
            try {
                val cards = repository.getCreditCards()
                _matches.value = cards
            } catch (e: Exception) {
                   println(e)
            }
        }
    }
}