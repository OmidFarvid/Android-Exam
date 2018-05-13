package com.example.omid.firstexam;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.omid.firstexam.POJO.Country;

import java.util.List;

@Dao
public interface DAO
{
    @Insert
    public void addCountry(Country newCountry);

    @Query("select * from Country")
    public List<Country> getCountries();

    @Query("Delete from Country")
    public void deleteCountries();

}
