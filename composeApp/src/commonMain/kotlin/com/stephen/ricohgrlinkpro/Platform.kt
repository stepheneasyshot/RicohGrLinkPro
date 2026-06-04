package com.stephen.ricohgrlinkpro

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform