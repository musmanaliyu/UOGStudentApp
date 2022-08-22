package com.example.hostelmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ManagementHostel.db";
    private HashMap hp;


    /*For Student Entity*/
    public static final String TABLE_STUDENT = "student";
    public static final String STUDENT_ROLL_NO = "roll_no";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_PROG = "prog";
    public static final String STUDENT_ROOM_ID = "room_id";
    public static final String STUDENT_HOSTEL_NAME = "hostel_name";
    public static final String STUDENT_GENDER = "gender";

    /*For Room Entity*/
    public static final String TABLE_ROOM = "room";
    public static final String ROOM_ID = "room_id";
    public static final String ROOM_SPACE = "room_space";

    /*For Hostel Entity*/
    public static final String TABLE_HOSTEL = "hostel";
    public static final String HOSTEL_NAME = "hostel_name";
    public static final String HOSTEL_TYPE = "hostel_type";

    /*For Complaint Entity*/
    public static final String TABLE_COMPLAINTS = "complaints";
    public static final String STUDENT_ID_C = "student_id_c";
    public static final String ROOM_ID_C = "room_id_c";
    public static final String COMPLAINT = "complaint";
    public static final String DATE_TIME = "date_time";


    private static final String TABLE_CREATE_HOSTEL =
            "CREATE TABLE " + TABLE_HOSTEL + " (" +
                    HOSTEL_NAME + " TEXT, " +
                    HOSTEL_TYPE + " INTEGER " +
                    ")";

    private static final String TABLE_CREATE_ROOM =
            "CREATE TABLE " + TABLE_ROOM + " (" +
                    ROOM_ID + " TEXT PRIMARY KEY, " +
                    ROOM_SPACE + " INTEGER " +
                    ");";

    private static final String TABLE_CREATE_STUDENT =
            "CREATE TABLE " + TABLE_STUDENT + " ( " +
                    STUDENT_ROLL_NO + " INTEGER PRIMARY KEY, " +
                    STUDENT_NAME + " TEXT, " +
                    STUDENT_PROG + " TEXT, " +
                    STUDENT_ROOM_ID + " TEXT, " +
                    STUDENT_HOSTEL_NAME + " TEXT, " +
                    STUDENT_GENDER + " INTEGER " +
                    ")";

    private static final String TABLE_CREATE_COMPLAINTS =
            "CREATE TABLE " + TABLE_COMPLAINTS + " (" +
                    STUDENT_ID_C + " INTEGER, " +
                    ROOM_ID_C + " TEXT, " +
                    COMPLAINT + " TEXT, " +
                    DATE_TIME + " TEXT " +
                    ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSTEL);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLAINTS);
//        db.execSQL(TABLE_CREATE_COMPLAINTS);
//        context.deleteDatabase(DATABASE_NAME);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
//        Log.i("ASD","WWW");
        db.execSQL(TABLE_CREATE_ROOM);
        db.execSQL(TABLE_CREATE_STUDENT);
        db.execSQL(TABLE_CREATE_HOSTEL);
        db.execSQL(TABLE_CREATE_COMPLAINTS);
