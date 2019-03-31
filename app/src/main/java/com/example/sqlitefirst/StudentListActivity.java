package com.example.sqlitefirst;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class StudentListActivity extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView = findViewById(R.id.ListView);


    }
}
