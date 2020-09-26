package com.example.todolist

import com.example.todolist.Project
import java.util.*

class User {
    var dayOff: Date = Date()
    val currentProjects = List(3) { Project("", 0) }
}