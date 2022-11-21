package com.example.sw_pedrogc.Modelos

data class PersonajesResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)