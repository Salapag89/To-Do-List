package com.example.todolist.model

import java.util.*

class User {
    var dayOff: Date = Date()
    val currentProjects = List(3) {Project("",0)}
}