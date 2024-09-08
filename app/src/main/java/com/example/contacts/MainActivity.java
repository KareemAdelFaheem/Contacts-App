package com.example.contacts;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity {
FloatingActionButton plus_btn;
private RecyclerView recyclerView;
private List<ListItem> listItems;
private RecyclerView.Adapter adapter;
MyDatabaseHelper MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        plus_btn=findViewById(R.id.floatingActionButton);
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });


        MyDB=new MyDatabaseHelper(MainActivity.this);
        listItems=new ArrayList<>();
        storeDataInListItems();
        adapter=new MyAdapter(this,listItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    void storeDataInListItems(){
        Cursor cursor= MyDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();

        }else{
            while(cursor.moveToNext()){
                listItems.add(new ListItem(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }
        }
    }

    public void showDialog(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.55);
        dialog.getWindow().setLayout(width,height);
        Button add_btn=dialog.findViewById(R.id.dialog_add_btn);
        EditText name_et=dialog.findViewById(R.id._dialog_name_et);
        EditText number_et=dialog.findViewById(R.id.dialog_number_et);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper MyDB=new MyDatabaseHelper(MainActivity.this);
                MyDB.addContact(name_et.getText().toString().trim(),number_et.getText().toString().trim());
                listItems.clear();
                storeDataInListItems();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}