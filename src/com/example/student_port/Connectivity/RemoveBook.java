package com.example.student_port.Connectivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.DeleteStudent;
import com.example.student_port.Delete_For_Admin;
import com.example.student_port.Singleton.URLInstance;
import com.example.student_port.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoveBook {

    private static Context context = null;
    private static String bookName;
    private static String semister;
    private static String method;
    private static String format;
    private static String studentDeletedResponse;

    public String DeleteBookFromDB(String nameOfBook, String Studentsemister) throws Exception {

        method = "DeleteBook";
        format = "json";
        bookName = nameOfBook;
        semister = Studentsemister;

        final String URL = URLInstance.getUrl();
        JSONObject params = new JSONObject();
        try {
            params.put("method", method);
            params.put("format", format);
            params.put("bookName", bookName);
            params.put("semister", semister);

        } catch (Exception e) {

        }
        JsonObjectRequest addStudent = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.v("Response: %n %s", response.toString());
                        try {
                            returnResponse(response.getString("deleteBookResponse"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                        //Intent gotoTimeOutError = new Intent(context, TimeOut_DialogBox.class);
                        //context.startActivity(gotoTimeOutError);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(addStudent);
        return studentDeletedResponse;
    }

    public RemoveBook(Context context) {
        this.context = context;
    }

    public void returnResponse(String response) {

        if (response.equals("SUCCESSFULLY_DELETED")) {
            Intent gotoindexpage = new Intent(context, Delete_For_Admin.class);
            context.startActivity(gotoindexpage);
            Toast.makeText(context, "Book Deleted Successfully.", Toast.LENGTH_SHORT).show();

        } else if (response.equals("ERROR")) {
            Intent gotoStudentRegistration = new Intent(context,DeleteStudent.class);
            context.startActivity(gotoStudentRegistration);
            Toast.makeText(context, "Book Not Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
