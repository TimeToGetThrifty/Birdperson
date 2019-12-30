package com.greenback.constants

class SecurityConstants {

    companion object {
        const val AUTH_LOGIN_URL = "/greenback/api/authenticate"
        const val TOKEN_HEADER = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        const val TOKEN_TYPE = "JWT"
        const val TOKEN_ISSUER = "greenback-api"
        const val TOKEN_AUDIENCE = "greenback-web"
    }
}