package com.example.student_port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class View_For_Faculty extends Activity implements View.OnClickListener {

    private static Button viewStudent;
    private static Button viewBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_for_faculty);

        viewStudent=(Button) findViewById(R.id.btnStudentView);
        viewBook=(Button) findViewById(R.id.btnBookView);

        viewStudent.setOnClickListener(this);
        viewBook.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStudentView){
            Intent gotoViewStudent = new Intent(View_For_Faculty.this,SemSelectionForStudent.class);
            startActivity(gotoViewStudent);
        }else if(v.getId() == R.id.btnBookView){
            Intent gotoSelectSem = new Intent(View_For_Faculty.this,SemSelectionForBook.class);
            startActivity(gotoSelectSem);
        }
    }

}