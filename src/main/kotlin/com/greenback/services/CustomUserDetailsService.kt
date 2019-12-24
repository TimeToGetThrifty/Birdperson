package com.greenback.services

import com.greenback.repositories.UserInfoRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userInfoRepository: UserInfoRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val userInfo =
            userInfoRepository.findByUserName(username ?: throw UsernameNotFoundException("User name is required"))
        return object : UserDetails {
            override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                return mutableListOf(object : GrantedAuthority {
                    override fun getAuthority(): String = userInfo.role
                })
            }

            override fun isEnabled(): Boolean {
                return userInfo.active
            }

            override fun getUsername(): String {
                return userInfo.userName
            }

            override fun isCredentialsNonExpired(): Boolean {
                return userInfo.active
            }

            override fun getPassword(): String {
                return userInfo.password
            }

            override fun isAccountNonExpired(): Boolean {
                return userInfo.active
            }

            override fun isAccountNonLocked(): Boolean {
                return userInfo.active
            }
        }
    }
}