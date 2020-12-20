package com.ale.hrapigatewayzuul.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableResourceServer
class ResourceServerConfig @Autowired constructor(
    val tokenStore: JwtTokenStore
): ResourceServerConfigurerAdapter() {

    val PUBLIC: Array<String> = arrayOf("/hr-oauth/oauth/token")

    val OPERATOR: Array<String> = arrayOf("/hr-worker/**")

    val ADMIN: Array<String> = arrayOf(
        "/hr-payroll/**",
        "/hr-user/**",
        "/actuator/**",
        "/hr-worker/actuator/**",
        "/hr-oauth/actuator/**"
    )

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources?.tokenStore(tokenStore)
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
            ?.antMatchers(*PUBLIC)?.permitAll()
            ?.antMatchers(HttpMethod.GET, *OPERATOR)?.hasAnyRole("OPERATOR", "ADMIN")
            ?.antMatchers(*ADMIN)?.hasRole("ADMIN")
            ?.anyRequest()?.authenticated()

        http?.cors()?.configurationSource(corsConfigurationSource())

    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfig = CorsConfiguration()
        corsConfig.allowedOrigins = listOf("*")
        corsConfig.allowedMethods = listOf("POST", "GET", "PUT", "DELETE", "PATCH")
        corsConfig.allowedHeaders = listOf("Authorization", "Content-Type")
        corsConfig.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return source
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter> {
        val bean: FilterRegistrationBean<CorsFilter> = FilterRegistrationBean(CorsFilter(corsConfigurationSource()))
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}