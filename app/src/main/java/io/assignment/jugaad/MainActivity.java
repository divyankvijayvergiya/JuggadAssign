package io.assignment.jugaad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button registerUser, registerDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerUser = findViewById(R.id.register_user);
        registerDriver = findViewById(R.id.register_driver);
        clickItems();

    }

    private void clickItems() {
        registerDriver.setOnClickListener(this);
        registerUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == registerDriver) {
            Intent intent = new Intent(MainActivity.this, DriverActivity.class);
            startActivity(intent);
        } else if (v == registerUser) {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        }
    }
}
