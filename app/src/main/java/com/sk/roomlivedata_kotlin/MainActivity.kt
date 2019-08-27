package com.sk.roomlivedata_kotlin

import android.app.AlertDialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import com.sk.roomlivedata_kotlin.adapter.StudentAdapter
import com.sk.roomlivedata_kotlin.db.AppDatabase
import com.sk.roomlivedata_kotlin.model.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Declare Database class object
    var appDatabase: AppDatabase? = null
    lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize object of Database class
        appDatabase = AppDatabase.getInstance(this)

        /**
         * Perform action for add student data
         *
         */
        addData.onClick {
            addStudentData()
        }

        /**
         * Perform action for delete
         *
         */
        deleteData.onClick {
            deleteStudent()
        }

    }

    override fun onResume() {
        super.onResume()

        val studentData: LiveData<List<Student>> = appDatabase!!.studentModelDao().getStudent()
        studentData.observe(this,
            Observer<List<Student>> { t ->
                if (t != null) {
                    studentAdapter.setStudentData(t)
                }
            })

        /**
         * Display data on RecyclerView
         *
         */
        RvStudentsList.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        studentAdapter = StudentAdapter()
        RvStudentsList.adapter = studentAdapter
    }


    /**
     * Popup Dialog for Add new student Data
     *
     */
    private fun addStudentData() {

        val dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_addstudent, null)
        val edtName: EditText = dialogLayout.findViewById(R.id.edtName)
        val edtCityName: EditText = dialogLayout.findViewById(R.id.edtCityName)
        var edtAge: EditText = dialogLayout.findViewById(R.id.edtAge)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(dialogLayout)
        dialog.setPositiveButton("Save", null)
        dialog.setNegativeButton("Not Now", null)

        val builder = dialog.show()

        builder.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val studentData = Student()
            studentData.name = edtName.text.toString()
            studentData.age = edtAge.text.toString().toInt()
            studentData.cityName = edtCityName.text.toString()

            appDatabase!!.studentModelDao().insertStudent(studentData)
            builder.dismiss()
            Toast("Added Successfully")
        }

        builder.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            builder.dismiss()

        }
    }

    private fun deleteStudent() {
        val dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_deletestudent, null)
        var edtId: EditText = dialogLayout.findViewById(R.id.edtId)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(dialogLayout)
        dialog.setPositiveButton("Delete", null)
        dialog.setNegativeButton("Not Now", null)

        val builder = dialog.show()

        builder.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val studentData = Student()
            studentData.id = edtId.text.toString().toInt()

            appDatabase!!.studentModelDao().deleteStudent(studentData)
            builder.dismiss()
            Toast("Delete Successfully")
        }

        builder.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            builder.dismiss()

        }
    }
}
