package com.example.hostelmanagement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFragment extends Fragment {

    private Spinner hostelNameSpinner;
    private Spinner roomNameSpinner;
    private Spinner programSpinner;

    private DatePicker datePicker;

    private TextView hostelType;
    private TextView hostelName;

    private Button submitBtn;


    private EditText name;
    private EditText rollNo;
    private RadioGroup radioGroup;


    private static List<String> roomsName = new ArrayList<>();
    private static List<String> hostelsName = new ArrayList<>();
    private static List<Integer> hostelsType = new ArrayList<>();

    private OnFragmentInteractionListener interactionListener;


    private String[] programOffering = {"BS-CS", "MS-CS", "BBA", "Economics", "BS-Physics", "BS-Math"};
    private String programSelect;
    private String hostelSelect;
    private String roomSelect;
    private int gender;
    private final int MALE = 0;
    private final int FEMALE = 1;

    public static boolean handler = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        Referencing(view);
        AssignValue(view);
        GetDataFromInputFields();
        return view;
    }

    private void GetDataFromInputFields() {
        if (name.getText() != null && rollNo.getText() != null) {
            interactionListener.GetDataFromUser(name.getText().toString(), rollNo.getText().toString(),
                    hostelSelect, roomSelect, programSelect, gender);
        } else {
            Toast.makeText(getActivity(), "Please submit all Attributes", Toast.LENGTH_SHORT).show();
        }
    }

    void Referencing(View view) {
        rollNo = view.findViewById(R.id.roll_no_Et);
        name = view.findViewById(R.id.user_name);
        hostelNameSpinner = view.findViewById(R.id.hostel_name);
        roomNameSpinner = view.findViewById(R.id.room_no);
        programSpinner = view.findViewById(R.id.select_prog);
        datePicker = view.findViewById(R.id.DatePicker_id);
        hostelName = view.findViewById(R.id.hostel_name_tv);
        hostelType = view.findViewById(R.id.hostel_type_tv);
        submitBtn = view.findViewById(R.id.submit_btn);
        radioGroup = view.findViewById(R.id.radio_group);

    }

    void AssignValue(View view) {


        ArrayAdapter roomAvailable = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, roomsName);
        roomNameSpinner.setAdapter(roomAvailable);

        roomNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomSelect = roomsName.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter hostelAvailable = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, hostelsName);
        hostelNameSpinner.setAdapter(hostelAvailable);

        ArrayAdapter programAvailable = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, programOffering);
        programSpinner.setAdapter(programAvailable);

        programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                programSelect = programOffering[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hostelNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type_int = String.valueOf(hostelsType.get(position));
                String type_str = type_int.equals("0") ? "Boys" : "Girls";
                hostelName.setText(hostelsName.get(position));
                hostelType.setText(type_str);
                hostelSelect = hostelsName.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            View radioBtn = view.findViewById(checkedId);
            int index = radioGroup.indexOfChild(radioBtn);
            switch (index) {
                case MALE:
                    gender = 0;
                    break;
                case FEMALE:
                    gender = 1;
                    break;
                default:

            }
        });

        submitBtn.setOnClickListener(v -> {
            Log.d("ASD", "BtnClicked");
            handler = true;
            GetDataFromInputFields();
//            for (boolean in : allAttributes) {
//                Log.d("ASD", String.valueOf(in));
//                if (in) {
//                    handler = true;
//                } else {
//                    handler = false;
//                }
//            }
//            Log.d("ASD", String.valueOf(handler));
        });

    }


    public static Fragment newInstance(ArrayList<String> rooms, ArrayList<String> hostel_name, ArrayList<Integer> hostel_type) {
        RegistrationFragment fragment = new RegistrationFragment();
        roomsName = rooms;
        hostelsName = hostel_name;
        hostelsType = hostel_type;
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) context;
        }
    }

    interface OnFragmentInteractionListener {
        void GetDataFromUser(String user_name, String user_rollno, String hostel_name, String room_id, String program_select, int gender);
    }

}