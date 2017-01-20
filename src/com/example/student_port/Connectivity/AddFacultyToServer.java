package com.example.student_port.Connectivity;


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

public class AddFacultyToServer {
    private static Context context = null;
    private static String username;
    private static String password;
    private static String method;
    private static String format;
    private static String signInResponse;

    public String AddFacultyToDB(String email, String confirmpassword) throws Exception {

        method = "AddFaculty";
        format = "json";
        username = email;
        password = confirmpassword;

        final String URL = URLInstance.getUrl();
        JSONObject params = new JSONObject();
        try {
            params.put("method", method);
            params.put("format", format);
            params.put("Username", username);
            params.put("password", password);

        } catch (Exception e) {

        }
        JsonObjectRequest signinReq = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.v("Response: %n %s", response.toString());
                        try {
                            returnResponse(response.getString("AddFacultyResponse"));
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
        AppController.getInstance().addToRequestQueue(signinReq);
        return signInResponse;
    }

    public AddFacultyToServer(Context context) {
        this.context = context;
    }

    public void returnResponse(String response) {

        if (response.equals("SUCCESSFULLY_ADDED")) {
            Intent gotoAdminPage = new Intent(context, Add_For_Admin.class);
            context.startActivity(gotoAdminPage);
            Toast.makeText(context, "Faculty Added Successfully.", Toast.LENGTH_SHORT).show();

        } else if (response.equals("ERROR")) {
            Intent gotoAdminPage = new Intent(context, com.example.student_port.AddFaculty.class);
            context.startActivity(gotoAdminPage);
            Toast.makeText(context, "Faculty Not Added Successfully.", Toast.LENGTH_SHORT).show();
        }
    }

}
