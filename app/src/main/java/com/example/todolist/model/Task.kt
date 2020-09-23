package com.example.todolist.model

class Task {

    var id: Int = 0
    var name : String = ""
    var projectID : Int = 0
    var description: String = ""
    var priority: Int = 0

    constructor(name:String, projectID:Int, description:String, priority:Int){
        this.name = name
        this.projectID = projectID
        this.description = description
        this.priority = priority
    }
}