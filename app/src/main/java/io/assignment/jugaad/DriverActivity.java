package io.assignment.jugaad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DriverActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailDriver, passwordDriver;
    Button registerDriver;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        emailDriver = findViewById(R.id.email_driver);
        passwordDriver = findViewById(R.id.password_driver);
        registerDriver = findViewById(R.id.register_driver);
        mAuth = FirebaseAuth.getInstance();
        clickItems();

    }

    private void authenticateDriver() {
        String email = emailDriver.getText().toString();
        String password = passwordDriver.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("userSuccess", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                Intent intent = new Intent(DriverActivity.this, DashboardDriverActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(DriverActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("userFail", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(DriverActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void clickItems() {
        registerDriver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == registerDriver) {
            authenticateDriver();
        }
    }
}
