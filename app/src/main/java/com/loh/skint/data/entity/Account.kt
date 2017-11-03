package com.loh.skint.data.entity

import io.requery.*
import java.util.*

@Entity(name = "AccountEntity") interface Account : Persistable {
    @get:[Key Generated] val id: Int
    @get:Column(unique = true) var uuid: UUID
    var name: String
    var balance: String
    var dateCreated: Date
}