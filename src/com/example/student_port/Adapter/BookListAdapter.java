package com.example.student_port.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.student_port.Files_Download;
import com.example.student_port.Model.BookListItems;
import com.example.student_port.Model.StudentListItems;
import com.example.student_port.R;

import java.util.List;
import java.util.StringTokenizer;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {


    private List<BookListItems> bookListItem;
    View v;
    ViewHolder viewHolder;
    RecyclerView recyclerView;

    public BookListAdapter(List<BookListItems> bookListItem) {
        this.bookListItem = bookListItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.booklistsubitems, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        BookListItems bookListItems = bookListItem.get(i);
        viewHolder.bindPetList(bookListItems);
    }

    @Override
    public int getItemCount() {
        return bookListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        public TextView name;
        public TextView semister;
        String path;
        public View booklClick;
        private BookListItems listItem;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.bookName);
            semister = (TextView) itemView.findViewById(R.id.studentSemisterForBook);
            booklClick = itemView;
            booklClick.setOnClickListener(this);
        }

        public void bindPetList(BookListItems bookListItems) {
            this.listItem = bookListItems;

            name.setText(bookListItems.getBookName());

            semister.setText(bookListItems.getBookSemister());
            path = bookListItems.getBookPath();

        }

        @Override
        public void onClick(View v) {

            try {
                Intent bookDownload = new Intent(v.getContext(), Files_Download.class);
                bookDownload.putExtra("path",listItem.getBookPath());
                bookDownload.putExtra("bookName",listItem.getBookName());
                bookDownload.putExtra("semister",listItem.getBookSemister());
                v.getContext().startActivity(bookDownload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
