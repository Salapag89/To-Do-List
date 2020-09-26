package com.example.todolist

class Project {

    var id: Int = 0
    var name : String = ""
    var color : Int = 0

    constructor(name:String,color:Int){
        this.name = name
        this.color = color
    }

    constructor(){}
}