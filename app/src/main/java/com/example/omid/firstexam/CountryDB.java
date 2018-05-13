package com.example.omid.firstexam;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.omid.firstexam.POJO.Country;

@Database(entities = {Country.class},version = 1)
public abstract class CountryDB extends RoomDatabase
{

    public abstract DAO dao();

}