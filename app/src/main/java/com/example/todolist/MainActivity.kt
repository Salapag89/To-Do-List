package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    var name : String = ""
    lateinit var database : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        database = DatabaseHandler(context)

        var projectButton = findViewById<Button>(R.id.projectButton)

        projectButton.setOnClickListener {
            var builder = AlertDialog.Builder(context)
            builder.setTitle("Create Project")

            var input = EditText(context)

            builder.setView(input)

            builder.setPositiveButton("OK", DialogInterface.OnClickListener() { dialog, id ->
                name = input.toString()

                var project: Project = Project(name)
                database.insertProjectData(project)

            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener() { dialog, id ->
                dialog.cancel()
            })

            val alert = builder.create()

            alert.show()
        }

    }

}
