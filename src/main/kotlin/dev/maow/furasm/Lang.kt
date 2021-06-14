package dev.maow.furasm

class Instruction(val opcode: Opcode,
                       val arguments: List<String>) {
    private var index = 0

    fun argument(): String {
        val argument = arguments[index]
        index++
        return argument
    }

    fun reset() {
        index = 0
    }
}

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
    ;

    companion object {
        operator fun invoke(name: String) = valueOf(name.uppercase())
    }
}

open class Register(open var value: Int = 0)

class MewRegister : Register() {
    override var value = 0
        get() = readLine()?.toIntOrNull() ?: field
        set(value) {
            field = value
            print(value.toChar())
        }
}

class DmwRegister : Register() {
    override var value = 0
        set(value) {
            field = value
            print(value)
        }
}