package com.example.todolist

import android.content.*
import android.database.sqlite.*
import android.widget.Toast

const val DATABASE = "MyDB"
const val TABLE_PROJECTS = "Projects"
const val TABLE_TASKS = "Tasks"
const val COL_NAME = "name"
const val COL_ID = "id"
const val COL_PROJECT_ID = "project_id"
const val COL_DESCRIPTION = "description"
const val COL_PRIORITY = "priority"


class DatabaseHandler(private var context: Context) : SQLiteOpenHelper(context,
    DATABASE,null,1){
    override  fun onCreate(db: SQLiteDatabase?){
        val createProjectTable = "CREATE TABLE" + TABLE_PROJECTS +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME +" VARCHAR(256)";

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

        var result = db.insert(TABLE_PROJECTS, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

    }

    fun insertTaskData(task : Task, projectID: Int){
        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COL_NAME, task.name)
        cv.put(COL_PROJECT_ID, projectID)
        cv.put(COL_DESCRIPTION, task.description)
        cv.put(COL_PRIORITY, task.priority)

        var result = db.insert(TABLE_PROJECTS, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun getProjects() : MutableList<Project>{
        var list : MutableList<Project> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PROJECTS"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var project = Project()
                project.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                project.name = result.getString(result.getColumnIndex(COL_NAME))
                list.add(project)
            }while(result.moveToNext())
        }

        return list
    }

    fun deleteProject(_id:Int){
        val db = this.writableDatabase
        db.delete(
            TABLE_PROJECTS,
            COL_ID, arrayOf(_id.toString()))
        db.close()
    }

    fun getTask(projectID:Int) : MutableList<Task>{
        var list : MutableList<Task> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_TASKS WHERE $COL_PROJECT_ID IS $projectID"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var task = Task()
                task.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                task.name = result.getString(result.getColumnIndex(COL_NAME))
                task.description = result.getString(result.getColumnIndex(COL_DESCRIPTION))
                task.priority = result.getString(result.getColumnIndex(COL_PRIORITY)).toInt()

                list.add(task)
            }while(result.moveToNext())
        }

        return list
    }

    fun deleteTask(_id:Int){
        val db = this.writableDatabase
        db.delete(
            TABLE_TASKS,
            COL_ID, arrayOf(_id.toString()))
        db.close()
    }
}