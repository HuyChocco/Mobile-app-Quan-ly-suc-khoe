package com.chocco.huy.qlsk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText u,p;
    Button go;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        u = (EditText) findViewById(R.id.edtEmail_lg);
        p = (EditText) findViewById(R.id.edtPass_lg);
        go = (Button) findViewById(R.id.btnSignIn);
        mAuth = FirebaseAuth.getInstance();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }
    private void signin ()
    {
        String user = u.getText().toString();
        String pass = p.getText().toString();
        mAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            Toast.makeText(login.this,"Ban da dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(login.this,"Da co loi xay ra",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
