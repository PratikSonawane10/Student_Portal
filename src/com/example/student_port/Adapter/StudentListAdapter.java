package com.example.student_port.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.student_port.Model.StudentListItems;
import com.example.student_port.R;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {


    View v;
    ViewHolder viewHolder;
    RecyclerView recyclerView;
    private List<StudentListItems> studentsList;
    public StudentListAdapter(List<StudentListItems> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.studentslistubitems, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        StudentListItems StudentListItems = studentsList.get(i);
        viewHolder.bindStudentList(StudentListItems);
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView semister;

        private StudentListItems listItem;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.studentName);
            semister = (TextView) itemView.findViewById(R.id.studentSemister);
        }

        public void bindStudentList(StudentListItems StudentListItems) {
            this.listItem = StudentListItems;

            name.setText("Name : " +StudentListItems.getStudentName());
            semister.setText("Semister : " + StudentListItems.getStudentSemister());
        }
    }
}
