package com.icstech.cstv.ui.match_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icstech.cstv.data.MatchItem
import com.icstech.cstv.data.repository.MatchRepository
import kotlinx.coroutines.launch

class MatchDetailViewModel: ViewModel()  {
    private val repository = MatchRepository()
    private val _match = MutableLiveData<MatchItem>()
    val match: LiveData<MatchItem> = _match
    val isLoading = MutableLiveData(false)

    fun fetchMatch(matchId: String?) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val match = repository.getMatch(matchId)
                _match.value = match
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
                println(e)
            }
        }
    }
}