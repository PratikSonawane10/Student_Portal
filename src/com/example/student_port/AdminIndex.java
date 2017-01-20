package com.example.student_port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminIndex extends Activity implements View.OnClickListener {

    private static Button btnAdd;
    private static Button btnView;
    private static Button btnDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_index);
        btnAdd=(Button) findViewById(R.id.btnAddAdminRights);
        btnView=(Button) findViewById(R.id.btnViewAdminRights);
        btnDelete=(Button) findViewById(R.id.btnDeleteAdminRights);

        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAddAdminRights){
            Intent gotoAddpage = new Intent(AdminIndex.this,Add_For_Admin.class);
            startActivity(gotoAddpage);
        }else if(v.getId() == R.id.btnViewAdminRights){
            Intent gotoViewpage = new Intent(AdminIndex.this,View_For_Admin.class);
            startActivity(gotoViewpage);
        }else if(v.getId() == R.id.btnDeleteAdminRights){
            Intent gotoDeletewpage = new Intent(AdminIndex.this,Delete_For_Admin.class);
            startActivity(gotoDeletewpage);
        }

    }
}