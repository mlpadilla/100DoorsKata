package com.mlpadilla.a100doorskata.ui

import com.mlpadilla.a100doorskata.Door
import com.mlpadilla.a100doorskata.DoorState

fun Door.toChar(): Char =
    when (state) {
        DoorState.OPEN -> '@'
        DoorState.CLOSED -> '#'
    }
