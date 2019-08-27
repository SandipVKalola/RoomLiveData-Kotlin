package com.sk.roomlivedata_kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sk.roomlivedata_kotlin.R
import com.sk.roomlivedata_kotlin.model.Student
import kotlinx.android.synthetic.main.list_student.view.*

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private lateinit var studentList: List<Student>


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(p0.context).inflate(R.layout.list_student, null)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return if (::studentList.isInitialized)
            studentList.size
        else 0

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.bindItems(studentList[index])
    }

    fun setStudentData(student: List<Student>) {
        studentList = student
        notifyDataSetChanged()
    }

    class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bindItems(student: Student) {
            itemView.txtName.text = "Name: ".plus(student.name).plus("-").plus(student.id)
            itemView.txtCityName.text = "City: ".plus(student.cityName)
            itemView.txtAge.text = "Age: ".plus(student.age.toString())
        }

    }
}