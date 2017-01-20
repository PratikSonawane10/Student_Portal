package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.example.student_port.Connectivity.LoginFromServer;
import com.example.student_port.SHA_256.PasswordConverter;

public class Login extends Activity {

    private static EditText username;
    private static EditText password;
    private Spinner spinner1;
    private static Button login_btn;

    ProgressDialog progressDialog;
    String loginUsername;
    String userType;
    String userpassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        addListenerOnSpinnerItemSelection();
        SignInButton();
    }
    public void addListenerOnSpinnerItemSelection(){

        spinner1 = (Spinner) findViewById(R.id.spinnerUserType);
       // spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void SignInButton() {
        spinner1 = (Spinner) findViewById(R.id.spinnerUserType);
        username = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPassword);
        login_btn = (Button) findViewById(R.id.btnSubmit);

        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                            Toast.makeText(Login.this, "Enter Username & Password", Toast.LENGTH_LONG).show();
                            //Snackbar.make(SignIn.this,"Enter Username & Password",Snackbar.LENGTH_LONG).show();
                        } else {
                            progressDialog = new ProgressDialog(Login.this);
                            progressDialog.setMessage("Login Please Wait...");
                            progressDialog.show();

                            userType=String.valueOf(spinner1.getSelectedItem());
                            loginUsername = username.getText().toString();
                            userpassword = password.getText().toString();

                            try {
                                PasswordConverter passwordConverter = new PasswordConverter();
                                userpassword = passwordConverter.ConvertPassword(password.getText().toString());
                                LoginFromServer loginFromServer = new LoginFromServer(Login.this);
                                loginFromServer.CheckToRemoteServer(loginUsername, userpassword,userType );
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
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