package com.example.loginscreen;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewAccount extends AppCompatActivity {
    EditText inUN;
    EditText inEmail;
    EditText inPassword;
    EditText inRePassword;
    Button signUp;
    FirebaseAuth mAthu;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        inUN = findViewById(R.id.singupUN);
        inEmail = findViewById(R.id.signupEmail);
        inPassword = findViewById(R.id.signupPass);
        inRePassword = findViewById(R.id.signupRePa);
        signUp = findViewById(R.id.signUpBTN);
        mAthu = FirebaseAuth.getInstance();

        signUp.setOnClickListener(view -> {
            createUser();
        });

    }
    private void createUser(){

        String Email = inEmail.getText().toString();
        String Pass = "";
        if(inPassword.getText().toString().equals(inRePassword.getText().toString())){
             Pass = inPassword.getText().toString();
        }
        else{
            Toast.makeText(this, "passwords are not matches ", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(Email)){
            inEmail.setError("cannot be empty");
            inEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(Pass)){
            inPassword.setError("cannot be empty");
            inPassword.requestFocus();
        }
        else{
            mAthu.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(NewAccount.this,"we are here",Toast.LENGTH_SHORT).show();
                    if(task.isSuccessful()) {
                        Toast.makeText(NewAccount.this, "registered ", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(NewAccount.this, "registration Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("registration Error",task.getException().getMessage());
                    }
                }
            });
        }
    }
}
