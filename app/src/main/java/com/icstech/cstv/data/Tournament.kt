package com.icstech.cstv.data

data class Tournament(
    val begin_at: String,
    val end_at: Any,
    val has_bracket: Boolean,
    val id: Int,
    val league_id: Int,
    val live_supported: Boolean,
    val modified_at: String,
    val name: String,
    val prizepool: Any,
    val serie_id: Int,
    val slug: String,
    val tier: String,
    val winner_id: Any,
    val winner_type: String
)