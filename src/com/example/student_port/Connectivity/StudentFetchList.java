package com.example.student_port.Connectivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.Model.StudentListItems;
import com.example.student_port.Singleton.URLInstance;
import com.example.student_port.app.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class StudentFetchList {
    private static final String TAG = StudentFetchList.class.getSimpleName();
    private static Context context = null;
    private static String sem;
    private static String cuuPage;
    private static String method;
    private static String format;

    //public static List studentFetchList(List<StudentListItems> StudentsList, RecyclerView.Adapter adapter, String url,ProgressDialog progressDialog) {
    public static List studentFetchList(List<StudentListItems> StudentsList, RecyclerView.Adapter adapter, String semister, String current_page,ProgressDialog progressDialog) {

        //JsonObjectRequest studentListReq = new JsonObjectRequest(Request.Method.GET, url, null,

        method = "ViewStudent";
        format = "json";
        sem = semister;
        cuuPage = current_page;

        final String URL = URLInstance.getUrl();
        JSONObject params = new JSONObject();
        try {
            params.put("method", method);
            params.put("format", format);
            params.put("semister", sem);
            params.put("current_page", cuuPage);

        } catch (Exception e) {

        }
        JsonObjectRequest studentListReq = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("viewStudentResponse");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    StudentListItems studentListItems = new StudentListItems();
                                    studentListItems.setStudentName(replaceSpecialChars(obj.getString("username")));
                                    studentListItems.setStudentSemister(obj.getString("semister"));

                                    // adding pet to pets array
                                    StudentsList.add(studentListItems);
                                    adapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(studentListReq);
        return StudentsList;
    }

    public static String replaceSpecialChars(String str) {
        str = str.replaceAll("[+]"," ");
        return str;
    }
}
