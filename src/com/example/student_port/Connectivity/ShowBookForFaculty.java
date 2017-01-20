package com.example.student_port.Connectivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.student_port.Model.BookListItems;
import com.example.student_port.Singleton.URLInstance;
import com.example.student_port.app.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ShowBookForFaculty {

    private static Context context = null;
    private static String sem;
    private static String cuuPage;
    private static String method;
    private static String format;
    private static String BookListResponse;

    private static final String TAG = ShowBookForFaculty.class.getSimpleName();

    public static List showBooksListSemWise(List<BookListItems> bookList,RecyclerView.Adapter adapter, String semister,String current_page, ProgressDialog progressDialog) {

        method = "ViewBook";
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

        JsonObjectRequest bookListReq = new JsonObjectRequest(Request.Method.POST, URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("viewBookResponse");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    BookListItems bookListItems = new BookListItems();
                                    bookListItems.setBookName((obj.getString("bookName")));
                                    bookListItems.setBookSemister((obj.getString("semister")));
                                    bookListItems.setBookPath((obj.getString("bookpath")));

                                    // adding pet to pets array
                                    bookList.add(bookListItems);
                                    adapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    progressDialog.hide();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.hide();
                        }
                        progressDialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

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
