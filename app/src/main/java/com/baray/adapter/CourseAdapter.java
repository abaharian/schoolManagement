package com.baray.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baray.primitive.Course;
import com.baray.schoolmanagement.CourseDetailActivity;
import com.baray.schoolmanagement.R;

import java.util.ArrayList;

/**
 * Created by Akram on 4/3/2017.
 */
public class CourseAdapter extends ArrayAdapter<Course> {
    private Context context;
    private ArrayList<Course> data;

    public CourseAdapter(Context context, ArrayList<Course> array) {
        super(context, R.layout.one_course);
        this.context = context;
        data = array;
    }

    @Override
    public int getCount() {
        return data == null ?0 :data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        Course item = (Course) data.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.one_course, parent, false);
            holder = new viewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.fill(this, item, position);
        return convertView;
    }

    private class viewHolder {
        private View mainView;
        private TextView tvName;
        private TextView tvTeacher;
        private View layout1;
        private View layoutMain;
        private TextView tvLastUpdate;

        // Get a view and Cast it's controls.
        private viewHolder(View view) {
            mainView = view;
            tvName = (TextView) view.findViewById(R.id.oc_tv_title);
            tvTeacher = (TextView) view.findViewById(R.id.oc_tv_teacher_name);
            layout1 = view.findViewById(R.id.oc_layout1);
            layoutMain = view.findViewById(R.id.oc_layout_main);
            tvLastUpdate = (TextView) view.findViewById(R.id.oc_tv_date);
        }

        public void fill(final ArrayAdapter<Course> adapter,
                         final Course item, final int position) {
            tvName.setText(item.getName());
            String teacherName = item.getTeacherName();
            tvTeacher.setText(teacherName == null ?"" :teacherName);
            tvLastUpdate.setText(item.getLastUpdate());

            View.OnClickListener ocl = new View.OnClickListener() {

                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(context, CourseDetailActivity.class);
                        context.startActivity(intent);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            tvName.setOnClickListener(ocl);
            tvTeacher.setOnClickListener(ocl);
            layout1.setOnClickListener(ocl);
            layoutMain.setOnClickListener(ocl);
            tvLastUpdate.setOnClickListener(ocl);
            mainView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                }
            });

        }

    }
}
