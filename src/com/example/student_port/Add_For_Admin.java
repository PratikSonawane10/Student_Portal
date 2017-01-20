package com.example.student_port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Add_For_Admin extends Activity implements View.OnClickListener{
    private static Button addStudent;
    private static Button addFaculty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_for_admin);

        addStudent=(Button) findViewById(R.id.btnStudentAdd);
        addFaculty=(Button) findViewById(R.id.btnFacultyAdd);

        addStudent.setOnClickListener(this);
        addFaculty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStudentAdd){
            Intent gotoAddStudent = new Intent(Add_For_Admin.this,AddStudent.class);
            startActivity(gotoAddStudent);
        }else if(v.getId() == R.id.btnFacultyAdd){
            Intent gotoAddFaculty = new Intent(Add_For_Admin.this,AddFaculty.class);
            startActivity(gotoAddFaculty);
        }

    }


}