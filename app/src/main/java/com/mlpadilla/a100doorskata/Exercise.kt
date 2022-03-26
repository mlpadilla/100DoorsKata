package com.mlpadilla.a100doorskata

class Exercise(
    doors: A100DoorsInARow
) {
    init {
        doors.pass(openAllDoorsTransformation)
        doors.pass(closeEverySecondDoorTransformation)
        doors.pass(toggleEveryThirdDoorTransformation)
        for (i in 3..99) {
            doors.pass(toggleEveryXDoorTransformation(x = i + 1))
        }
    }
}
