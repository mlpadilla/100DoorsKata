package com.mlpadilla.a100doorskata

typealias DoorsTransformation = (List<Door>) -> List<Door>

val openAllDoorsTransformation: DoorsTransformation = {
    it.map { door -> door.copy(state = DoorState.OPEN) }
}

val closeEverySecondDoorTransformation: DoorsTransformation = {
    it.mapIndexed { index, door ->
        door.takeIf { index % 2 == 0 }
            ?: door.copy(state = DoorState.CLOSED)
    }
}

fun toggleEveryXDoorTransformation(x: Int): DoorsTransformation = {
    it.mapIndexed { index, door ->
        door.takeUnless { index % x == x - 1 } ?: door.toggle()
    }
}
