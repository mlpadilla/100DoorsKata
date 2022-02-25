package com.mlpadilla.a100doorskata

class A100DoorsInARow {
    private val _doors = mutableListOf<Door>().apply {
        for (i in 0..99) add(Door(state = DoorState.CLOSED))
    }
    val doors: List<Door> = _doors

    fun pass() {
        for (i: Int in 0 until _doors.size) {
            _doors[i] = _doors[i].copy(state = DoorState.OPEN)
        }
    }
}
