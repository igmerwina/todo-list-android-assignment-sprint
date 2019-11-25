package com.sprintb_kelompok1.to_dolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class ToDoFormActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_form);
    }

    public void addToDoItem(View view){
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("item", message);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
