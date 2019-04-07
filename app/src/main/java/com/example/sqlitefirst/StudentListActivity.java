package com.example.sqlitefirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListActivity extends Activity {

    ListView listView;
    StudentDatabaseSource source;
    ArrayList<StudentModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView = findViewById(R.id.ListView);

        source = new StudentDatabaseSource(this);

        arrayList = source.getAllStudent();

        Toast.makeText(StudentListActivity.this, source.getAllStudent().size()+"gfg", Toast.LENGTH_LONG).show();
        // StudentAdapter studentAdapter = new StudentAdapter(this,source.getAllStudent());
        StudentAdapter studentAdapter = new StudentAdapter(this,arrayList);

        listView.setAdapter(studentAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            StudentModel studentModel = arrayList.get(position);

                Intent intent = new Intent(StudentListActivity.this,MainActivity.class);
                intent.putExtra("STUDENT",studentModel);
                startActivity(intent);

            }
        });
    }
}
