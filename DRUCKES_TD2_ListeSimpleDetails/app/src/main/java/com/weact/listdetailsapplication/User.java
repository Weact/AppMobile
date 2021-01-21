package com.weact.listdetailsapplication;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private final String EXTRA_TAG = "USER";
    private String fname;
    private String name;
    private String gender;
    private String email;
    private int pictureid;

    public User(String fname, String name, String gender, int pictureid) { // CONSTRUCTOR WITH GIVEN ARGS : FNAME ; NAME ; GENDER
        super();
        this.fname = fname;
        this.name = name;
        this.gender = gender;
        this.pictureid = pictureid;
        this.email = fname + "." + name + "@ludus-academie.com";
    }

    protected User(Parcel in) {
        fname = in.readString();
        name = in.readString();
        gender = in.readString();
        email = in.readString();
        pictureid = in.readInt();
    }

    //REQUIRED PARCELABLE METHOD
    public static final Creator<User> CREATOR = new Creator<User>() {

        //Create object from parcel
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        //Create array of objects
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //SETTERS
    public void setFName(String fname){ //FNAME
        this.fname = fname;
    }
    public void setName(String name){ //NAME
        this.name = name;
    }
    public void setGender(String gender){ //GENDER
        this.gender = gender;
    }
    public void setPictureId(int pictureid){ //picture
        this.pictureid = pictureid;
    }
    //EMAIL HAS NO SETTER, AUTO SET BY CUSTRUCTOR

    //GETTERS
    public String getFName(){ //FNAME
        return fname;
    }
    public String getName(){ //NAME
        return name;
    }
    public String getGender(){ // GENDER
        return gender;
    }
    public String getEmail(){ //EMAIL
        return email;
    }
    public int getPictureId(){ //picture
        return pictureid;
    }
    public String getEXTRA_TAG() {
        return EXTRA_TAG;
    }

    //PARCEL METHODS
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(fname);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeInt(pictureid);
    }


}