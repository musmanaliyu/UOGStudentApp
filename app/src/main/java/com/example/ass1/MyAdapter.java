package com.example.ass1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VHolder> {

     List <Transcript_Info> Data;

    public MyAdapter(List<Transcript_Info> data) {
        Data = data;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.transcript_items,parent,false);
        return new VHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        Transcript_Info mydata= Data.get(position);
        holder.t1.setText(mydata.getName());
        holder.t2.setText(mydata.getID());
        holder.t3.setText(mydata.getRoll());
        holder.t4.setText(mydata.getAddress());
        holder.t5.setText(mydata.getDOB());
        holder.t6.setText(mydata.getReg());
        holder.t7.setText(mydata.getSemester());
        holder.t8.setText(mydata.getContact());
        holder.t9.setText(mydata.getBank());
        holder.t10.setText(mydata.getChallan());
        holder.t11.setText(mydata.getAmount());
        holder.t12.setText(mydata.getDate());

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }


    public static class VHolder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
        Button b1;

        public VHolder(@NonNull View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.textView2);
            t2=itemView.findViewById(R.id.textView3);
            t3=itemView.findViewById(R.id.textView4);
            t4=itemView.findViewById(R.id.textView5);
            t5=itemView.findViewById(R.id.textView6);
            t6=itemView.findViewById(R.id.textView7);
            t7=itemView.findViewById(R.id.textView8);
            t8=itemView.findViewById(R.id.textView9);
            t9=itemView.findViewById(R.id.textView);
            t10=itemView.findViewById(R.id.textView11);
            t11=itemView.findViewById(R.id.textView12);
            t12=itemView.findViewById(R.id.textView13);



        }
    }
}
