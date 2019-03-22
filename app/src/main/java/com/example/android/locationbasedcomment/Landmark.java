package com.example.android.locationbasedcomment;
public class Landmark {
    public Integer landmarkPicture;
    public String landmarkName;
    public String coordinates;

    Landmark(Integer landmarkPicture, String landmarkName, String coordinates) {
        this.landmarkPicture = landmarkPicture;
        this.landmarkName = landmarkName;
        this.coordinates = coordinates;
    }
}
