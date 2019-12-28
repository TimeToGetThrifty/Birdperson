package com.greenback.filters

import com.greenback.constants.SecurityConstants
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.util.StringUtils
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.streams.toList

class JwtAuthorizationFilter(authenticationManager: AuthenticationManager, private val jwtSecret: String) :
    BasicAuthenticationFilter(authenticationManager) {

    private val log: Logger = LoggerFactory.getLogger(JwtAuthorizationFilter::class.java)

    @Throws(IOException::class, ServletException::class)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authentication = getAuthentication(request)
        if (authentication == null) {
            filterChain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(SecurityConstants.TOKEN_HEADER)
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                val signingKey = jwtSecret.toByteArray()

                val parsedToken = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token.replace("Bearer ", ""))

                val username = parsedToken
                    .body
                    .subject

                val authorities = (parsedToken.body["role"] as List<*>).stream()
                    .map { authority ->
                        authority as Map<String, String>
                        SimpleGrantedAuthority(authority["authority"])
                    }
                    .toList()

                if (!StringUtils.isEmpty(username)) {
                    return UsernamePasswordAuthenticationToken(username, null, authorities)
                }
            } catch (exception: Exception) {
                log.warn("Exception to authenticate: {}", exception.message)
            }
        }

        return null
    }

}