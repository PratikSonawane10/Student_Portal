package com.example.student_port;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FacultyIndex extends Activity implements View.OnClickListener {

    private static Button btnAdd;
    private static Button btnView;
    private static Button btnDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_index);

        btnAdd=(Button) findViewById(R.id.btnAddFacultyRights);
        btnView=(Button) findViewById(R.id.btnViewFacultynRights);
        btnDelete=(Button) findViewById(R.id.btnDeleteFacultyRights);

        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAddFacultyRights){
            Intent gotoAddPage = new Intent(FacultyIndex.this,Add_For_Faculty.class);
            startActivity(gotoAddPage);
        }else if(v.getId() == R.id.btnViewFacultynRights){
            Intent gotoViewPage = new Intent(FacultyIndex.this,View_For_Faculty.class);
            startActivity(gotoViewPage);
        }else if(v.getId() == R.id.btnDeleteFacultyRights){
            Intent gotoDeletePage = new Intent(FacultyIndex.this,Delete_For_Faculty.class);
            startActivity(gotoDeletePage);
        }

    }
}