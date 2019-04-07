package com.example.sqlitefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etAddress;
    Button addBtn,showStudent;
    StudentDatabaseSource studentDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etAge = findViewById(R.id.etAge);
        addBtn = findViewById(R.id.addStudent);
        showStudent = findViewById(R.id.showStudent);

        studentDatabaseSource = new StudentDatabaseSource(this);

        final StudentModel studentModel = (StudentModel) getIntent().getSerializableExtra("STUDENT");

        if(studentModel!=null){
            addBtn.setText("Update Student");
            etName.setText(studentModel.getName());
            etAge.setText(studentModel.getAge() + "");
            etAddress.setText(studentModel.getAddress());
        }
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // updated
                if(studentModel!=null){
                    String updatedName = etName.getText().toString();
                    int updatedAge = Integer.valueOf(etAge.getText().toString());
                    String updatedAddress = etAddress.getText().toString();
                    int id = studentModel.getId();

                    StudentModel updatedStudentModel = new StudentModel(id,updatedName,updatedAge,updatedAddress);
                    boolean updatedStatus = studentDatabaseSource.updateStudent(updatedStudentModel);

                    if(updatedStatus) {
                        Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Not Updated Successfully", Toast.LENGTH_SHORT);
                    }

                }

                // insert
                StudentModel studentModel = new StudentModel(etName.getText().toString(),Integer.valueOf(etAge.getText().toString()),etAddress.getText().toString());

                Boolean status = studentDatabaseSource.addStudent(studentModel);
                if(status==true){
                    Toast.makeText(MainActivity.this,"saved",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"not saved",Toast.LENGTH_SHORT).show();


            }
        });

        showStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StudentListActivity.class);
                startActivity(intent);

            }
        });

    }
}
