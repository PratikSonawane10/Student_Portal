package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.student_port.Connectivity.RemoveFaculty;

public class DeleteFaculty  extends Activity {

    private static EditText username;
    private static Button facDelete_btn;

    ProgressDialog progressDialog;
    String facName;
    String userpassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletefaculty);
        AddFacultyButton();
    }

    public void AddFacultyButton() {
        username = (EditText) findViewById(R.id.facUsername);
        facDelete_btn = (Button) findViewById(R.id.btnDeleteFaculty);

        facDelete_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().isEmpty()) {
                            Toast.makeText(DeleteFaculty.this, "Enter Username", Toast.LENGTH_LONG).show();
                            //Snackbar.make(SignIn.this,"Enter Username & Password",Snackbar.LENGTH_LONG).show();
                        } else {
                            progressDialog = new ProgressDialog(DeleteFaculty.this);
                            progressDialog.setMessage("Deleting Please Wait...");
                            progressDialog.show();
                            facName = username.getText().toString();

                            try {

                                RemoveFaculty deleteFaculty = new RemoveFaculty(DeleteFaculty.this);
                                deleteFaculty.DeleteFacultyFromDB(facName);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(DeleteFaculty.this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
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