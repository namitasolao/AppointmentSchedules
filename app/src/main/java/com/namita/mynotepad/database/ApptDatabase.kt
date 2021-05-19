package com.namita.mynotepad.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Appointments::class] , version = 1,exportSchema = false)
@TypeConverters(Converters::class)

public abstract class ApptDatabase : RoomDatabase(){

    abstract val appointmentsDAO : AppointmentsDAO

    companion object{

        @Volatile     //The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory
        var INSTANCE : ApptDatabase? = null     //variable will keep a reference to the database, when one has been created

        fun getInstance(context : Context) : ApptDatabase{

            //synchronized means that only one thread of execution at a time can enter this block of code,
            // which makes sure the database only gets initialized once
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApptDatabase::class.java,
                        "appointments_tab")
                        .fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance

            }

        }


    }

}

