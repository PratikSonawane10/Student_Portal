package com.example.student_port.Connectivity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.Model.StudentListItems;
import com.example.student_port.app.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class StudentRefreshList {

    private static final String TAG = StudentRefreshList.class.getSimpleName();
    private static Context context;

    public static List studentRefreshFetchList(List<StudentListItems> StudentsList, RecyclerView.Adapter adapter, String url, SwipeRefreshLayout studentListSwipeRefreshLayout) {
        JsonObjectRequest studentListReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("showStudentDetailsResponse");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {

                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    StudentListItems studentListItems = new StudentListItems();
                                    studentListItems.setStudentName(replaceSpecialChars(obj.getString("name")));
                                    studentListItems.setStudentSemister(obj.getString("semister"));

                                    // adding pet to pets array
                                    StudentsList.add(0, studentListItems);
                                    adapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list Adapter about data changes
                        // so that it renders the list view with updated data
                        if(studentListSwipeRefreshLayout.isRefreshing()) {
                            studentListSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if(studentListSwipeRefreshLayout.isRefreshing()) {
                    studentListSwipeRefreshLayout.setRefreshing(false);
                }
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
