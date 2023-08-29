package com.icstech.csgomatchescompose.data

data class OpponentX(
    val acronym: Any,
    val id: Int,
    val image_url: String,
    val location: String,
    val modified_at: String,
    val name: String,
    val slug: String,
    val players: List<Player>,
)