//        db.execSQL(TABLE_CREATE_STUDENT);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
//            Log.d("ASD", "WWW");
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSTEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLAINTS);
        onCreate(db);
    }

    /* For Student Record */

    //region Student
    public boolean InsertStudent(String name, long roll_no, String hostel_name, String room_id, String prog, int gender) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_ROLL_NO, roll_no);
        contentValues.put(STUDENT_NAME, name);

        contentValues.put(STUDENT_PROG, prog);
        contentValues.put(STUDENT_ROOM_ID, room_id);
        contentValues.put(STUDENT_HOSTEL_NAME, hostel_name);

        contentValues.put(STUDENT_GENDER, gender);
        db.insert(TABLE_STUDENT, null, contentValues);

        return true;
    }

    public Cursor GetStudentData(long roll_no) {
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from " + TABLE_STUDENT + " where " + STUDENT_ROLL_NO + " = " + roll_no, null);
        Cursor res = db.rawQuery("select *from student where roll_no = " + roll_no, null);
        if (res != null && res.moveToFirst()) {
            Log.d("ASD", String.valueOf(res.getString(res.getColumnIndex(STUDENT_NAME))));
        }
        return res;
    }

    public int NumberOfRowsStudent() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_STUDENT);
        return numRows;
    }

    public boolean UpdateStudent(Integer id, String name, long roll_no, String hostel_name, String prog, String room_id, int gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_ROLL_NO, roll_no);
        contentValues.put(STUDENT_NAME, name);

        contentValues.put(STUDENT_PROG, prog);
        contentValues.put(STUDENT_ROOM_ID, room_id);
        contentValues.put(STUDENT_HOSTEL_NAME, hostel_name);

        contentValues.put(STUDENT_GENDER, gender);
        db.update(TABLE_STUDENT, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer DeleteStudent(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_STUDENT,
                "id = ? ",
                new String[]{Long.toString(id)});
    }

    public ArrayList<String> GetAllStudents(boolean room_no) {
        ArrayList<String> array_list = new ArrayList<String>();
        ArrayList<String> array_list_room = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_STUDENT, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            if (room_no) {
                array_list_room.add(res.getString(res.getColumnIndex(STUDENT_ROOM_ID)));
                array_list = array_list_room;
            } else {
                array_list.add(res.getString(res.getColumnIndex(STUDENT_ROLL_NO)));
            }
            res.moveToNext();
        }
        return array_list;
    }
    //endregion

    /*For Room Record*/

    // region Room
    public boolean InsertRoom(String room_name, int room_space) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(ROOM_ID, room_name);

        contentValues.put(ROOM_SPACE, room_space);

        db.insert(TABLE_ROOM, null, contentValues);
        return true;
    }

    public int NumberOfRowsRoom() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_ROOM);
        return numRows;
    }

    public ArrayList<String> GetAllRooms() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ROOM, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(ROOM_ID)));
            res.moveToNext();
        }
        return array_list;
    }
    //endregion

    /*For Hostel Record*/

    //region Hostel
    public boolean InsertHostel(String hostel_name, int hostel_type) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(HOSTEL_NAME, hostel_name);

        contentValues.put(HOSTEL_TYPE, hostel_type);

        db.insert(TABLE_HOSTEL, null, contentValues);
        return true;
    }

    public int NumberOfRowsHostel() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_HOSTEL);
        return numRows;
    }

    public <T> ArrayList<T> GetAllHostels(boolean hostel_name) {
        ArrayList<T> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_HOSTEL, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            if (hostel_name) {
                array_list.add((T) res.getString(res.getColumnIndex(HOSTEL_NAME)));
            } else {
                array_list.add((T) res.getString(res.getColumnIndex(HOSTEL_TYPE)));
            }
            res.moveToNext();
        }
        return array_list;
    }
    //endregion

    /*For Complaint Record*/

    //region Complaint

    public boolean InsertComplaint(long std_id, String room_id, String complaint, String datetime) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_ID_C, std_id);

        contentValues.put(COMPLAINT, complaint);
        contentValues.put(ROOM_ID_C, room_id);
        contentValues.put(DATE_TIME, datetime);

        db.insert(TABLE_COMPLAINTS, null, contentValues);
        return true;
    }

//    public int NumberOfRowsHostel() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_HOSTEL);
//        return numRows;
//    }

    public ArrayList<String> GetAllComplaint() {

        ArrayList<String> data = new ArrayList<>();

        String dataCollector;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_COMPLAINTS, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            dataCollector = res.getString(res.getColumnIndex(DATE_TIME)) + " " +
                    res.getString(res.getColumnIndex(STUDENT_ID_C)) + res.getString(res.getColumnIndex(ROOM_ID_C)) + "\n" +
                    res.getString(res.getColumnIndex(COMPLAINT));
            data.add(dataCollector);
            res.moveToNext();
        }
        return data;
    }

    public ArrayList<String> GetAllComplaintStudentID() {

        ArrayList<String> data = new ArrayList<>();
        String dataCollector;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_COMPLAINTS, null);

        res.moveToFirst();

        while (!res.isAfterLast()) {
            dataCollector=res.getString(res.getColumnIndex(STUDENT_ID_C));
            data.add(dataCollector);
            res.moveToNext();
        }
        return data;
    }

    public boolean UpdateComplaint(long std_id, String room_id, String complaint, String datetime) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_ID_C, std_id);

        contentValues.put(COMPLAINT, complaint);
        contentValues.put(ROOM_ID_C, room_id);
        contentValues.put(DATE_TIME, datetime);

//        Log.d("ASD", std_id + room_id + complaint + datetime);
        db.update(TABLE_COMPLAINTS, contentValues, "student_id_c = ? ", new String[]{Long.toString(std_id)});
        return true;
    }
    public Integer DeleteComplaint(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_COMPLAINTS,
                "student_id_c = ? ",
                new String[]{Long.toString(id)});
    }
    //endregion
}
