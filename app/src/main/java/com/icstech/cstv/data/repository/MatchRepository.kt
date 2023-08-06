package com.icstech.cstv.data.repository


import com.icstech.cstv.data.MatchItem

class MatchRepository {
    private val creditCardService = RetrofitInstance.matchService


    suspend fun getCreditCards(): List<MatchItem> {
        return creditCardService.getMatches()
    }
}