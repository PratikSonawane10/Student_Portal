package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SemSelectionForStudent extends Activity {
    private static Spinner studentSemister;
    private static Button semSubmit_btn;
    ProgressDialog progressDialog;
    String semister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semselection_forstudent);
        SemForStudentlist();
    }

    public void SemForStudentlist() {
        studentSemister = (Spinner) findViewById(R.id.spinnerSemister);
        semSubmit_btn = (Button) findViewById(R.id.btnSemSubmit);

        semSubmit_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressDialog = new ProgressDialog(SemSelectionForStudent.this);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.show();

                        semister=String.valueOf(studentSemister.getSelectedItem());
                        Intent passtoListPage = new Intent(v.getContext(),ViewStudent.class);
                        passtoListPage.putExtra("semister",semister);
                        startActivity(passtoListPage);

                        try {
                            //ShowStudentForFaculty showStudentForFaculty = new ShowStudentForFaculty(SemSelectionForStudent.this);
                            //showStudentForFaculty.showBooksListSemWise(semister);
                        } catch (Exception e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(SemSelectionForStudent.this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    //add spinner item here ADMIN STUDENT FACULTY
    @Override
    public void onBackPressed() {
        this.finish();
    }
}