package com.example.todolist.viewmodels

import android.content.*
import android.database.sqlite.*
import android.widget.Toast
import com.example.todolist.model.*

const val DATABASE = "MyDB"
const val TABLE_PROJECTS = "Projects"
const val TABLE_TASKS = "Tasks"
const val COL_NAME = "name"
const val COL_ID = "id"
const val COL_COLOR = "color"
const val COL_PROJECT_ID = "project_id"
const val COL_DESCRIPTION = "description"
const val COL_PRIORITY = "priority"


class Database(var context: Context) : SQLiteOpenHelper(context,DATABASE,null,1){
    override  fun onCreate(db: SQLiteDatabase?){
        val createProjectTable = "CREATE TABLE" + TABLE_PROJECTS +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME +" VARCHAR(256)," +
                COL_COLOR +" INTEGER)";

        val createTaskTable = "CREATE TABLE" + TABLE_TASKS +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME +" VARCHAR(256)," +
                COL_PROJECT_ID +" INTEGER," +
                COL_DESCRIPTION +" VARCHAR(256)," +
                COL_PRIORITY +" INTEGER)";

        db?.execSQL(createProjectTable)
        db?.execSQL(createTaskTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertProjectData(project : Project){
        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COL_NAME, project.name)
        cv.put(COL_COLOR, project.color)

        var result = db.insert(TABLE_PROJECTS, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

    }

    fun insertTaskData(task : Task){
        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COL_NAME, task.name)
        cv.put(COL_PROJECT_ID, task.projectID)
        cv.put(COL_DESCRIPTION, task.description)
        cv.put(COL_PRIORITY, task.priority)

        var result = db.insert(TABLE_PROJECTS, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}