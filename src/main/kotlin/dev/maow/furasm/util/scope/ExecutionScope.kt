package dev.maow.furasm.util.scope

import dev.maow.furasm.lang.*

class ExecutionScope(private val program: Program) {
    fun next(): InstructionScope? {
        var instruction: Instruction
        with(program) {
            if (pointer == -1)
                return null
            with(instructions[program.pointer]) {
                require(this.opcode.arguments == arguments.size) {
                    "[err] Incorrect argument size for opcode ${this.opcode}\nEXPECTED: ${this.opcode.arguments} | ACTUAL: ${arguments.size}"
                }
                instruction = this
            }
        }
        return InstructionScope(instruction, program)
    }

    inline fun execute(init: InstructionScope.() -> Unit) {
        var scope = next()
        while (scope != null) {
            scope.init()
            scope = next()
        }
        println("[info] Program terminated")
    }
}