package com.example.loginscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText UserNameET;
    EditText PasswordET;
    public String Email = "";
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserNameET = findViewById(R.id.ETUN);
        PasswordET = findViewById(R.id.ETPA);
        mAuth =FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Toast.makeText(this, "please Sign in", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginButton(View view) {
        login();
    }

    private void login(){
         Email = UserNameET.getText().toString();
        String Pass = PasswordET.getText().toString();

        if(TextUtils.isEmpty(Email)){
            UserNameET.setError("cannot be empty");
            UserNameET.requestFocus();
        }
        else if(TextUtils.isEmpty(Pass)){
            PasswordET.setError("cannot be empty");
            PasswordET.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(MainActivity.this,"done",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,HomeScreen.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this,"there is an error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void cancelButton(View view) {
        if(!UserNameET.getText().toString().isEmpty() || !PasswordET.getText().toString().isEmpty())
            Toast.makeText(this, "Inputs cleared", Toast.LENGTH_SHORT).show();
        UserNameET.setText("");
        PasswordET.setText("");

    }

    public void createNewUser(View view) {
        startActivity(new Intent(this,NewAccount.class));
    }
}