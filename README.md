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

### Language Guide
#### Program Quickstart
1. Create a new file ending with '.fur'
2. Write your script.
3. Run it with the interpreter (view interpreter guide above for more information).
#### Instructions
Each program contains a set of instructions.
These instructions perform different actions.<br>
Instructions can be written like so: `<opcode> <arguments>`

**Example:** `pet OWO 5` *Set the MEW register to 5.*

*View the opcode listing near the bottom for more information on the different types of instructions.*
#### Comments
Comments are lines that are skipped by the interpreter.<br>
They are useful for documenting your code.

- In an empty line: &nbsp;&nbsp;&nbsp;&nbsp;
`; My comment`
- After an instruction:
`pet MEW 10; My comment`
#### Macros
Macros are instructions that create other instructions, 
they are used to reduce the amount of lines in a script,
while also having the same amount of instructions as a script without said lines.

They always start with `@`, and can take a type-less value with `=`.

**Example:** `@print = OwO, What's This?` *Print "OwO, What's This?" to the console.*

## To-Be-Implemented
- **Labels**, label an instruction to give it a pounce-able name (alias for instruction pointers)
- ~~**Constants**, use string literals as a shortcut for mass-petting (alias for per-character petting)~~
  - *Implemented as macros.*
- **BEP Meta-Register**, the value of it is always the current instruction pointer
- **Instruction modification instructions.**

## Specification
**Syntax**
- An instruction starts with an opcode, followed by the instruction's arguments.
- A newline signals an instruction's ending.
- Comments come after a semicolon at the end of a line.
- Int literals cannot contain any spaces in them.
- Macros start with a '@', and are followed by their names.
- Macro values (optional) start with '=' and are followed by *literally anything.*
- Macro names and values are trimmed for whitespace before and after their text values.

**Semantics**
- A program starts at the first instruction (index 0); however, jumps are 1-indexed.
- All numbers are 32-bit integers. (-2147483647 -> 2147483647)
- Registers can be used as arguments, and if so, their value will be set/gotten.
- Opcodes and register IDs are not case-sensitive.
- The only other valid value are int literals.
- Registers are initialized at 0.
- Macros can contain any value and are processed individually.

**Conventions**
- A FurASM file ends with '.fur'
- Opcodes should be in lowercase.
- Register IDs should be in uppercase.
- There should be a single space on each side of the '=' in a macro.

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

**Macros**
```
@print - Print a series of characters to the terminal via 'pet MEW'.
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