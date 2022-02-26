package com.mlpadilla.a100doorskata

class A100DoorsInARow {
    var doors: List<Door> = mutableListOf<Door>().apply {
        for (i in 0..99) add(Door(state = DoorState.CLOSED))
    }
        private set

    fun pass(doorsTransformation: DoorsTransformation) {
        doors = doorsTransformation(doors)
    }
}
