package com.example.hostelmanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomFragment extends Fragment {

    RecyclerView recyclerView;
    private static ArrayList<String> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        RecycleView recycleView = new RecycleView(data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycleView);
        return view;
    }

    public static RoomFragment newInstance(ArrayList<String> details) {
//        Bundle args = new Bundle();
//        Room fragment = new Room();
//        fragment.setArguments(args);
        RoomFragment roomFragment = new RoomFragment();
        data = details;
//        Log.d("ASD", String.valueOf(data.size()));
        return roomFragment;
    }

}