package com.example.swifty_component.models

data class CursusUser(
    val begin_at: String,
    val cursus: Cursus,
    val cursus_id: Int,
    val end_at: Any,
    val grade: Any,
    val has_coalition: Boolean,
    val id: Int,
    val level: Double,
    val skills: List<Skill>,
    val user: UserX
)