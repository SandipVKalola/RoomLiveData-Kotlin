package com.sk.roomlivedata_kotlin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Simple Student table with column name
 *
 * @Entity annotation to tell Room to use the current class as a database table.
 *
 * Student Indicates name of Table
 *
 *
 */
@Entity
class Student {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
    var cityName: String = ""
    var age: Int = 0

}