package com.veselovvv.drinks.core

interface Object<T, M : Mapper> {
    fun map(mapper: M): T

    interface ToDb<E, M : Mapper> {
        fun mapWith(mapper: M): E
    }
}