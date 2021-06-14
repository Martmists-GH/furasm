package dev.maow.furasm.lang

import dev.maow.furasm.util.*

class Program(val instructions: MutableList<Instruction>) {
    var pointer = 0
    val registers = mapOf(
        register("OWO"),
        register("UWU"),
        register("ONO"),
        register("UNU"),

        "MEW" to object: Register() {
            override var value = 0
                get() = readLine()?.toIntOrNull() ?: field
                set(value) {
                    field = value
                    print(value.toChar())
                }
        },

        "DMW" to object: Register() {
            override var value = 0
                get() = readLine()?.toInt() ?: error("[err] Integer input required")
                set(value) {
                    field = value
                    print(value)
                }
        }
    )
    val stack = stackOf<Int>()
}