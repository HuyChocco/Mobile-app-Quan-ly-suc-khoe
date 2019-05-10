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

public class signup extends AppCompatActivity {
    EditText user ;
    EditText pass ;
    EditText repass ;
    Button go ;
    FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        user = (EditText) findViewById(R.id.edtEmail);
        pass = (EditText) findViewById( R.id .edtPass);
        repass = (EditText) findViewById(R.id.edtRePass);
        go = (Button) findViewById(R.id.btnSignUp);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }
    private void signup ()
    {
        String u = user.getText().toString();
        String p = pass.getText().toString();
        String r = repass.getText().toString();
        if (p.compareTo(r)==0)
        {
            mAuth.createUserWithEmailAndPassword(u, p)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                Toast.makeText(signup.this,"Ban da dang ky thanh cong",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(signup.this,"Da co loi xay ra",Toast.LENGTH_SHORT).show();
                        }
                    });        }
        else
        {
            Toast.makeText(signup.this,"Mat khau va mat khau nhap lai phai trung nhau",Toast.LENGTH_SHORT).show();
        }
    }

}
