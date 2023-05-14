package com.nirwashh.rickandmortyapp.core.utils

interface Mapper<R, S> {

    fun map(source: S): R

    interface Unit<S> : Mapper<kotlin.Unit, S>
}