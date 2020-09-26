package com.example.todolist

class Task {

    var id: Int = 0
    var name : String = ""
    var description: String = ""
    var priority: Int = 0

    constructor(name:String, description:String, priority:Int){
        this.name = name
        this.description = description
        this.priority = priority
    }

    constructor(){}
}