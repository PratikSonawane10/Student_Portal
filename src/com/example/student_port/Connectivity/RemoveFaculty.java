package com.example.student_port.Connectivity;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.*;
import com.example.student_port.Singleton.URLInstance;
import com.example.student_port.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoveFaculty {
    private static Context context = null;
    private static String username;
    private static String method;
    private static String format;
    private static String DeleteFacultyResponse;

    public String DeleteFacultyFromDB(String email) throws Exception {

        method = "DeleteFaculty";
        format = "json";
        username = email;

        final String URL = URLInstance.getUrl();
        JSONObject params = new JSONObject();
        try {
            params.put("method", method);
            params.put("format", format);
            params.put("Username", username);

        } catch (Exception e) {

        }
        JsonObjectRequest deleteFaculty = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.v("Response: %n %s", response.toString());
                        try {
                            returnResponse(response.getString("DeleteFacultyResponse"));
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
        AppController.getInstance().addToRequestQueue(deleteFaculty);
        return DeleteFacultyResponse;
    }

    public RemoveFaculty(Context context) {
        this.context = context;
    }

    public void returnResponse(String response) {

        if (response.equals("SUCCESSFULLY_DELETED")) {
            Intent gotoAdminPage = new Intent(context, Delete_For_Admin.class);
            context.startActivity(gotoAdminPage);
            Toast.makeText(context, "Faculty Deleted Successfully.", Toast.LENGTH_SHORT).show();

        } else if (response.equals("ERROR")) {
            Intent gotoAdminPage = new Intent(context, DeleteFaculty.class);
            context.startActivity(gotoAdminPage);
            Toast.makeText(context, "Faculty Not Deleted Successfully.", Toast.LENGTH_SHORT).show();
        }
    }

}
