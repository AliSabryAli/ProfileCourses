package com.ali.profilecourses.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ali.profilecourses.R;

public class DetailesActivity extends AppCompatActivity {
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            courseId = bundle.getInt("course_id");
            Toast.makeText(DetailesActivity.this, "" + courseId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
