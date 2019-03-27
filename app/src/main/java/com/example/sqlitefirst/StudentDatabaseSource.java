package com.example.sqlitefirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class StudentDatabaseSource {

    StudentDatabaseHelper studentDatabaseHelper;
    StudentModel studentModel;
    SQLiteDatabase sqLiteDatabase;

    public StudentDatabaseSource(Context context){
        studentDatabaseHelper = new StudentDatabaseHelper(context);


    }

    public void open(){
        sqLiteDatabase = studentDatabaseHelper.getWritableDatabase();
    }

    public void close(){
        studentDatabaseHelper.close();
    }

    public boolean addStudent(StudentModel studentModel){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDatabaseHelper.COL_NAME,studentModel.getName());
        contentValues.put(StudentDatabaseHelper.COL_ADDRESS,studentModel.getAddress());
        contentValues.put(StudentDatabaseHelper.COL_AGE,studentModel.getAge());
        Long insertedRow = sqLiteDatabase.insert(StudentDatabaseHelper.STUDENT_TABLE,null,contentValues);
        this.close();


        if(insertedRow>0){
            return  true;
        }
        else
            return false;


    }


}
