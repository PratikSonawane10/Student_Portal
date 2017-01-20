package com.example.student_port.Connectivity;

import android.app.ProgressDialog;
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

public class BookFetchList {

    private static final String TAG = BookFetchList.class.getSimpleName();

    public static List bookFetchList(List<BookListItems> bookList, RecyclerView.Adapter adapter, String url, ProgressDialog progressDialog) {
        JsonObjectRequest bookListReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("showBookDetailsResponse");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    BookListItems bookListItems = new BookListItems();
                                    bookListItems.setBookName(replaceSpecialChars(obj.getString("bookName")));
                                    bookListItems.setBookSemister(obj.getString("semister"));
                                    bookListItems.setBookPath(obj.getString("bookpath"));

                                    // adding pet to pets array
                                    bookList.add(bookListItems);
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
        AppController.getInstance().addToRequestQueue(bookListReq);
        return bookList;
    }

    public static String replaceSpecialChars(String str) {
        str = str.replaceAll("[+]"," ");
        return str;
    }
}

