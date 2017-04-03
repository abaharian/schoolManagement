package com.baray.tools;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.baray.primitive.Student;
import com.baray.schoolmanagement.Application;
import com.baray.schoolmanagement.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Akram on 3/13/2017.
 */
public class Database {
    private static SQLiteDatabase database;
    private static  String DIR_SDCARD   = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static  String uniqueStr = Application.getApplication().getString(R.string.school_unique_str);
    private static  String DIR_DATABASE = DIR_SDCARD + "/baray/" + uniqueStr + "/database/";
    private static  String DIR_FILES = DIR_SDCARD + "/baray/" + uniqueStr + "/files/";

    private static final String TBL_CHILD = "child_tbl";
    private static final String COL_CHILD_NATIONAL_CODE = "national_code";
    private static final String COL_CHILD_FIRST_NAME = "first_name";
    private static final String COL_CHILD_LASTNAME = "last_name";
    private static final String COL_CHILD_GRADE = "grade";
    private static final String COL_CHILD_CLASS_NO = "class_no";
    private static final String COL_CHILD_FILE_PATH = "file_path";


    public void initialize() {

        new File(DIR_DATABASE).mkdirs();
        try{
            database = SQLiteDatabase.openOrCreateDatabase(DIR_DATABASE + "/" + uniqueStr + "db.sqlite", null);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        //Create Tables
        String createTblStudent = "CREATE TABLE IF NOT EXISTS " +
                TBL_CHILD + " ( " +
                COL_CHILD_NATIONAL_CODE + " TEXT PRIMARY KEY NOT NULL UNIQUE, " +
                COL_CHILD_FIRST_NAME + " TEXT, " +
                COL_CHILD_LASTNAME + " TEXT, " +
                COL_CHILD_GRADE + " TEXT, " +
                COL_CHILD_CLASS_NO + " TEXT, " +
                COL_CHILD_FILE_PATH + " TEXT) ";
        database.execSQL(createTblStudent);
    }

    public ArrayList<Student> getMyChild(){
        ArrayList<Student> result = new ArrayList<Student>();
        Student temp;

        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + TBL_CHILD, null);
            while (cursor.moveToNext()) {
                temp = new Student();
                temp.setFirstName(cursor.getString(cursor.getColumnIndex(COL_CHILD_FIRST_NAME)));
                temp.setGrade(cursor.getString(cursor.getColumnIndex(COL_CHILD_GRADE)));
                temp.setLastName(cursor.getString(cursor.getColumnIndex(COL_CHILD_LASTNAME)));
                temp.setNationalCode(cursor.getString(cursor.getColumnIndex(COL_CHILD_NATIONAL_CODE)));
                String path = cursor.getString(cursor.getColumnIndex(COL_CHILD_FILE_PATH));
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                temp.setPhotoPath(path);
                result.add(temp);
            }
            cursor.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public void addNewChild(Student child){
        try{
            ContentValues cv = new ContentValues();
            cv.put(COL_CHILD_CLASS_NO, child.getClassNo());
            cv.put(COL_CHILD_FILE_PATH, child.getPhotoPath());
            cv.put(COL_CHILD_FIRST_NAME, child.getFirstName());
            cv.put(COL_CHILD_GRADE, child.getGrade());
            cv.put(COL_CHILD_LASTNAME, child.getLastName());
            cv.put(COL_CHILD_NATIONAL_CODE, child.getNationalCode());

            database.insert(TBL_CHILD, null, cv);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void removeChild(Student child){
        database.delete(TBL_CHILD, COL_CHILD_NATIONAL_CODE + " = " + child.getNationalCode(), null);
    }
}
