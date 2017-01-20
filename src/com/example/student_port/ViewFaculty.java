package com.example.student_port;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.student_port.Adapter.FacultyListAdapter;
import com.example.student_port.Connectivity.FacultyFetchList;
import com.example.student_port.Connectivity.FacultyRefreshList;
import com.example.student_port.Listener.StudentFetchListScrollListener;
import com.example.student_port.Model.FacultyListItems;
import com.example.student_port.Singleton.URLInstance;

import java.util.ArrayList;
import java.util.List;

public class ViewFaculty extends Activity {

    private static final String TAG = ViewFaculty.class.getSimpleName();

    private static String url;
    private ProgressDialog progressDialog;

    public List<FacultyListItems> FacultyList = new ArrayList<FacultyListItems>();

    public static RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout facultyListSwipeRefreshLayout;

    public List<FacultyListItems> originalStudentLists = new ArrayList<FacultyListItems>();

    static String urlForFetch;
    private int current_page = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewfaculty);

        recyclerView = (RecyclerView) findViewById(R.id.facultyList);
        facultyListSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.facultyListSwipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        url =  URLInstance.getUrl();
        url = url+"?method=ViewFaculty&format=json&currentPage="+current_page+" ";

        recyclerView.addOnScrollListener(new StudentFetchListScrollListener(layoutManager, current_page){
            @Override
            public void onLoadMore(int current_page) {

                url = "";
                url = URLInstance.getUrl();
                url = url+"?method=ViewFaculty&format=json&currentPage="+current_page+"";
                grabURL(url);

            }
        });
        recyclerView.smoothScrollToPosition(0);
        adapter = new FacultyListAdapter(FacultyList);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        progressDialog.setMessage("Fetching List Of Faculty...");
        progressDialog.show();

        facultyListSwipeRefreshLayout.setOnRefreshListener(studentListSwipeRefreshListener);
        facultyListSwipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3,
                R.color.refresh_progress_4);

        grabURL(url);
    }

    public void grabURL(String url) {
        try {
            //urlForFetch = url[0];
            FacultyFetchList.facultyFetchList(FacultyList, adapter, url, progressDialog);
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
    }

    private SwipeRefreshLayout.OnRefreshListener studentListSwipeRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            FacultyListItems FacultyListItems = FacultyList.get(0);
            try {
                url = "";
                url = URLInstance.getUrl();
                url = url+"?method=ViewFacultySwipeRefreshList&format=json";
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
                FacultyRefreshList.facultyRefreshFetchList(FacultyList, adapter, urlForFetch, facultyListSwipeRefreshLayout);
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