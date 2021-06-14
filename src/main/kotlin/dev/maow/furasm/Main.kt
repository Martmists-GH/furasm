@file:JvmName("Main")
package dev.maow.furasm

import dev.maow.furasm.Opcode.*
import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty())
        error("[err] No '.fur' files specified.")
    args.map(::File).forEach { file ->
        val instructions = file.useLines { lines ->
            lines
                .map(String::trim)
                .filter(String::isNotBlank)
                .filter { !it.startsWith(';') }
                .mapTo(mutableListOf()) { line ->
                    clean(line)
                        .split(Regex("\\s+"))
                        .run { Instruction(Opcode(first()), drop(1)) }
                }
        }
        println("[info] Running '${file.name}'")
        Program(instructions).next()
    }
}

private fun clean(line: String) =
    if (line.contains(';'))
        line.substring(0 until line.indexOf(';'))
    else
        line

class Program(private val instructions: List<Instruction>) {
    private var pointer = 0
    private val registers = mapOf(
        "OWO" to Register(),
        "UWU" to Register(),
        "ONO" to Register(),
        "UNU" to Register(),

        "MEW" to MewRegister()
    )
    private val stack = ArrayDeque<Int>()

    fun next() {
        if (pointer == -1) {
            println("[info] Program terminated.")
            return
        }
        with(instructions[pointer]) {
            require(opcode.arguments == arguments.size) {
                "[err] Incorrect argument size for opcode $opcode\nEXPECTED: ${opcode.arguments} | ACTUAL: ${arguments.size}"
            }
            execute(this)
            reset()
            next()
        }
    }

    private fun execute(instruction: Instruction) =
        with(instruction) {
            when (opcode) {
                PET -> set { value() }
                PAW -> set { value() + value() }
                BOP -> set { value() - value() }
                LIK -> set { value() * value() }
                KIS -> set { value() / value() }
                BTE -> set { value() % value() }
                CYT -> comp { value() > value() }
                WAG -> comp { value() == value() }
                PNC -> {
                    stack.addLast(pointer + 1)
                    pointer = value()
                }
                WIG -> pointer = value()
                NUZ -> pointer = stack.removeLast()
                PAT -> {
                    if (register().value == 0)
                        pointer++
                    pointer++
                }
                YIF -> pointer = -1
            }
        }

    private fun register(argument: String) = argument
        .run {
            requireNotNull(registers[this]) {
                "[err] Not a valid register: $this"
            }
        }

    private fun Instruction.register() = register(argument())

    private fun Instruction.value() = argument()
        .run {
            toIntOrNull() ?: register(this).value
        }

    private inline fun Instruction.set(block: () -> Int) {
        register().value = block()
        pointer++
    }

    private inline fun Instruction.comp(block: () -> Boolean) {
        if (block()) register().value = 0
        pointer++
    }
}