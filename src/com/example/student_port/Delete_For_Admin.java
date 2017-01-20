package com.example.student_port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Delete_For_Admin extends Activity implements View.OnClickListener{

    private static Button deleteStudent;
    private static Button deleteFaculty;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_for_admin);

        deleteStudent=(Button) findViewById(R.id.btnStudentDelete);
        deleteFaculty=(Button) findViewById(R.id.btnFacultyDelete);

        deleteStudent.setOnClickListener(this);
        deleteFaculty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStudentDelete){
            Intent gotoAddStudent = new Intent(Delete_For_Admin.this,DeleteStudent.class);
            startActivity(gotoAddStudent);
        }else if(v.getId() == R.id.btnFacultyDelete){
            Intent gotoAddFaculty = new Intent(Delete_For_Admin.this,DeleteFaculty.class);
            startActivity(gotoAddFaculty);
        }

    }
}