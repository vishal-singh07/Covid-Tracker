package com.example.apidemoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apidemoapp.R;
import com.example.apidemoapp.data.State;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<State> states;
    Context context;

    public RVAdapter(List<State> states, Context context) {
        this.states = states;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvStateName.setText(states.get(position).getName());
        holder.tvCases.setText(Integer.toString(states.get(position).getCases()));
    }

    @Override
    public int getItemCount() {
        return states.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvStateName,tvCases;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCases = itemView.findViewById(R.id.tvCases);
        tvStateName = itemView.findViewById(R.id.tvStateName);
    }
}
