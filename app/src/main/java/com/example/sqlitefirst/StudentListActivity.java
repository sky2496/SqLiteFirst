package com.example.sqlitefirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        registerForContextMenu(listView);

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu,menu);
//        menu.setHeaderTitle("Delete Student");

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        menu.setHeaderTitle("Delete Student");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo;
        adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.deletemenu){
            boolean status = source.deleteStudent(arrayList.get(adapterContextMenuInfo.position));

            if(status){
                Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"fail to delete",Toast.LENGTH_SHORT).show();
            }
        }


        return super.onContextItemSelected(item);
    }
}
