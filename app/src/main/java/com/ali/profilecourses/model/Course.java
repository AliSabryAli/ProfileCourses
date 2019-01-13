package com.ali.profilecourses.model;

import android.content.Context;

public class Course {
    private String couseName;
    private String couseImg;
    private String profileImg;

    public Course(String couseName, String couseImg, String profileImg) {
        this.couseName = couseName;
        this.couseImg = couseImg;
        this.profileImg = profileImg;
    }

    public int getImgResource(Context context) {
        return context.getResources().getIdentifier(this.couseImg, "drawable", context.getPackageName());
    }

    public String getCouseName() {
        return couseName;
    }

    public void setCouseName(String couseName) {
        this.couseName = couseName;
    }

    public String getCouseImg() {
        return couseImg;
    }

    public void setCouseImg(String couseImg) {
        this.couseImg = couseImg;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
