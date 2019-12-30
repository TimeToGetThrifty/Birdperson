package com.greenback.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.greenback.filters.JwtAuthenticationFilter
import com.greenback.filters.JwtAuthorizationFilter
import com.greenback.services.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val userService: CustomUserDetailsService, private val objectMapper: ObjectMapper) :
    WebSecurityConfigurerAdapter() {

    @Value("\${jwt.secret}")
    private lateinit var jwtSecret: String

    @Throws(Exception::class)
    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/greenback/api/userInfo").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(JwtAuthenticationFilter(authenticationManager(), objectMapper, jwtSecret))
            .addFilter(JwtAuthorizationFilter(authenticationManager(), jwtSecret))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}