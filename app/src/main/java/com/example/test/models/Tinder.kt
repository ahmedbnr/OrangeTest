package com.example.test.models

class Tinder(val activity: String?, val type: String?, val participants: Int?, val price: Int?, val link: String?, val key: String?, val accessibility: Int?) {


    override fun toString(): String {
        return "Tinder(activity='$activity)"
    }

    constructor(activity: String?) : this(activity, null, null, null, null, null, null)
}