package com.example.sqlitefirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

    public boolean updateStudent(StudentModel studentModel){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDatabaseHelper.COL_NAME,studentModel.getName());
        contentValues.put(StudentDatabaseHelper.COL_ADDRESS,studentModel.getAddress());
        contentValues.put(StudentDatabaseHelper.COL_AGE,studentModel.getAge());

        int updatedRow = sqLiteDatabase.update(StudentDatabaseHelper.STUDENT_TABLE,contentValues,studentDatabaseHelper.COL_ID+" =?",new String[]{String.valueOf(studentModel.getId()) });

        this.close();
        if(updatedRow>0){
            return true;
        }
        else{
            return false;
        }

    }

    public ArrayList<StudentModel> getAllStudent(){
        this.open();

        ArrayList<StudentModel> arrayList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(studentDatabaseHelper.STUDENT_TABLE,null,null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_NAME));
                // String name1 = cursor.getString(1);
                int age = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_AGE));
                // int age1 = cursor.getInt(2);
                String address = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_ADDRESS));
                int id = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_ID));

                StudentModel studentModel = new StudentModel(id,name,age,address);

                arrayList.add(studentModel);


            }while ((cursor.moveToNext()));
        }
        return arrayList;
    }

    public boolean deleteStudent(StudentModel model){
        this.open();

        // int deletedRow = sqLiteDatabase.delete(StudentDatabaseHelper.STUDENT_TABLE,studentDatabaseHelper.COL_ID + " =?",new String[]{String.valueOf(studentModel.getId())});

        int deletedRow = sqLiteDatabase.delete(studentDatabaseHelper.STUDENT_TABLE,studentDatabaseHelper.COL_ID + " =?",new String[]{String.valueOf(model.getId())});

        this.close();
        if(deletedRow > 0){
            return true;
        }
        else return false;
    }


}
