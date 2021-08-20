package com.selim.quotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {

    private Context context;
    List<Model> userList;


    public Adapter(Context context, List<Model> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return  new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {




        Model userdata = userList.get(position);
        holder.da.setText(userdata.getKey());


        String[] mColors = {"#F70039", "#00AEE7", "#AA1A1A", "#F6C400", "#BE72BA", "#F08BAD", "#FFB64D",
                "#FF608C", "#F65C5C", "#4E1AAB", "#4AD562", "#911AAA"};
        holder.L.setBackgroundColor(Color.parseColor(mColors[position % 10]));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Quotes.class);
                intent.putExtra("MODEL",userdata.getKey());

                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView da;
        LinearLayout L;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            da = itemView.findViewById(R.id.textView);
            L = itemView.findViewById(R.id.l);

        }
    }
}
