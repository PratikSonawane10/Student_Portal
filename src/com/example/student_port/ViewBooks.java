package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.student_port.Adapter.BookListAdapter;
import com.example.student_port.Adapter.FacultyListAdapter;
import com.example.student_port.Connectivity.*;
import com.example.student_port.Listener.StudentFetchListScrollListener;
import com.example.student_port.Model.BookListItems;
import com.example.student_port.Model.FacultyListItems;
import com.example.student_port.Singleton.URLInstance;

import java.util.ArrayList;
import java.util.List;

public class ViewBooks extends Activity {
    private static final String TAG = ViewBooks.class.getSimpleName();

    private static String url;
    private ProgressDialog progressDialog;

    public List<BookListItems> bookList = new ArrayList<BookListItems>();

    public static RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout bookListSwipeRefreshLayout;

    public List<BookListItems> originalBookLists = new ArrayList<BookListItems>();

    static String urlForFetch;
    private int current_page = 1;
    static String semister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewbooks);

        recyclerView = (RecyclerView) findViewById(R.id.bookList);
        bookListSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.bookListSwipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent myIntent = getIntent();
        semister = myIntent.getStringExtra("semister");

        recyclerView.smoothScrollToPosition(0);
        adapter = new BookListAdapter(bookList);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        progressDialog.setMessage("Fetching List Of Books...");
        progressDialog.show();

        String currPage=String.valueOf(current_page);
        try {

            ShowBookForFaculty.showBooksListSemWise(bookList, adapter,semister, currPage, progressDialog);
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }

        url =  URLInstance.getUrl();
        url = url+"?method=ViewBook&format=json&currentPage="+current_page+"&semister="+semister+"";

        recyclerView.addOnScrollListener(new StudentFetchListScrollListener(layoutManager, current_page){
            @Override
            public void onLoadMore(int current_page) {

                url = "";
                url = URLInstance.getUrl();
                url = url+"?method=ViewBook&format=json&currentPage="+current_page+"&semister="+semister+"";
                grabURL(url);

            }
        });


//        bookListSwipeRefreshLayout.setOnRefreshListener(studentListSwipeRefreshListener);
//        bookListSwipeRefreshLayout.setColorSchemeResources(
//                R.color.refresh_progress_1,
//                R.color.refresh_progress_2,
//                R.color.refresh_progress_3,
//                R.color.refresh_progress_4);

        grabURL(url);
    }
    public void grabURL(String url) {
        //new FetchListFromServer().execute(url);
    }





    private void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
    }

}