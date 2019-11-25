package com.sprintb_kelompok1.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class ToDoFormActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_form);
    }

    public void addToDoItem(View view) {
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();

        try {
            final Context context = this;
            JSONObject newPost = new JSONObject();
            newPost.put("message", message);

            // endpoint API server
            String url = "http://10.0.2.2:3000/todo";
            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, newPost,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            startActivity(new Intent(context, MainActivity.class));
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            new AlertDialog.Builder(context)
                                    .setTitle("Error")
                                    .setMessage(error.getMessage())
                                    .show();
                        }
                    }
            );

            // Add the request to the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(postRequest);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }
}
