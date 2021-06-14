@file:JvmName("Main")
package dev.maow.furasm

import dev.maow.furasm.lang.*
import dev.maow.furasm.lang.Opcode.*
import dev.maow.furasm.util.scope.*
import java.io.File

private val whitespace = Regex("\\s+")

fun main(args: Array<String>) {
    if (args.isEmpty())
        error("[err] No '.fur' files specified")
    args.map(::File).forEach { file ->
        require(file.exists()) {
            "[err] Specified file '${file.name}' does not exist"
        }
        val instructions = file.useLines { lines ->
            lines
                .filter(String::isNotBlank)
                .filter { !it.startsWith(';') }
                .map(String::trim)
                .mapTo(mutableListOf(), ::instruction)
        }
        println("[info] Running '${file.name}'")
        ExecutionScope(Program(instructions)).execute {
            when(instruction.opcode) {
                PET -> set { value }
                PAW -> calc { it + value }
                BOP -> calc { it - value }
                LIK -> calc { it * value }
                KIS -> calc { it / value }
                BTE -> calc { it % value }
                CYT -> comp(::isGreater)
                WAG -> comp(::isEqual)
                PNC -> { push(pointer++); jump(value - 1) }
                WIG -> jump(value)
                NUZ -> jump(pop())
                PAT -> branch(::isZero) { pointer++ }
                YIF -> pointer = -1
            }
        }
    }
}

private fun instruction(line: String) =
    line.substringBefore(';')
        .split(whitespace)
        .run {
            Instruction(
                Opcode.valueOf(this[0].uppercase()),
                drop(1).map(::argument))
        }

private fun argument(s: String) =
    Argument(s, when {
        s.toIntOrNull() != null  -> Argument.Type.INT
        else                     -> Argument.Type.REGISTER
    })