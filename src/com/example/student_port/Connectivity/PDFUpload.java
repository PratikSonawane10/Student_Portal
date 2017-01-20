package com.example.student_port.Connectivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.student_port.FileUpload;
import com.example.student_port.Singleton.URLInstance;
import com.example.student_port.app.AppController;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PDFUpload {

    private static final String SERVER_URL =  URLInstance.getUrl();
    private static Context context;
    private static Map<String, String> headerPart;
    private static Map<String, File> filePartData;
    private static Map<String, String> stringPart;

    public static void uploadToRemoteServer(FileUpload fileUpload, String filePath, String nameOfBook, String bookSemister, ProgressDialog progressDialog) throws Exception {

        context = fileUpload.getApplicationContext();
        String pdfFilePath = filePath;
        String bookName = nameOfBook;
        String semister = bookSemister;
        String method = "uploadPDFFile";
        String format = "json";

        //Auth header
        headerPart = new HashMap<>();
        headerPart.put("Content-Type", "multipart/form-data;");

        //File part
        filePartData = new HashMap<>();
        filePartData.put("pdfFilePath", new File(pdfFilePath));

        //String part
        stringPart = new HashMap<>();
        //stringPart.put("pdfFilePath", pdfFilePath);
        stringPart.put("method", method);
        stringPart.put("format", format);
        stringPart.put("bookName", bookName);
        stringPart.put("semister", semister);

        //new UploadToServerCustomRequest().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        CustomMultipartRequest mCustomRequest = new CustomMultipartRequest(Request.Method.POST, context, SERVER_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(context, "Succefully Uploaded", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "Error Uploading File", Toast.LENGTH_LONG).show();
            }
        }, filePartData, stringPart, headerPart);
        AppController.getInstance().addToRequestQueue(mCustomRequest);
    }

    public static class UploadToServerCustomRequest extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            CustomMultipartRequest mCustomRequest = new CustomMultipartRequest(Request.Method.POST, context, SERVER_URL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Toast.makeText(context, "Succefully Uploaded", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, "Error Uploading File", Toast.LENGTH_LONG).show();
                }
            }, filePartData, stringPart, headerPart);
            AppController.getInstance().addToRequestQueue(mCustomRequest);
            return null;
        }
    }
}
