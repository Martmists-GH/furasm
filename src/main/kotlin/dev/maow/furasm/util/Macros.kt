package dev.maow.furasm.util

import dev.maow.furasm.lang.Instruction

inline fun macro(line: String,
                 block: (String, String?) -> List<Instruction>) =
    with(line.drop(1)) {
        if (contains('='))
            block(substringBefore('=').trim(),
                substringAfter('=').trim())
        else
            block(this.trim(), null)
    }