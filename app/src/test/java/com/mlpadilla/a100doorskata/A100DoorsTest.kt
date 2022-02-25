package com.mlpadilla.a100doorskata

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class A100DoorsTest: BehaviorSpec ({
    given("a hundred doors in a row") {
        val a100doors = A100DoorsInARow()
        a100doors.size shouldBe 100
        then("they are all closed") {
            for(door in a100doors) {
                door.state shouldBe DoorState.CLOSED
            }
        }
    }
})