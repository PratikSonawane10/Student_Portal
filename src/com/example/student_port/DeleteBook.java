package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.student_port.Connectivity.RemoveBook;
import com.example.student_port.Connectivity.RemoveStudent;

public class DeleteBook extends Activity {

    private static EditText bookName;
    private static Spinner studentSemister;
    private static Button deleteBook_btn;

    ProgressDialog progressDialog;
    String nameOfBook;
    String semister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletebook);
        DeleteBookButton();
    }

    public void DeleteBookButton() {
        studentSemister = (Spinner) findViewById(R.id.spinnerSemister);
        bookName = (EditText) findViewById(R.id.bookName);
        deleteBook_btn = (Button) findViewById(R.id.btnBookDelete);

        deleteBook_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bookName.getText().toString().isEmpty()) {
                            Toast.makeText(DeleteBook.this, "Enter Book name.", Toast.LENGTH_LONG).show();
                            //Snackbar.make(SignIn.this,"Enter Username & Password",Snackbar.LENGTH_LONG).show();
                        } else {
                            progressDialog = new ProgressDialog(DeleteBook.this);
                            progressDialog.setMessage("Deleting Please Wait...");
                            progressDialog.show();

                            nameOfBook = bookName.getText().toString();
                            semister=String.valueOf(studentSemister.getSelectedItem());
                            try {
                                RemoveBook deletebook = new RemoveBook(DeleteBook.this);
                                deletebook.DeleteBookFromDB(nameOfBook,semister);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(DeleteBook.this, "Exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
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