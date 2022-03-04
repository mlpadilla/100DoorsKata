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
