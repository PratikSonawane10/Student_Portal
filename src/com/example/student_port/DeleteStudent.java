package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.student_port.Connectivity.RemoveStudent;
import com.example.student_port.SHA_256.PasswordConverter;

public class DeleteStudent extends Activity {

    private static EditText username;
    private static Spinner studentSemister;
    private static Button deleteStudent_btn;

    ProgressDialog progressDialog;
    String studName;
    String semister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletestudent);
        DeleteStudentButton();
    }
    public void DeleteStudentButton() {
        studentSemister = (Spinner) findViewById(R.id.spinnerSemisterDeleteStudent);
        username = (EditText) findViewById(R.id.stdUsername);
        deleteStudent_btn = (Button) findViewById(R.id.btnStudentDelete);

        deleteStudent_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().isEmpty()) {
                            Toast.makeText(DeleteStudent.this, "Enter Username.", Toast.LENGTH_LONG).show();
                            //Snackbar.make(SignIn.this,"Enter Username & Password",Snackbar.LENGTH_LONG).show();
                        } else {
                            progressDialog = new ProgressDialog(DeleteStudent.this);
                            progressDialog.setMessage("Deleting Please Wait...");
                            progressDialog.show();

                            studName = username.getText().toString();
                            semister=String.valueOf(studentSemister.getSelectedItem());
                            try {
                                RemoveStudent removeStudent = new RemoveStudent(DeleteStudent.this);
                                removeStudent.DeleteStudentFromDB(studName,semister, progressDialog);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(DeleteStudent.this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
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