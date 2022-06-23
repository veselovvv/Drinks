package com.veselovvv.drinks.core

interface Object<T, M : Mapper> {
    fun map(mapper: M): T
}