package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.student_port.Connectivity.ShowBookForFaculty;
import com.example.student_port.Model.BookListItems;

import java.util.ArrayList;
import java.util.List;

public class SemSelectionForBook extends Activity {
    private static Spinner bookSemister;
    private static Button semSubmit_btn;
    ProgressDialog progressDialog;
    String semister;
    public List<BookListItems> bookListItems = new ArrayList<BookListItems>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semselection_forbook);
        SemForBooklist();
    }

    public void SemForBooklist() {
        bookSemister = (Spinner) findViewById(R.id.spinnerSemister);
        semSubmit_btn = (Button) findViewById(R.id.btnSemSubmit);

        semSubmit_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        semister=String.valueOf(bookSemister.getSelectedItem());

                        Intent passtoListPage = new Intent(v.getContext(),ViewBooks.class);
                        passtoListPage.putExtra("semister",semister);
                        startActivity(passtoListPage);
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