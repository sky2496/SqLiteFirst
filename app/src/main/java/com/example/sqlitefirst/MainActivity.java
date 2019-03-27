package com.example.sqlitefirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etAddress;
    Button addBtn;
    StudentDatabaseSource studentDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etAge = findViewById(R.id.etAge);
        addBtn = findViewById(R.id.addStudent);

        studentDatabaseSource = new StudentDatabaseSource(this);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel studentModel = new StudentModel(etName.getText().toString(),Integer.valueOf(etAge.getText().toString()),etAddress.getText().toString());

                Boolean status = studentDatabaseSource.addStudent(studentModel);
                if(status==true){
                    Toast.makeText(MainActivity.this,"saved",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"not saved",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
