package dev.maow.furasm.util

typealias Stack<T> = ArrayDeque<T>

fun <T> stackOf(vararg elements: T) = Stack(elements.toList())

fun <T> Stack<T>.push(element: T) = addLast(element)

fun <T> Stack<T>.pop() = removeLastOrNull() ?: error("[err] Stack underflow")