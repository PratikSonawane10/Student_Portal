package com.example.student_port.Connectivity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.Model.BookListItems;
import com.example.student_port.app.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BookRefreshList {



    private static final String TAG = BookRefreshList.class.getSimpleName();
    private static Context context;

    public static List bookRefreshFetchList(List<BookListItems> bookList, RecyclerView.Adapter adapter, String url, SwipeRefreshLayout bookListSwipeRefreshLayout) {
        JsonObjectRequest bookListReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("showBookDetailsResponse");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {

                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    BookListItems bookListItems = new BookListItems();
                                    bookListItems.setBookName(replaceSpecialChars(obj.getString("bookname")));
                                    bookListItems.setBookSemister(obj.getString("semister"));
                                    bookListItems.setBookPath(obj.getString("path"));

                                    // adding pet to pets array
                                    bookList.add(0, bookListItems);
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
                        if(bookListSwipeRefreshLayout.isRefreshing()) {
                            bookListSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if(bookListSwipeRefreshLayout.isRefreshing()) {
                    bookListSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        AppController.getInstance().addToRequestQueue(bookListReq);
        return bookList;
    }

    public static String replaceSpecialChars(String str) {
        str = str.replaceAll("[+]"," ");
        return str;
    }
}
