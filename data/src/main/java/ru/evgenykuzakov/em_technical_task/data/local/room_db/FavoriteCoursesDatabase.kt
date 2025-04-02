package ru.evgenykuzakov.em_technical_task.data.local.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.evgenykuzakov.em_technical_task.data.local.model.CourseEntity


@Database(entities = [CourseEntity::class], version = 1)
abstract class FavoriteCoursesDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}