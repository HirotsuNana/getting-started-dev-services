package org.acme

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity

@Entity
    class Users: PanacheEntity() {
    lateinit var name: String
}