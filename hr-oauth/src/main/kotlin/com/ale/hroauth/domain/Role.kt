package com.ale.hroauth.domain

import java.io.Serializable

data class Role constructor (
    var id: Long,
    var roleName: String
): Serializable