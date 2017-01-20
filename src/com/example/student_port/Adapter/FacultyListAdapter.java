package com.example.student_port.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.student_port.Model.FacultyListItems;
import com.example.student_port.R;
import java.util.List;

public class FacultyListAdapter  extends RecyclerView.Adapter<FacultyListAdapter.ViewHolder> {

    List<FacultyListItems> facultyList;
    View v;
    ViewHolder viewHolder;
    RecyclerView recyclerView;

    public FacultyListAdapter(List<FacultyListItems> facultyList) {
        this.facultyList = facultyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.facultylistubitems, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FacultyListItems facultyListItems = facultyList.get(i);
        viewHolder.bindFacultyList(facultyListItems);
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        private FacultyListItems facultyListItems;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.facultyName);
        }

        public void bindFacultyList(FacultyListItems facultyListItems) {
            this.facultyListItems = facultyListItems;

            name.setText("Name : " +facultyListItems.getFacultyName());
        }
    }

}
