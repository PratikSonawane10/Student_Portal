package com.example.student_port.Connectivity;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.Model.FacultyListItems;
import com.example.student_port.app.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class FacultyFetchList {

    private static final String TAG = FacultyFetchList.class.getSimpleName();

    public static List facultyFetchList(List<FacultyListItems> facultyList, RecyclerView.Adapter adapter, String url, ProgressDialog progressDialog) {
        JsonObjectRequest facultyListReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("viewFacultyResponse");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    FacultyListItems facultyListItems = new FacultyListItems();
                                    facultyListItems.setFacultyName(replaceSpecialChars(obj.getString("username")));

                                    // adding pet to pets array
                                    facultyList.add(facultyListItems);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter.notifyDataSetChanged();
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
        AppController.getInstance().addToRequestQueue(facultyListReq);
        return facultyList;
    }

    public static String replaceSpecialChars(String str) {
        str = str.replaceAll("[+]"," ");
        return str;
    }
}
