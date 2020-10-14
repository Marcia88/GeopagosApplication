package com.example.android.geopagosapplication.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}