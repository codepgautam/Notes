package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView register = (TextView)findViewById(R.id.textView8);
        TextView alreadyHaveAnAccount = findViewById(R.id.textView4);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameText = findViewById(R.id.editTextTextPersonName);
                final EditText userText = findViewById(R.id.username);
                final EditText phoneText = findViewById(R.id.editTextPhone);
                final EditText emailText = findViewById(R.id.editTextTextEmailAddress);
                final EditText passwordText = findViewById(R.id.editTextTextPassword);
                String name = nameText.getText().toString();
                String username = userText.getText().toString();
                int phone = Integer.parseInt(phoneText.getText().toString());
                String email = emailText .getText().toString();
                String password = passwordText.getText().toString();


                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("info");
                DataContract data = new DataContract(name,username, phone,email,password);
                reference.child(userText.getText().toString()).setValue(data);

                Intent i = new Intent(RegisterActivity.this , displayActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Account Registered to FireBase", Toast.LENGTH_SHORT).show();
            }
        });

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this , LoginActivity.class);
                startActivity(i);
            }
        });
    }
}