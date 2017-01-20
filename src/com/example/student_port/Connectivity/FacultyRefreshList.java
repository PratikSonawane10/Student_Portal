package com.example.student_port.Connectivity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class FacultyRefreshList {

    private static final String TAG = FacultyRefreshList.class.getSimpleName();
    private static Context context;

    public static List facultyRefreshFetchList(List<FacultyListItems> facultyList, RecyclerView.Adapter adapter, String url, SwipeRefreshLayout facultyListSwipeRefreshLayout) {
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
                                    facultyListItems.setFacultyName(replaceSpecialChars(obj.getString("name")));

                                    // adding pet to pets array
                                    facultyList.add(0, facultyListItems);
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
                        if(facultyListSwipeRefreshLayout.isRefreshing()) {
                            facultyListSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if(facultyListSwipeRefreshLayout.isRefreshing()) {
                    facultyListSwipeRefreshLayout.setRefreshing(false);
                }
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
