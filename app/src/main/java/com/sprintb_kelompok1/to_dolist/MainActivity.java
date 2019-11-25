package com.sprintb_kelompok1.to_dolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;
import android.content.Intent;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.sprintb_kelompok1.to_dolist.TodoListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {

    private TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create and configure the adapter
        this.todoListAdapter = new TodoListAdapter(this);
        ListView microPostListView = findViewById(R.id.to_do_items);
        microPostListView.setAdapter(todoListAdapter);

        // issue the request
        String url = "http://10.0.2.2:3000/todo";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest microPostRequest = new JsonArrayRequest(url, this, this);
        queue.add(microPostRequest);
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            List<ToDoItem> toDoItems = new ArrayList<>(response.length());
            for (int i=0; i<response.length(); i++){
                JSONObject item = response.getJSONObject(i);
                // Suspicious
                String id = item.getString("id");
                String message = item.getString("message");

                toDoItems.add(new ToDoItem(id, message));
            }
            todoListAdapter.setToDoList(toDoItems);
        } catch (JSONException error) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(error.toString())
                    .show();

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        todoListAdapter.setToDoList(new ArrayList<ToDoItem>());
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(error.getMessage())
                .show();
    }

    public void openToDoForm(View view) {
        startActivityForResult(new Intent(this, ToDoFormActivity.class), 1);
    }

}
