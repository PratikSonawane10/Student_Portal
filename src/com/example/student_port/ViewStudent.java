package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.student_port.Adapter.StudentListAdapter;
import com.example.student_port.Connectivity.StudentFetchList;
import com.example.student_port.Connectivity.StudentRefreshList;
import com.example.student_port.Listener.StudentFetchListScrollListener;
import com.example.student_port.Model.StudentListItems;
import com.example.student_port.Singleton.URLInstance;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewStudent extends Activity {

    private static final String TAG = ViewStudent.class.getSimpleName();

    private static String url;
    private ProgressDialog progressDialog;

    public List<StudentListItems> StudentsList = new ArrayList<StudentListItems>();

    public static RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout studentListSwipeRefreshLayout;

    public List<StudentListItems> originalStudentLists = new ArrayList<StudentListItems>();

    static String urlForFetch;
    private int current_page = 1;
    private String semister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstudent);

        recyclerView = (RecyclerView) findViewById(R.id.studentList);
        studentListSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.studentListSwipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.smoothScrollToPosition(0);
        adapter = new StudentListAdapter(StudentsList);
        recyclerView.setAdapter(adapter);

        Intent myIntent = getIntent();
        semister = myIntent.getStringExtra("semister");

        url =  URLInstance.getUrl();
        url = url+"?method=ViewStudent&format=json&currentPage="+current_page+"&semister="+semister+"";

        progressDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        progressDialog.setMessage("Fetching List Of Student...");
        progressDialog.show();
        String currPage=String.valueOf(current_page);
        try {
            StudentFetchList.studentFetchList(StudentsList, adapter, semister,currPage, progressDialog);
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
        recyclerView.addOnScrollListener(new StudentFetchListScrollListener(layoutManager, current_page){
            @Override
            public void onLoadMore(int current_page) {

                    url = "";
                    url = URLInstance.getUrl();
                    url = url+"?method=ViewStudent&format=json&currentPage="+current_page+"&semister="+semister+"";
                    grabURL(url);

            }
        });



    }

    public void grabURL(String url) {
        //new FetchListFromServer().execute(url);
        try {
            String currPage=String.valueOf(current_page);
            //urlForFetch = url;
           // StudentFetchList.studentFetchList(StudentsList, adapter, url, progressDialog);
            StudentFetchList.studentFetchList(StudentsList, adapter, semister,currPage, progressDialog);
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
    }

    public class FetchListFromServer extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... url) {
            try {
                urlForFetch = url[0];
                //StudentFetchList.studentFetchList(StudentsList, adapter, urlForFetch, progressDialog);
            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }
            return null;
        }
    }

    private SwipeRefreshLayout.OnRefreshListener studentListSwipeRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            StudentListItems studentListItems = StudentsList.get(0);
            try {
                url = "";
                url = URLInstance.getUrl();
                url = url+"?method=showStudentSwipeRefreshList&format=json&semister="+semister+"";
            } catch (Exception e) {
                e.printStackTrace();
            }
            new FetchRefreshListFromServer().execute(url);
        }
    };

    public class FetchRefreshListFromServer extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... url) {
            try {
                urlForFetch = url[0];
                StudentRefreshList.studentRefreshFetchList(StudentsList, adapter, urlForFetch, studentListSwipeRefreshLayout);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
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