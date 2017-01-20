package com.example.student_port.Connectivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.Add_For_Admin;
import com.example.student_port.Singleton.URLInstance;
import com.example.student_port.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;

public class AddStudentToServer {
    private static Context context = null;
    private static String username;
    private static String password;
    private static String semister;
    private static String method;
    private static String format;
    private static String addStudentResponse;


    public String AddStudentToDB(String email, String confirmpassword, String Studentsemister,ProgressDialog progressDialog) throws Exception {

        method = "AddStudent";
        format = "json";
        username = email;
        password = confirmpassword;
        semister = Studentsemister;

        final String URL = URLInstance.getUrl();
        JSONObject params = new JSONObject();
        try {
            params.put("method", method);
            params.put("format", format);
            params.put("Username", username);
            params.put("password", password);
            params.put("semister", semister);

        } catch (Exception e) {

        }
        JsonObjectRequest addStudent = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.v("Response: %n %s", response.toString());
                        try {
                            returnResponse(response.getString("addStudentResponse"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.hide();
                        }
                        progressDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                        progressDialog.hide();
                        //Intent gotoTimeOutError = new Intent(context, TimeOut_DialogBox.class);
                        //context.startActivity(gotoTimeOutError);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(addStudent);
        return addStudentResponse;
    }

    public AddStudentToServer(Context context) {
        this.context = context;
    }

    public void returnResponse(String response) {

        if (response.equals("SUCCESSFULLY_ADDED")) {
            Toast.makeText(context, "Student Added Successfully.", Toast.LENGTH_SHORT).show();


        } else if (response.equals("ERROR")) {
            Toast.makeText(context, "Student Not Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
