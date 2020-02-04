package com.example.nikm_todo_v13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nikm_todo_v13.DB.EveryData;

import java.io.Serializable;
import java.util.ArrayList;

public class EveryAdapter extends RecyclerView.Adapter<EveryAdapter.CustomViewHolder> implements Serializable {

    private ArrayList<EveryData> arrayList;

    public EveryAdapter(ArrayList<EveryData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public EveryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EveryAdapter.CustomViewHolder holder, int position) {
        holder.tv_time.setText(arrayList.get(position).getTv_time());
        holder.tv_do.setText(arrayList.get(position).getTv_do());
        holder.tv_value.setText(arrayList.get(position).getTv_value());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_time.getText().toString();
                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                remove(holder.getAdapterPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public EveryData getArrayAt(int position) {
        return arrayList.get(position);
    }

    public void remove(int position) {
        try {

            arrayList.remove(position);
            notifyItemRemoved(position);

        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_time;
        protected TextView tv_do;
        protected TextView tv_value;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            this.tv_do = (TextView) itemView.findViewById(R.id.tv_do);
            this.tv_value = (TextView) itemView.findViewById(R.id.tv_value);
        }
    }

}