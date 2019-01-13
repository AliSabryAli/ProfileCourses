package com.ali.profilecourses.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.profilecourses.R;
import com.ali.profilecourses.model.Course;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    CourseData courseData = new CourseData();
    public OnItemClickListner itemClickListener;

    @NonNull
    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View courseRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_row, viewGroup, false);
        return new ViewHolder(courseRow);
    }

    public void setOnClickListner(OnItemClickListner itemClickListener) {
        this.itemClickListener = (OnItemClickListner) itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseListAdapter.ViewHolder viewHolder, int i) {
        Course course = courseData.getCoursesList().get(i);
        final Context context = viewHolder.courseName.getContext();
        viewHolder.courseName.setText(course.getCouseName());

        Picasso.get().load(course.getImgResource(context)).fit().into(viewHolder.courseImg);
        Picasso.get().load(course.getImgResource(context)).fit().into(viewHolder.profileImg);

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(), course.getImgResource(context));
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(@Nullable Palette palette) {
                int bgColor = palette.getVibrantColor(ContextCompat.getColor(context, R.color.lightGray));
                viewHolder.courseName.setBackgroundColor(bgColor);
                viewHolder.profileImg.setBorderColor(bgColor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseData.getCoursesList().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView courseName;
        private ImageView courseImg;
        private CircleImageView profileImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            courseName = itemView.findViewById(R.id.tvTitleID);
            courseImg = itemView.findViewById(R.id.imgBgID);
            profileImg = itemView.findViewById(R.id.imgvProfileID);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListner {
        void onItemClick(View view, int pos);
    }
}
