package com.ale.hruser.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_role")
data class Role constructor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var roleName: String
): Serializable {

}