package com.ale.hrapigatewayzuul.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@RefreshScope
@Configuration
class AppConfig constructor(
    @Value("\${jwt.secret}")
    private val jwtSecret: String
) {
    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val tokenConverter = JwtAccessTokenConverter()
        tokenConverter.setSigningKey(jwtSecret)
        return tokenConverter
    }

    @Bean
    fun tokenStore(): JwtTokenStore {
        return JwtTokenStore(accessTokenConverter())
    }
}