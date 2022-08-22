package com.example.hostelmanagement;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RegistrationFragment.OnFragmentInteractionListener, ComplaintFragment.OnFragmentInteractionListenerComplaint, RecycleView.OnDeleteComplaint {


    enum FragmentState {
        ROOM,
        REGISTRATION,
        COMPLAINTS
    }

    private Button room_btn;
    private Button registration_btn;
    private Button complaint_btn;
    private FragmentState fragmentState;

    boolean is_fragmentOpen = false;

    DBHelper dbHelper;

    ArrayList<String> complaintDetails = new ArrayList<>();
    ArrayList<String> student = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        References();
        Listeners();


    }


    void InsertingRecord() {

//        dbHelper.GetStudentData(18321519128L);
//        dbHelper.InsertRoom("A-01", 4);
//        dbHelper.InsertRoom("A-02", 4);
//        dbHelper.InsertRoom("A-03", 4);
////
//
//        dbHelper.InsertStudent("M Hassan", 18321519128L, "Boys-Abubakar(A)","A-01" ,"BS-CS", 0);
//        dbHelper.InsertStudent("Ahmed Ibrahim", 18321519136L, "Boys-Ali(C)", "A-02","BS-CS", 0);
//        dbHelper.InsertStudent("Sufyan Abbas", 18321519114L, "Girls-Fatima(B)", "A-01","BS-CS", 0);
//
//        dbHelper.InsertHostel("Boys-Abubakar(A)", 0);
//        dbHelper.InsertHostel("Boys-Ali(C)", 0);
//        dbHelper.InsertHostel("Boys-Usman(A)", 0);
//        dbHelper.InsertHostel("Girls-Fatima(B)", 1);
//        dbHelper.InsertHostel("Girls-Fatima(A)", 1);
//        dbHelper.InsertHostel("Girls-Ayesha(C)", 1);
//        dbHelper.InsertHostel("Girls-Fatima(C)", 1);

        //        Log.d("ASD", String.valueOf(dbHelper.NumberOfRowsRoom()));
//        Log.d("ASD", String.valueOf(dbHelper.GetAllStudents()));
//        Log.d("ASD", String.valueOf(dbHelper.GetAllRooms()));

//        Log.d("ASD", String.valueOf(dbHelper.getData(18321519128L).getString(1)));
//        dbHelper.GetStudentData(18321519128L);
//        Log.d("ASD", String.valueOf(dbHelper.GetAllHostels(true)));

    }

    void References() {
        room_btn = findViewById(R.id.room_btn);
        registration_btn = findViewById(R.id.registration_btn);
        complaint_btn = findViewById(R.id.complain_btn);
        fragmentState = FragmentState.ROOM;
        complaintDetails = dbHelper.GetAllComplaint();
        student = dbHelper.GetAllComplaintStudentID();
        RecycleView.rollno = student;
//        complaintDetails.remove(0);
        FragmentHandler();
    }

    void Listeners() {
        room_btn.setOnClickListener(v -> {
            fragmentState = FragmentState.ROOM;
            FragmentHandler();
            InsertingRecord();
        });

        registration_btn.setOnClickListener(v -> {
            fragmentState = FragmentState.REGISTRATION;
            FragmentHandler();
        });

        complaint_btn.setOnClickListener(v -> {
            fragmentState = FragmentState.COMPLAINTS;
            FragmentHandler();
        });

    }

    private void CloseFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment).commit();
            is_fragmentOpen = false;
//            Log.d("ASD", "Fragment is Close:");
        }
    }

    private void DisplayFragment(Fragment fragment) {
//        Log.d("ASD","Fragment is open");
        is_fragmentOpen = true;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, fragment).commit();
    }

    void FragmentHandler() {

        RoomFragment roomFragment = RoomFragment.newInstance(complaintDetails);
        RegistrationFragment registrationFragment = (RegistrationFragment) RegistrationFragment.newInstance(dbHelper.GetAllRooms(), dbHelper.<String>GetAllHostels(true), dbHelper.<Integer>GetAllHostels(false));
        ComplaintFragment complaintFragment = ComplaintFragment.newInstance(dbHelper.GetAllStudents(false), dbHelper.GetAllStudents(true));

        switch (fragmentState) {
            case ROOM:
                CloseFragment();
                DisplayFragment(roomFragment);
                break;
            case COMPLAINTS:
                CloseFragment();
                DisplayFragment(complaintFragment);
                break;
            case REGISTRATION:
                CloseFragment();
                DisplayFragment(registrationFragment);
                break;
            default:
        }
    }

    @Override
    public void GetDataFromUser(String user_name, String user_rollno, String hostel_name, String room_id, String program_select, int gender) {
//        Log.d("ASD", "ASD" + RegistrationFragment.handler);
        if (RegistrationFragment.handler) {
            Toast.makeText(getApplicationContext(), "Form submit", Toast.LENGTH_SHORT).show();
//            dbHelper.InsertStudent("Aslam", 18321519033L, "Girls-Fatima(A)", "A-03", "BBA", 1);
            if (dbHelper != null) {
                dbHelper.InsertStudent(user_name, Long.parseLong(user_rollno), hostel_name, room_id, program_select, gender);
            }

        }
    }

    @Override
    public void InsertDataToComplaintTable(long roll_no, String room_id, String complaint, String date_time) {
        dbHelper.InsertComplaint(roll_no, room_id, complaint, date_time);
        String complaintValue = date_time + " " + room_id + " " + roll_no + "\n" + complaint;
        RecycleView.rollno.add(Long.toString(roll_no));
        complaintDetails.add(complaintValue);
//        Log.d("ASD", complaintValue);
    }

    @Override
    public void UpdateDataToComplaintTable(long roll_no, String room_id, String complaint, String date_time) {
        dbHelper.UpdateComplaint(roll_no, complaint, room_id, date_time);
//        complaintDetails.add(pos,complaintValue);
    }


    @Override
    public void DeleteComplaint(long rollno) {
        dbHelper.DeleteComplaint(rollno);
    }


}