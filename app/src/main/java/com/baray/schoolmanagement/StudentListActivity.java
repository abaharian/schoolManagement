package com.baray.schoolmanagement;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.baray.adapter.StudentAdapter;
import com.baray.primitive.Student;
import com.baray.tools.Database;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton menuButton;
    private ListView listView;
    private FloatingActionButton actionButton;
    private TextView tvAlertNoChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_student_list);
        }catch(Throwable t){
            t.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvAlertNoChild = (TextView) findViewById(R.id.student_list_tv_alert);

        actionButton = (FloatingActionButton) findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewChild();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
//        LinearLayout sideNavLayout = (LinearLayout)header.findViewById(R.id.nav_header_layout);
//        sideNavLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        menuButton = (ImageButton) findViewById(R.id.toolbar_menu_btn);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        listView = (ListView)findViewById(R.id.list_view_student);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fillStudentList();
    }

    private void fillStudentList(){
        Database db = new Database();
        db.initialize();

        ArrayList<Student> students = db.getMyChild();
        if(students == null || students.size() == 0){
            tvAlertNoChild.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else {
            tvAlertNoChild.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            StudentAdapter adapter = new StudentAdapter(this, students);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_list, menu);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addNewChild(){
        AddChildDialog dialog = new AddChildDialog(this);
        dialog.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    private class AddChildDialog extends android.app.Dialog {

        private Button btnAdd;
        private EditText etNationalCode;
        private EditText etFirstName;
        private EditText etLastName;

        public AddChildDialog(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_new_child);
            btnAdd = (Button) findViewById(R.id.dialog_nc_btn_add);
            etNationalCode = (EditText) findViewById(R.id.dialog_nc_national_code);
            etFirstName = (EditText) findViewById(R.id.dialog_nc_first_name);
            etLastName = (EditText) findViewById(R.id.dialog_nc_last_name);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Student s = new Student();
                    s.setNationalCode(etNationalCode.getText().toString());
                    s.setFirstName(etFirstName.getText().toString());
                    s.setLastName(etLastName.getText().toString());
                    Database db = new Database();
                    db.initialize();
                    db.addNewChild(s);
                    fillStudentList();
                    AddChildDialog.this.dismiss();
                }
            });

        }


    }

    public void deleteStudent(final Student item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String msg = "آیا مطمئن هستید می خواهید دانش آموز ";
        msg += item.getFirstName() + " " + item.getLastName();
        msg += " را حدف نمایید؟";
        builder.setMessage(msg);
        builder.setTitle("حذف دانش آموز");
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
                Database db = new Database();
                db.initialize();
                db.removeChild(item);
                fillStudentList();
            }
        });
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        builder.create().show();

    }
}
