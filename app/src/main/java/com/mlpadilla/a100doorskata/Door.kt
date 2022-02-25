package com.mlpadilla.a100doorskata

data class Door (
    val state: DoorState
)

enum class DoorState {
    CLOSED,
    OPEN
}
