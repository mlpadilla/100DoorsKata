package com.mlpadilla.a100doorskata

typealias DoorsTransformation = (List<Door>) -> List<Door>

val openAllDoorsTransformation: DoorsTransformation = {
    it.map { door -> door.copy(state = DoorState.OPEN) }
}
