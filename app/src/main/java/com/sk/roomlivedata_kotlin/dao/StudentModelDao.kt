package com.sk.roomlivedata_kotlin.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sk.roomlivedata_kotlin.model.Student

/**
 * @Dao to tell Room that this is a DAO class.
 *
 * @Insert annotation for methods that insert entries into the table.
 *
 * Similarly use @Delete and @Update for deletion and update method respectively.
 *
 * @Query annotation is paired with a method. When the paired method is called, the query gets executed.
 *
 */
@Dao
interface StudentModelDao {
    // Insert Data to student table
    @Insert
    fun insertStudent(student: Student)

    // Delete student based on id
    @Delete
    fun deleteStudent(student: Student)

    // Get all student data
    @Query("SELECT * FROM Student")
    fun getStudent(): LiveData<List<Student>>
}
