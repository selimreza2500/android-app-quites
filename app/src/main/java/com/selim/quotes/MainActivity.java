package com.selim.quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String name;
    FloatingActionButton flot;
    DatabaseReference reference;
    ArrayList<Model>dataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.homeRV);
        dataArrayList = new ArrayList<>();
        flot = findViewById(R.id.flt);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);





        flot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }
        });



    }

    private void getUserList() {


        reference.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                    for (DataSnapshot snapshot : task.getResult().getChildren()){
                        Model model = snapshot.getValue(Model.class);
                        Log.v("Tag",snapshot.toString());

                        name = snapshot.getKey();
                        model.setKey(name);
                        dataArrayList.add(model);
                    }


                    Adapter adapter = new Adapter(MainActivity.this, dataArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                }



        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        dataArrayList. clear();
        getUserList();
    }
}