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

val toggleEveryThirdDoorTransformation: DoorsTransformation = {
    it.mapIndexed { index, door ->
        door.takeUnless { index % 3 == 2 } ?: door.toggle()
    }
}
