package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.student_port.Connectivity.AddStudentToServer;
import com.example.student_port.SHA_256.PasswordConverter;

public class AddStudent extends Activity {

    private static EditText username;
    private static EditText password;
    private static Spinner studentSemister;
    private static Button addStudent_btn;

    ProgressDialog progressDialog;
    String email;
    String userpassword;
    String semister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        AddStudentButton();
    }

    public void AddStudentButton() {
        studentSemister = (Spinner) findViewById(R.id.spinnerSemisterAddStudent);
        username = (EditText) findViewById(R.id.stdUserName);
        password = (EditText) findViewById(R.id.stdPassword);
        addStudent_btn = (Button) findViewById(R.id.btnAddStudent);

        addStudent_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                            Toast.makeText(AddStudent.this, "Enter Username & Password", Toast.LENGTH_LONG).show();
                            //Snackbar.make(SignIn.this,"Enter Username & Password",Snackbar.LENGTH_LONG).show();
                        } else {
                            progressDialog = new ProgressDialog(AddStudent.this);
                            progressDialog.setMessage("Adding Please Wait...");
                            progressDialog.show();

                            email = username.getText().toString();
                            userpassword = password.getText().toString();
                            semister=String.valueOf(studentSemister.getSelectedItem());
                            try {
                                PasswordConverter passwordConverter = new PasswordConverter();
                                userpassword = passwordConverter.ConvertPassword(password.getText().toString());
                                AddStudentToServer addStudent = new AddStudentToServer(AddStudent.this);
                                addStudent.AddStudentToDB(email,userpassword,semister, progressDialog);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(AddStudent.this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
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