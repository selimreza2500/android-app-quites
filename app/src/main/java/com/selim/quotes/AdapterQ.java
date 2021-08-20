package com.selim.quotes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterQ extends RecyclerView.Adapter<AdapterQ.UserViewHolder> {

    private Context context;
    List<Model> userList;



    public AdapterQ(Context context, List<Model> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data_layout,parent,false);
        return  new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {


        Model us = userList.get(position);


        String[] mColors = {"#F70039",  "#AA1A1A", "#F6C400", "#BE72BA",  "#FFB64D",
                 "#F65C5C", "#4E1AAB", "#4AD562", "#911AAA"};
        holder.da.setTextColor(Color.parseColor(mColors[position % 40]));


            holder.da.setText(" ❝   "+us.getText()+"   ❞ ");



    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView da;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            da = itemView.findViewById(R.id.textView);


        }
    }
}
