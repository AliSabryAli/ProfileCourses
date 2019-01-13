package com.ali.profilecourses.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ali.profilecourses.R;
import com.ali.profilecourses.data.CourseListAdapter;

public class BoardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CourseListAdapter courseListAdapter;
    private Menu menu;
    private Boolean isListV = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        recyclerView = findViewById(R.id.recycleViewID);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        courseListAdapter = new CourseListAdapter();
        recyclerView.setAdapter(courseListAdapter);
        courseListAdapter.setOnClickListner(new CourseListAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int pos) {
                Toast.makeText(BoardActivity.this, "Cliccked " + (pos + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        if (isListV) {
            showGridV();
        } else {
            showListV();
        }
    }

    private void showListV() {
        staggeredGridLayoutManager.setSpanCount(1);
        MenuItem item = menu.findItem(R.id.action_toggle);
        item.setIcon(R.drawable.baseline_grid_on_white_24dp);
        isListV = true;
    }

    private void showGridV() {
        staggeredGridLayoutManager.setSpanCount(2);
        MenuItem item = menu.findItem(R.id.action_toggle);
        item.setIcon(R.drawable.baseline_view_list_white_24dp);
        isListV = false;
    }
}
