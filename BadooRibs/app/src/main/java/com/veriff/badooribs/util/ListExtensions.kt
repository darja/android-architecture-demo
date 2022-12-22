package com.veriff.badooribs.util

import java.util.*

fun <T> linkedListOf(vararg items: T): LinkedList<T> {
    val list = LinkedList<T>()
    list.addAll(items)
    return list
}

fun <T> LinkedList<T>.replaceLastBy(item: T): LinkedList<T> {
    this.removeLast()
    this.addLast(item)
    return this
}

fun <T> LinkedList<T>.replaceAtPosition(position: Int, items: List<T>): LinkedList<T> {
    this.removeAt(position)
    this.addAll(position, items)
    return this
}

fun <T> LinkedList<T>.append(item: T): LinkedList<T> {
    this.add(item)
    return this
}