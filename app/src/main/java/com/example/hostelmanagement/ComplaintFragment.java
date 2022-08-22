package com.example.hostelmanagement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ComplaintFragment extends Fragment {

    private static ArrayList<String> std_roll_no;
    private static ArrayList<String> std_room_no;

    private TextView rollno;
    private Button complain;
    private TextView complaintText;

    long roll_no;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd/HH:mm:ss");
    String currentDateandTime = sdf.format(new Date());

    private OnFragmentInteractionListenerComplaint listenerComplaint;

    long roll_no_Tobe_Update;
    boolean update = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);
        Referencing(view);
        MatchingRollNo();
        return view;
    }

    private void MatchingRollNo() {
        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int values = 0; values < std_roll_no.size(); values++)
                    if (std_roll_no.get(values).equals(rollno.getText().toString())) {
                        listenerComplaint.InsertDataToComplaintTable(Long.parseLong(std_roll_no.get(values)), std_room_no.get(values),
                                complaintText.getText().toString(),
                                currentDateandTime);

                        if (update && std_roll_no.get(values).equals(Long.toString(roll_no_Tobe_Update))) {
                            listenerComplaint.UpdateDataToComplaintTable(roll_no_Tobe_Update,
                                    std_room_no.get(values), complaintText.getText().toString(), currentDateandTime);
                            update = false;
                        }
                    }
            }
        });

    }

    void UpdateRecord(long roll) {
        update = true;
        roll_no_Tobe_Update = roll;
//        Log.d("ASD", String.valueOf(roll));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerComplaint) {
            listenerComplaint = (OnFragmentInteractionListenerComplaint) context;
        }
    }

    private void Referencing(View view) {
        rollno = view.findViewById(R.id.roll_no_for_complaint);
        complain = view.findViewById(R.id.submit_complain);
        complaintText = view.findViewById(R.id.complain_txt_et);
    }

    public static ComplaintFragment newInstance(ArrayList<String> roll_no, ArrayList<String> room_no) {
        ComplaintFragment fragment = new ComplaintFragment();
        std_roll_no = roll_no;
        std_room_no = room_no;
        return fragment;
    }

    public void DeleteRecord(long roll_no) {
//        listenerComplaint.DeleteDataToComplaintTable(roll_no);
    }

    interface OnFragmentInteractionListenerComplaint {
        void InsertDataToComplaintTable(long roll_no, String room_id, String complaint, String date_time);

        void UpdateDataToComplaintTable(long roll_no, String room_id, String complaint, String date_time);

//        void DeleteDataToComplaintTable(long rollno);

    }
}