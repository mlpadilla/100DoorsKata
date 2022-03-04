package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DoorTest: BehaviorSpec({
    given("an open door") {
        val door = Door(state = DoorState.OPEN)
        `when`("toggling it") {
            val toggledDoor = door.toggle()
            then("the door is closed") {
                toggledDoor.state shouldBe DoorState.CLOSED
            }
        }
    }
    given("a closed door") {
        val door = Door(state = DoorState.CLOSED)
        `when`("toggling it") {
            val toggledDoor = door.toggle()
            then("the door is open") {
                toggledDoor.state shouldBe DoorState.OPEN
            }
        }
    }
})
