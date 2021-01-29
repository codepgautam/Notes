package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    final EditText username = (EditText)findViewById(R.id.textView6);
    final EditText password = (EditText)findViewById(R.id.textView7);
    Button login = findViewById(R.id.button3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkPassword() | !checkUsername()) {
                    return;
                }
                else{
                    final String userEnteredUsername = username.getText().toString().trim();
                    final String userEnteredPassword = password.getText().toString().trim();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("info");
                    Query checkuser=reference.orderByChild("username").equalTo(userEnteredUsername);
                    checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                username.setError(null);
                                String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                                assert passwordFromDB != null;
                                if (passwordFromDB.equals(userEnteredPassword)) {
                                    username.setError(null);
                                    String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                                    String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                                    String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                                    String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                                    Intent intent = new Intent(LoginActivity.this, displayActivity.class);
                                    intent.putExtra("name", nameFromDB);
                                    intent.putExtra("username", usernameFromDB);
                                    intent.putExtra("email", emailFromDB);
                                    intent.putExtra("phoneNo", phoneNoFromDB);
                                    intent.putExtra("password", passwordFromDB);
                                    startActivity(intent);
                                } else {
                                    password.setError("Wrong Password");
                                    password.requestFocus();
                                }
                            } else {
                                username.setError("No such User exist");
                                username.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }
        });


    }


    private boolean checkUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }
        else {
            username.setError(null);
            return true;
        }
    }

    private boolean checkPassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


}