package com.mlpadilla.a100doorskata

data class Door (
    val state: DoorState
)

fun Door.toggle(): Door =
    when(state) {
        DoorState.OPEN -> Door(state = DoorState.CLOSED)
        DoorState.CLOSED -> Door(state = DoorState.OPEN)
    }

enum class DoorState {
    CLOSED,
    OPEN
}
