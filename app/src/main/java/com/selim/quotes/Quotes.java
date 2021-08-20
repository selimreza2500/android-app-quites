package com.selim.quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Quotes extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseReference reference;
    Model mod;
    String str,name;
    ArrayList<Model>dataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        recyclerView = findViewById(R.id.homeRV);
        reference = FirebaseDatabase.getInstance().getReference();
        dataArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        getUserList();

    }

    private void getUserList() {

        Intent intent = getIntent();
        str = intent.getStringExtra("MODEL");
        reference.child("Users").child(str).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {



                for (DataSnapshot snapshot : task.getResult().getChildren()){



                        if (snapshot.getKey().equals("null")){

                        }
                        else{
                            mod = snapshot.getValue(Model.class);
                            Log.v("Tag1",snapshot.toString());
                            dataArrayList.add(mod);

                        }

                }

                AdapterQ adapter = new AdapterQ(Quotes.this, dataArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

        });

    }

}