package com.example.swifty_component.models

data class Project(
    val created_at: String,
    val current_team_id: Int,
    val cursus_ids: List<Int>,
    val final_mark: Int,
    val id: Int,
    val marked: Boolean,
    val marked_at: String,
    val occurrence: Int,
    val project: ProjectX,
    val retriable_at: String,
    val status: String,
    val updated_at: String,
    val validated: Boolean
)