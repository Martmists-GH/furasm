package dev.maow.furasm.util.scope

import dev.maow.furasm.lang.*
import dev.maow.furasm.util.pop
import dev.maow.furasm.util.push


class InstructionScope(val instruction: Instruction,
                       private val program: Program) {
    private var index = 0

    private val argument: Argument
        get() = instruction.arguments[index++]

    val value: Int
        get() {
            argument.run {
                return when (type) {
                    Argument.Type.INT      -> value.toInt()
                    Argument.Type.REGISTER -> asRegister(program).value
                }
            }
        }

    val register: Register
        get() = argument.asRegister(program)

    var pointer: Int
        set(value) { program.pointer = value }
        get() = program.pointer

    inline fun inst(block: () -> Unit) {
        block()
        pointer++
    }

    inline fun set(block: () -> Int) = inst {
        register.value = block()
    }

    inline fun calc(block: (Int) -> Int) = inst {
        register.apply {
            value = block(value)
        }
    }

    inline fun branch(predicate: () -> Boolean, block: () -> Unit) = inst {
        if (predicate()) block()
    }

    inline fun comp(predicate: () -> Boolean) = inst {
        if (predicate()) register.value = 0
    }

    fun jump(address: Int) = inst {
        program.pointer = address
    }

    fun push(element: Int) = program.stack.push(element)
    fun pop() = program.stack.pop()

    fun isZero() = register.value == 0
    fun isGreater() = value > value
    fun isEqual() = value == value
}