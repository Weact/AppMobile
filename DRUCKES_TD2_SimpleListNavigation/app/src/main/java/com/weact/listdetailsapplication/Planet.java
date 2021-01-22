package com.weact.listdetailsapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Planet implements Parcelable {
    private final String EXTRA_TAG = "PLANET";
    private String name;
    private int radius;
    private String weight;
    private String age;
    private int orbital;
    private int pictureid;

    public Planet(String name, int radius, String weight, String age, int orbital, int pictureid) { // String name, int radius, String weight, int age, int orbital
        super();
        this.name = name;
        this.radius = radius;
        this.weight = weight;
        this.age = age;
        this.orbital = orbital;
        this.pictureid = pictureid;
    }

    protected Planet(Parcel in) {
        name = in.readString();
        radius = in.readInt();
        weight = in.readString();
        age = in.readString();
        orbital = in.readInt();
        pictureid = in.readInt();
    }

    //REQUIRED PARCELABLE METHOD
    public static final Creator<Planet> CREATOR = new Creator<Planet>() {

        //Create object from parcel
        @Override
        public Planet createFromParcel(Parcel in) {
            return new Planet(in);
        }

        //Create array of objects
        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };

    //SETTERS
    public void setName(String name){ //FNAME
        this.name = name;
    }
    public void setRadius(int radius){ //NAME
        this.radius = radius;
    }
    public void setWeight(String weight){ //WEIGHT
        this.weight = weight;
    }
    public void setAge(String age){ //AGE
        this.age = age;
    }
    public void setRTS(int orbital){
        this.orbital = orbital;
    }
    public void setPictureId(int pictureid){ //picture
        this.pictureid = pictureid;
    }

    //GETTERS
    public String getName(){ // NAME
        return name;
    }
    public int getRadius(){ // RADIUS
        return radius;
    }
    public String getWeight(){ // WEIGHT
        return weight;
    }
    public String getAge(){ // AGE
        return age;
    }
    public int getOrbital(){ // ROTATION TIME IN SECOND
        return orbital;
    }
    public String getEXTRA_TAG() {
        return EXTRA_TAG;
    }
    public int getPictureId(){ //picture
        return pictureid;
    }

    //PARCEL METHODS
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeInt(radius);
        dest.writeString(weight);
        dest.writeString(age);
        dest.writeInt(orbital);
        dest.writeInt(pictureid);
    }


}