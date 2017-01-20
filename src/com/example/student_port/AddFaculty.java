package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.student_port.Connectivity.AddFacultyToServer;
import com.example.student_port.SHA_256.PasswordConverter;

public class AddFaculty extends Activity {

    private static EditText username;
    private static EditText password;
    private static Button login_btn;

    ProgressDialog progressDialog;
    String email;
    String userpassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_faculty);
        AddFacultyButton();
    }

    public void AddFacultyButton() {
        username = (EditText) findViewById(R.id.facUsername);
        password = (EditText) findViewById(R.id.facPassword);
        login_btn = (Button) findViewById(R.id.btnAddFaculty);

        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                            Toast.makeText(AddFaculty.this, "Enter Username & Password", Toast.LENGTH_LONG).show();
                            //Snackbar.make(SignIn.this,"Enter Username & Password",Snackbar.LENGTH_LONG).show();
                        } else {
                            progressDialog = new ProgressDialog(AddFaculty.this);
                            progressDialog.setMessage("Adding Please Wait...");
                            progressDialog.show();
                            email = username.getText().toString();

                            userpassword = password.getText().toString();
                            try {
                                PasswordConverter passwordConverter = new PasswordConverter();
                                userpassword = passwordConverter.ConvertPassword(password.getText().toString());
                                AddFacultyToServer addFaculty = new AddFacultyToServer(AddFaculty.this);
                                addFaculty.AddFacultyToDB(email, userpassword);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(AddFaculty.this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
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