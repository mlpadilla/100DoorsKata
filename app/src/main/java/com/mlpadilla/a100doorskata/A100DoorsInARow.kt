package com.mlpadilla.a100doorskata

class A100DoorsInARow : List<Door> by buildList(
    builderAction = {
        for (i in 0..99) add(Door(state = DoorState.CLOSED))
    }
) {
    fun pass() {}
}
