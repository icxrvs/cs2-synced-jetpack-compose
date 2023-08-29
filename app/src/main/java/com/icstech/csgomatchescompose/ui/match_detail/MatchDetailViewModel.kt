package com.icstech.csgomatchescompose.ui.match_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icstech.csgomatchescompose.data.MatchItem
import com.icstech.csgomatchescompose.data.OpponentXX
import com.icstech.csgomatchescompose.data.repository.MatchRepository
import kotlinx.coroutines.launch

class MatchDetailViewModel: ViewModel()  {
    private val repository = MatchRepository()
    private val _match = MutableLiveData<MatchItem>()
    val match: LiveData<MatchItem> = _match
    val isLoading = MutableLiveData(false)
    val isLoadingOpponents = MutableLiveData(false)

    private val _opponents = MutableLiveData<OpponentXX>()
    val opponents: LiveData<OpponentXX> = _opponents

    fun fetchMatch(matchId: String?) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val match = repository.getMatchDetails(matchId)
                _match.value = match
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
                println(e)
            }
        }
    }

    fun fetchOpponents(matchId: String?){
        isLoadingOpponents.value = true
        viewModelScope.launch {
            try {
                val opponents = repository.getOpponents(matchId)
                _opponents.value = opponents
                isLoadingOpponents.value = false
            } catch (e: Exception) {
                isLoadingOpponents.value = false
                println(e)
            }
        }
    }
}