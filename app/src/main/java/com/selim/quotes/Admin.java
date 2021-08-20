package com.selim.quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Admin extends AppCompatActivity  {


    EditText lname,ctext;
    Button btn,btnc;
    DatabaseReference reference;
    Spinner spinner;

    String ash = " ";
    ArrayList<String>list;
    ArrayAdapter<String>adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        lname = findViewById(R.id.lnameedt);
        btn= findViewById(R.id.button2);
        btnc= findViewById(R.id.buttonc);
        ctext= findViewById(R.id.ctext);
        spinner= (Spinner)findViewById(R.id.myspinner);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        reference = FirebaseDatabase.getInstance().getReference();

        list=new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ash = spinner.getSelectedItem().toString();
                String lk = lname.getText().toString();
                String id = reference.push().getKey();

                Model model = new Model(ash,lk,id);

                reference.child("Users").child(ash).child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if(task.isSuccessful()){
                            Toast.makeText(Admin.this, "Data stored", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(Admin.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });

        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lkc = "No Quotes Available";
                String idc = "null";
                String fkc = ctext.getText().toString();
                Model model = new Model(fkc,lkc,idc);
                reference.child("Users").child(ctext.getText().toString()).child(idc).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Admin.this, "Data stored", Toast.LENGTH_SHORT).show();
                            list.clear();
                            fetchData();
                            adapter2.notifyDataSetChanged();
                        }
                        else{

                            Toast.makeText(Admin.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });


        spinner.setAdapter(adapter2);
        fetchData();


    }



    private void fetchData() {

        reference.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){
                    for (DataSnapshot snapshot : task.getResult().getChildren()){
                        String k =snapshot.getKey();

                        list.add(k);


                    }
                    Log.v("Tagi",list.toString());
                    adapter2.notifyDataSetChanged();
                }else{
                    Toast.makeText(Admin.this, "Slow Connection", Toast.LENGTH_SHORT).show();
                }


            }


        });


    }



}