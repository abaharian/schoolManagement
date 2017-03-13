package com.baray.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.baray.primitive.Student;
import com.baray.schoolmanagement.R;

import java.util.ArrayList;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ListView listView = (ListView)findViewById(R.id.test_list_view);
        ArrayList<Student> students = new ArrayList<Student>();

        StudentAdapter adapter = new StudentAdapter(this, students);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
