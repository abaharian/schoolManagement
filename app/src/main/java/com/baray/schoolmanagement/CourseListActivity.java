package com.baray.schoolmanagement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baray.adapter.CourseAdapter;
import com.baray.primitive.Course;
import com.baray.primitive.Student;
import com.baray.tools.Webservice;

import java.util.ArrayList;

public class CourseListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Student student;
    private View tvWait;
    private View progressWait;
    private ListView listViewCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ccl_toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.cl_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.cl_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View menuBtn = findViewById(R.id.toolbar_menu_btn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);

            }
        });

        try {
            student = (Student) getIntent().getSerializableExtra("student");
            fillStudentIdentification();
        } catch(Exception e){

        }

        tvWait = findViewById(R.id.ccl_tv_wait);
        progressWait = findViewById(R.id.ccl_progress);
        listViewCourses = (ListView)findViewById(R.id.ccl_list_view);
        fillCourseList();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.cl_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Application.getApplication().menuAboutClick(this);
        } else if (id == R.id.nav_gallery) {
            Application.getApplication().menuGalleryClick(this);
        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_logout) {
            Application.getApplication().clearUsernamPassword();
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.cl_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fillCourseList(){
        new courseListReader(this.student).execute();
    }

    public void fillStudentIdentification(){
        ImageView photo = (ImageView)findViewById(R.id.ccl_stu_img);
        TextView tvName = (TextView)findViewById(R.id.ccl_stu_tv_name);
        TextView tvGrade = (TextView)findViewById(R.id.ccl_stu_tv_grade);

        String path = student.getPhotoPath();
        if(path != null && path.length() > 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);

            if (bitmap != null)
                photo.setImageBitmap(bitmap);
        }

        tvName.setText(student.getFullName());
        tvGrade.setText(student.getGrade());
    }

    class courseListReader extends AsyncTask<String, String, Boolean>{
        Student student;
        ArrayList<Course> courses;
        Webservice ws;

        courseListReader(Student s){
            student = s;
            ws = new Webservice();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listViewCourses.setVisibility(View.GONE);
            tvWait.setVisibility(View.VISIBLE);
            progressWait.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            courses = ws.getCourses(student);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            CourseAdapter adapter = new CourseAdapter(CourseListActivity.this, courses);
            listViewCourses.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            tvWait.setVisibility(View.GONE);
            progressWait.setVisibility(View.GONE);
            listViewCourses.setVisibility(View.VISIBLE);
        }
    }
}
