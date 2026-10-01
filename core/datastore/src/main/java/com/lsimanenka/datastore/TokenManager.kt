package com.lsimanenka.datastore

interface TokenManager {
    fun getToken(): String?
    fun saveToken(token: String)
}