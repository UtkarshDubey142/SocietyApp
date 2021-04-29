package com.utkarsh.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;     // private variable for register_Button:

    private EditText editTextEmail , editTextPassword;     // private variable for Log-in page fields:
    private Button logIn;                                 // private variable for logIn_Button:
    private TextView forgotPassword;
    private FirebaseAuth mAuth;                          // private variable for Firebase auth object:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);                  // onClick Event On register_Button:

        logIn = (Button) findViewById(R.id.logIn);         // For logIn Button:
        logIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.eMail);             // For eMail filed:
        editTextPassword = (EditText) findViewById(R.id.passWord);       // For passWord filed:

        mAuth = FirebaseAuth.getInstance();        // initializing mAuth variable:

        forgotPassword = (TextView) findViewById(R.id.forgotFiled);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Registration.class));   // It Will Take Us To Registration-Form:
                break;

            case R.id.logIn:
                userLogin();
                break;

            case R.id.forgotFiled:
                startActivity(new Intent(this, ForgotPassword.class));
        }
    }

    private void userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty())
          {
             editTextEmail.setError("e-mail is required");
             editTextEmail.requestFocus();
             return;
          }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
          {
              editTextEmail.setError("Please provide a valid e-mail");
              editTextEmail.requestFocus();
              return;
          }

        if (password.isEmpty())
          {
              editTextPassword.setError("Kindly enter password");
              editTextPassword.requestFocus();
              return;
          }

        if (password.length() < 6)
          {
            editTextPassword.setError("Password is too short");
            editTextPassword.requestFocus();
            return;
          }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                  {
                      // redirect to navigation_bar:
                      startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                  }
                else
                  {
                      Toast.makeText(MainActivity.this, "Failed to log-in, Try again!", Toast.LENGTH_SHORT).show();
                  }

            }
        });     //  Mauth object:

    }
}