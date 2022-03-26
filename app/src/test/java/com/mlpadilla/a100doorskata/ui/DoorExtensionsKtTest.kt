package com.mlpadilla.a100doorskata.ui

import com.mlpadilla.a100doorskata.Door
import com.mlpadilla.a100doorskata.DoorState
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DoorExtensionsKtTest : BehaviorSpec({
    given("an open door") {
        val openDoor = Door(state = DoorState.OPEN)
        `when`("representing it as a char") {
            val doorChar = openDoor.toChar()
            then("the resulting char is '@'") {
                doorChar shouldBe '@'
            }
        }
    }

    given("a closed door") {
        val openDoor = Door(state = DoorState.CLOSED)
        `when`("representing it as a char") {
            val doorChar = openDoor.toChar()
            then("the resulting char is '#'") {
                doorChar shouldBe '#'
            }
        }
    }
})
