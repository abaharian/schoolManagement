package com.baray.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baray.primitive.Student;
import com.baray.schoolmanagement.CourseListActivity;
import com.baray.schoolmanagement.R;
import com.baray.schoolmanagement.StudentListActivity;
import com.baray.tools.Database;

import java.util.ArrayList;

/**
 * Created by Akram on 3/10/2017.
 */
public class StudentAdapter extends ArrayAdapter<Student> {

    private Context context;
    ArrayList<Student> data;

    public StudentAdapter(Context context, ArrayList<Student> array) {
        super(context,R.layout.one_student);
        this.context = context;
        data = array;
    }

    @Override
    public int getCount() {
        return data == null ?0 :data.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        Student item = (Student) data.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.one_student, parent, false);
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
        private TextView tvGrade;
        private ImageView photo;
        private View layout;
        private View btnRemove;

        // Get a view and Cast it's controls.
        private viewHolder(View view) {
            mainView = view;
            tvName = (TextView) view.findViewById(R.id.one_stu_tv_name);
            tvGrade = (TextView) view.findViewById(R.id.one_stu_tv_grade);
            photo = (ImageView) view.findViewById(R.id.one_stu_img);
            layout = view.findViewById(R.id.one_class_layout);
            btnRemove = view.findViewById(R.id.one_stu_remove);
        }

        public void fill(final ArrayAdapter<Student> adapter,
                         final Student item, final int position) {
            tvName.setText(item.getFullName());
            String grade = item.getGrade();
            tvGrade.setText(grade == null ?"" :grade);
            String path = item.getPhotoPath();
            if(path != null && path.length() > 0) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(path, options);

                if (bitmap != null)
                    photo.setImageBitmap(bitmap);
            }

            View.OnClickListener ocl = new View.OnClickListener() {

                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(context, CourseListActivity.class);
                        intent.putExtra("student", item);
                        context.startActivity(intent);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            tvName.setOnClickListener(ocl);
            tvGrade.setOnClickListener(ocl);
            layout.setOnClickListener(ocl);
                        photo.setOnClickListener(ocl);
            mainView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                }
            });

            btnRemove.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    removeStudent(item, position);
                }
            });

        }

    }

    private void removeStudent(Student s, int position){
        if(context.getClass() == StudentListActivity.class){
            StudentListActivity sla = (StudentListActivity) context;
            sla.deleteStudent(s);
        }
    }

}
