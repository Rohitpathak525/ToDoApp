package com.zayn.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {
    private ArrayList<String> activity=new ArrayList<>();
    ListView listView;
    EditText editText;
Button deleteTask;
EditText editText_deleteTask;

    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        listView=(ListView)findViewById(R.id.listview);
        editText=(EditText)findViewById(R.id.edittext);
        deleteTask=(Button)findViewById(R.id.delete);
        editText_deleteTask=(EditText)findViewById(R.id.edelete);


        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        Intent intent=getIntent();
        String text=intent.getStringExtra(MainActivity.EXTRA_STRING); //Getting schedule entered in the RecyclerView
        TextView textView=(TextView)findViewById(R.id.textview);
        textView.setText(text);
        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.customlist,activity);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking this will get the task of the schedule and add it to ListView
                String task=editText.getText().toString();
                if(TextUtils.isEmpty(task))
                    Toast.makeText(NewActivity.this, "Please enter the task!",
                            Toast.LENGTH_SHORT).show();
                else {

                    activity.add(task);
                    listView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();

                }
            }
        });
        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=editText_deleteTask.getText().toString();
                int positon=Integer.parseInt(s);
                activity.remove(positon);
                arrayAdapter.notifyDataSetChanged();
            }
        });


    }
}
