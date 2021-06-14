![FurASM](https://raw.githubusercontent.com/MaowImpl/furasm/master/furasm.png)

[![License](https://img.shields.io/github/license/maowimpl/furasm?style=flat-square)](https://github.com/MaowImpl/furasm/blob/master/LICENSE)
[![Version](https://img.shields.io/github/v/release/maowimpl/furasm?style=flat-square)](https://github.com/MaowImpl/furasm/releases)

> A furry-themed assembly language and interpreter written in Kotlin.
> 
> **Inspired by [Furcode](https://github.com/sparksammy/FurcodeJS/), the [Synacor challenge](https://challenge.synacor.com/), and JVM bytecode.**

~~*I spent multiple hours on this. Dear god help me...*~~

#### Logo Assets
| Title | From | Link
| ---  | ---    | ---
| Fox Vector    | *Vecteezy*              | https://www.vecteezy.com/vector-art/95451-free-fox-silhouette-vector
| Public Sans   | *The U.S. Government*   | https://public-sans.digital.gov

### Interpreter Guide

This implementation of the FurASM interpreter contains a `scripts` folder which you can use to test out the language via pre-written FurASM scripts.

**Running the interpreter on one or more files**
<br>
`furasm "scripts/HelloWorld.fur" "scripts/Math.fur"`

**Interpreter console**
<br>
`[info]` Info about what the interpreter is doing, such as starting a new program.
<br>
`[err]` &nbsp; An error occurred, and the script was forced to terminate.

## To-Be-Implemented
- Labels, label an instruction to give it a pounce-able name (alias for instruction pointers)
- Constants, use string literals as a shortcut for mass-petting (alias for per-character petting)
- BEP Meta-Register, the value of it is always the current instruction pointer

## Specification
**Syntax**
- An instruction starts with an opcode, followed by the instruction's arguments.
- A newline signals an instruction's ending.
- Comments come after a semicolon at the end of a line.
- Int literals cannot contain any spaces in them.

**Semantics**
- A program starts at the first instruction (index 0); however, jumps are 1-indexed.
- All numbers are 32-bit integers. (0..2147483647)
- Registers can be used as arguments, and if so, their value will be set/gotten.
- Opcodes and register IDs are not case-sensitive.
- The only other valid value are int literals.
- Registers are initialized at 0.

**Conventions**
- A FurASM file ends with '.fur'
- Opcodes should be in lowercase.
- Register IDs should be in uppercase.

**Opcodes**
```
0.  pet = Set register to value.
1.  paw = Add a register and a value together.
2.  bop = Subtract a register and a value.
3.  lik = Multiply a register with a value.
4.  kis = Divide a register with a value.
5.  bte = Get the remainder of a register and a value.
6.  cyt = Set register to 0 if value 1 is greater than value 2.
7.  wag = Set register to 0 if value 1 is equal to value 2.
8.  pnc = Jump to instruction and push the pointer of the next instruction to stack.
9.  wig = Jump to instruction.
10. nuz = Pop stack and set current pointer to returned pointer.
11. pat = Skip next instruction if register is 0.
12. yif = Terminate the program.
```

### Registers

0. OWO
1. UWU
2. ONO
3. UNU

**MEW Meta-Register**
- When setting the value of this register, it outputs the ASCII representation of that value to the terminal.
- When using this register as an argument, it requests a number from the terminal.

**DMW (Direct MEW) Meta-Register**
- When setting the value of this register, it outputs the value.