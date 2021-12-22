package com.app.covidtracker.model.states

data class StatesResponse(
    val states: List<State>,
    val ttl: Int
)