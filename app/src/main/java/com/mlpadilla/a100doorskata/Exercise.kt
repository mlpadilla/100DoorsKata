package com.mlpadilla.a100doorskata

class Exercise(
    doors: A100DoorsInARow
) {
    init {
        doors.pass(openAllDoorsTransformation)
        doors.pass(closeEverySecondDoorTransformation)
        for (i in 2..99) {
            doors.pass(identityDoorsTransformation)
        }
    }
}

private val identityDoorsTransformation: DoorsTransformation = { it }
