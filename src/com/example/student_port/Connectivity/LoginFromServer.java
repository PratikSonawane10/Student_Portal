package com.example.student_port.Connectivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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

public class LoginFromServer {
    private static Context context = null;
    private static String username;
    private static String password;
    private static String method;
    private static String format;
    private static String usertype;
    private static String signInResponse;
    private static LayoutInflater layoutInflater;
    private String usernameLorLogin;

    public String CheckToRemoteServer(String loginUsername, String confirmpassword, String loginUserType) throws Exception {

         method = "userLogin";
         format = "json";
         username = loginUsername;
         password = confirmpassword;
         usertype = loginUserType;

        //this.usernameLorLogin = loginUsername;

        //final String URL = URLInstance.getUrl();
        //String URL="http://192.168.0.5/StudentPortalAPI/api/studentportalapi.php";
        String URL= URLInstance.getUrl();
        JSONObject params = new JSONObject();
        try {
            params.put("method", method);
            params.put("format", format);
            params.put("Username", username);
            params.put("password", password);
            params.put("userType", usertype);

        } catch (Exception e) {

        }
        JsonObjectRequest signinReq = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.v("Response: %n %s", response.toString());
                        try {
                            returnResponse(response.getString("loginDetailsResponse"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(signinReq);
        return signInResponse;
    }

    public LoginFromServer(Context context) {
        this.context = context;
    }

    public void returnResponse(String response) {

        if (response.equals("LOGIN_SUCCESS")) {
            if(usertype.equals("ADMIN")){
                Intent gotoindexpage = new Intent(context, AdminIndex.class);
                context.startActivity(gotoindexpage);
                Toast.makeText(context, "Logged In.", Toast.LENGTH_SHORT).show();
            }
            else if(usertype.equals("FACULTY")){
                Intent gotoindexpage = new Intent(context, FacultyIndex.class);
                context.startActivity(gotoindexpage);
                Toast.makeText(context, "Logged In.", Toast.LENGTH_SHORT).show();
            }
            else if(usertype.equals("STUDENT")){
                Intent gotoindexpage = new Intent(context, SemSelectionForBook.class);
                context.startActivity(gotoindexpage);
                Toast.makeText(context, "Logged In.", Toast.LENGTH_SHORT).show();
            }
        } else if (response.equals("LOGIN_FAILED")) {
            Intent gotologinpage = new Intent(context, Login.class);
            context.startActivity(gotologinpage);
            Toast.makeText(context, "Enter Valid Credentials.", Toast.LENGTH_SHORT).show();
        }
    }
}
