package com.professorapps.instant.talking.studentportal.model

import android.net.Uri

data class StudentModel(
    val Student_name: String,
    val Student_id:Int,
    val Student_Age: Int,
    var isSelected: Boolean=false
)
