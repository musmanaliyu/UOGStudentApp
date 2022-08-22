package com.example.hostelmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleView extends RecyclerView.Adapter<RecycleView.RVHolder> {

    private ArrayList<String> arrayList;
    public static ArrayList<String> rollno = new ArrayList<>();
    private OnDeleteComplaint deleteComplaint;


    public RecycleView(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecycleView.RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        RVHolder rvHolder = new RVHolder(view);
        deleteComplaint= (OnDeleteComplaint) parent.getContext();
        return rvHolder;
    }

    public interface OnDeleteComplaint
    {
        void DeleteComplaint(long rollno);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView.RVHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
        holder.delete_Btn.setOnClickListener(v -> {
           deleteComplaint.DeleteComplaint(Long.parseLong(rollno.get(position)));
        });
        holder.update_Btn.setOnClickListener(v -> {

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            ComplaintFragment myFragment = new ComplaintFragment();
            myFragment.UpdateRecord(Long.parseLong(rollno.get(position)));

            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit();

        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RVHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        public TextView textView;
        public Button update_Btn;
        public Button delete_Btn;

        public RVHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.complain_txt);
            update_Btn = itemView.findViewById(R.id.update);
            delete_Btn = itemView.findViewById(R.id.delete);
        }
    }


}

