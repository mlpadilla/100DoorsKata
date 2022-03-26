package com.mlpadilla.a100doorskata.ui

import com.mlpadilla.a100doorskata.A100DoorsInARow
import com.mlpadilla.a100doorskata.Exercise

fun main() {
    val a100Doors = A100DoorsInARow()
    Exercise(doors = a100Doors)

    println(a100Doors.toText())
}
