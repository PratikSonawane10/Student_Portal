package com.example.student_port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Add_For_Faculty  extends Activity implements View.OnClickListener{
    private static Button addStudent;
    private static Button addBooks;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_for_faculty);

        addStudent=(Button) findViewById(R.id.btnStudentAdd);
        addBooks=(Button) findViewById(R.id.btnBookAdd);

        addStudent.setOnClickListener(this);
        addBooks.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStudentAdd){
            Intent gotoAddStudent = new Intent(Add_For_Faculty.this,AddStudent.class);
            startActivity(gotoAddStudent);
        }else if(v.getId() == R.id.btnBookAdd){
            Intent gotoAddBook = new Intent(Add_For_Faculty.this,FileUpload.class);
            startActivity(gotoAddBook);
        }

    }
}