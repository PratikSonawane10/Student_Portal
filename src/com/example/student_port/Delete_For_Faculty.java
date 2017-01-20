package com.example.student_port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Delete_For_Faculty extends Activity implements View.OnClickListener{

    private static Button deleteStudent;
    private static Button deleteBook;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_for_faculty);

        deleteStudent=(Button) findViewById(R.id.btnStudentDelete);
        deleteBook=(Button) findViewById(R.id.btnBookDelete);

        deleteStudent.setOnClickListener(this);
        deleteBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStudentDelete){
            Intent gotoDeleteStudent = new Intent(Delete_For_Faculty.this,DeleteStudent.class);
            startActivity(gotoDeleteStudent);
        }else if(v.getId() == R.id.btnBookDelete){
            Intent gotoDeleteBook = new Intent(Delete_For_Faculty.this,DeleteBook.class);
            startActivity(gotoDeleteBook);
        }

    }
}