package ru.evgenykuzakov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.evgenykuzakov.database.dao.CourseDao
import ru.evgenykuzakov.database.model.CourseEntity


@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
abstract class FavoriteCoursesDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}