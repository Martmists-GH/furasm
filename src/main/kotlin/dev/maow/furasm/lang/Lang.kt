package dev.maow.furasm.lang

class Instruction(val opcode: Opcode,
                  val arguments: List<Argument>)

enum class Opcode(val arguments: Int) {
    PET(2),
    PAW(2),
    BOP(2),
    LIK(2),
    KIS(2),
    BTE(2),
    CYT(3),
    WAG(3),
    PNC(1),
    WIG(1),
    NUZ(0),
    PAT(1),
    YIF(0)
}

class Argument(val value: String,
               val type: Type) {
    fun asInt(): Int {
        require(type == Type.INT) {
            "[err] Cannot convert non-integer argument value to integer for '$value'."
        }
        return value.toInt()
    }

    fun asRegister(program: Program): Register {
        require(type == Type.REGISTER) {
            "[err] Cannot convert non-register argument value to register for '$value'."
        }
        return program.registers[value]
            ?: error("[err] Invalid register '$value'")
    }

    enum class Type {
        INT,
        REGISTER
        ;
    }
}

open class Register(open var value: Int = 0)

fun register(name: String) = name to Register()