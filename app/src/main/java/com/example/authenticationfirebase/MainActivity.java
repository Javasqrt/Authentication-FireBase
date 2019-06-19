package com.example.authenticationfirebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    public Button butregister;
    public Button butlogin;
    public EditText editemail;
    public EditText editpassword;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        butregister = (Button) findViewById(R.id.butregistration);
        butlogin = (Button) findViewById(R.id.butlogin);
        editemail = (EditText) findViewById(R.id.editemail);
        editpassword = (EditText) findViewById(R.id.editpassword);

        butregister.setOnClickListener(this);
        butlogin.setOnClickListener(this);
        progressdialog = new ProgressDialog(this);
    }

    private void registerUser(){
        String email = editemail.getText().toString().trim();
        String password = editpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            editemail.setError("Write the email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            editpassword.setError("Write password");
            return;
        }
        progressdialog.setMessage("Registering new user...");
        progressdialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Registration successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Registration faild", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void loginUser() {

        String email = editemail.getText().toString().trim();
        String password = editpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            editemail.setError("Write the email");
            return;
        } else if (TextUtils.isEmpty(password)) {
            editpassword.setError("Write password");
            return;
        }
        progressdialog.setMessage("Login...");
        progressdialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Login faild", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == butregister){
            registerUser();
        }
        if (view == butlogin){
            loginUser();

        }

    }
}
