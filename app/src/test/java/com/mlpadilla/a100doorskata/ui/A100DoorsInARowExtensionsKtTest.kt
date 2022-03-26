package com.mlpadilla.a100doorskata.ui

import com.mlpadilla.a100doorskata.A100DoorsInARow
import com.mlpadilla.a100doorskata.Door
import com.mlpadilla.a100doorskata.DoorState
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.*

class A100DoorsInARowExtensionsKtTest : BehaviorSpec({
    given("a 100 doors in a row") {
        val a100doorsInARow = A100DoorsInARow()
        mockkStatic(Door::toChar)
        every { Door(state = DoorState.CLOSED).toChar() } returns 'a'
        `when`("printing it to a String") {
            val doorsString = a100doorsInARow.toText()
            then("each door is represented as a char") {
                doorsString shouldContain "^[a]+\$".toRegex()
            }
            then("all doors are printed") {
                doorsString.length shouldBe a100doorsInARow.doors.size
            }
        }
        unmockkStatic(Door::toChar)
    }
})
