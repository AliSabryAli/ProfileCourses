package com.ali.profilecourses.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.profilecourses.R;
import com.ali.profilecourses.data.CourseData;
import com.ali.profilecourses.model.Course;

public class DetailesActivity extends AppCompatActivity implements View.OnClickListener {
    private int courseId;
    private Course course;
    private ImageView courseImg;
    private TextView tvCourseTitle;
    private FloatingActionButton btAddComment;
    private EditText etCommentText;
    private ListView commentsListView;
    private LinearLayout revealView;
    private InputMethodManager inputMethodManager;
    private Boolean isTextVisible = false;

    final private String TAG = "Detailes Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);
        setUpUi();
        loadCourse();

    }

    private void loadCourse() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            courseId = bundle.getInt("course_id");
            course = new CourseData().getCoursesList().get(courseId);
            courseImg.setImageResource(course.getImgResource(this));
            tvCourseTitle.setText(course.getCouseName());
        }
    }

    private void setUpUi() {
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        courseImg = findViewById(R.id.imgCourseDetailsId);
        tvCourseTitle = findViewById(R.id.tvTitleDetailsId);
        btAddComment = findViewById(R.id.btAddCommentDetailsId);
        etCommentText = findViewById(R.id.etCommentsDetailsId);
        commentsListView = findViewById(R.id.listCommentsId);
        revealView = findViewById(R.id.revealViewDetailsId);
        btAddComment.setOnClickListener(this);
        revealView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAddCommentDetailsId:
                if (!isTextVisible) {
                    startReveal(revealView);
                    etCommentText.requestFocus();
                    inputMethodManager.showSoftInput(etCommentText, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    hideReveal(revealView);
                    inputMethodManager.hideSoftInputFromWindow(etCommentText.getWindowToken(), 0);
                }
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startReveal(LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;
        final int maxRaduis = Math.max(revealView.getWidth(), revealView.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0f, maxRaduis);
        revealView.setVisibility(View.VISIBLE);
        isTextVisible = true;
        animator.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void hideReveal(final LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;
        int initialRaduis = revealView.getWidth();

        Animator animator = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, initialRaduis, 0f);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealView.setVisibility(View.INVISIBLE);
            }
        });
        isTextVisible = false;
        animator.start();
    }
}
