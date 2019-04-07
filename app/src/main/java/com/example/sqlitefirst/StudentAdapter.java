package com.example.sqlitefirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModel> {


    Context context;
    ArrayList<StudentModel> arrayList;
    public StudentAdapter( Context context, ArrayList<StudentModel> arrayList) {
        super(context, R.layout.single_student_row, arrayList);
        this.context = context;
        this.arrayList = arrayList;

    }



    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View v = layoutInflater.inflate(R.layout.single_student_row,parent,false);
        TextView nameTV = v.findViewById(R.id.name);
        TextView ageTV = v.findViewById(R.id.age);
        TextView addressTV = v.findViewById(R.id.address);

        nameTV.setText(arrayList.get(position).getName());
        ageTV.setText(String.valueOf(arrayList.get(position).getAge()));
        addressTV.setText(arrayList.get(position).getAddress());

        return v;
    }
}
