package com.example.mobileweek5;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "ACTIVITY_LIFECYCLE"; // just a constant for accessing logs
    // The tag is used to add to the logs so we can filter them in the log later

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d(TAG, "onCreate"); // We can check the logs on logcat

        Button btIncrement = findViewById(R.id.bt_increment);
        btIncrement.setOnClickListener(v->increment());
    }

    private void increment() {
        counter++;
        TextView tvCounter = findViewById(R.id.tv_counter);
        tvCounter.setText(String.valueOf(counter));
    }

    @Override // Adding override tag helps us know if we are correctly overriding the default method - in this case, we have to call the super
    public void onStart() {
        // We call the super because we have to import the default code of the function, and we add to it
        // but we don't alter its original way of working
        // This super call is required for all lifecycle methods
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        // Rotating the screen will actually destroy the activity and create it again
    }

    // This works when the android system automatically destroys the activity
    // But if the user manually quits/destroys the activity, it will not work
    // It is also called when the activity is stopped, because there is a chance
    // the system will destroy the stopped app to get back system resources
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("counter", counter); // This is what we are saving
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("counter"); // This is what we are restoring
        // The data in the memory has been restored, but the screen has not refreshed
        // So we need to update the view again
        TextView tvCounter = findViewById(R.id.tv_counter);
        tvCounter.setText(String.valueOf(counter));
        Log.d(TAG, "onRestoreInstanceState");
    }
}