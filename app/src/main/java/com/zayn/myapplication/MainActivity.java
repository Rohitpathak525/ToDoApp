package com.zayn.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdaptor.OnNoteListener{
    private ArrayList<String> name=new ArrayList<>();
    private RecyclerViewAdaptor adaptor;
    Button button;
    EditText schedule;
    public String scheduleName;
    public static final String EXTRA_STRING="HELLO";
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        schedule=(EditText)findViewById(R.id.et);
        add();
        mRecyclerView();
    }

    public void add(){     //Function to add text and insert cards in RecyclerView
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 scheduleName =schedule.getText().toString();
                 if(TextUtils.isEmpty(scheduleName)){
                     Toast.makeText(MainActivity.this, "Please enter the schedule",Toast.LENGTH_SHORT).show();

                 }

                 else {
                     name.add(0, scheduleName);
                     adaptor.notifyItemInserted(0);

                 }
                 }
        });
    }

    public void mRecyclerView() //Setting up the recycler view
    {
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        adaptor=new RecyclerViewAdaptor(name,this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }




    @Override
    public void onNoteClick(int postion) { //Click function for going to new Activity
        Intent intent=new Intent(this,NewActivity.class);
        intent.putExtra(EXTRA_STRING,scheduleName);
        startActivity(intent);

    }
}