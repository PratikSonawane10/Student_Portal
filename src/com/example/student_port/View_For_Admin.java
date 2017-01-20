package com.example.student_port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class View_For_Admin extends Activity implements View.OnClickListener {

    private static Button viewStudent;
    private static Button viewFaculty;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_for_admin);

        viewStudent=(Button) findViewById(R.id.btnStudentView);
        viewFaculty=(Button) findViewById(R.id.btnFacultyView);

        viewStudent.setOnClickListener(this);
        viewFaculty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStudentView){
            Intent gotoViewStudent = new Intent(View_For_Admin.this,SemSelectionForStudent.class);
            startActivity(gotoViewStudent);
        }else if(v.getId() == R.id.btnFacultyView){
            Intent gotoViewFaculty = new Intent(View_For_Admin.this,ViewFaculty.class);
            startActivity(gotoViewFaculty);
        }
    }
}