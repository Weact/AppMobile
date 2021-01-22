package com.weact.listdetailsapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private final String EXTRA_TAG = "FOOD";
    private String name;
    private String origin;
    private int price;
    private int pictureid;

    public Food(String name, String origin, int price, int pictureid) { // CONSTRUCTOR WITH GIVEN ARGS : String name, String origin, int price
        super();
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.pictureid = pictureid;
    }

    protected Food(Parcel in) {
        name = in.readString();
        origin = in.readString();
        price = in.readInt();
        pictureid = in.readInt();
    }

    //REQUIRED PARCELABLE METHOD
    public static final Creator<Food> CREATOR = new Creator<Food>() {

        //Create object from parcel
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        //Create array of objects
        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    //SETTERS
    public void setName(String name){ //FNAME
        this.name = name;
    }
    public void setOrigin(String origin){ //origin
        this.origin = origin;
    }
    public void setPrice(int price){ //price
        this.price = price;
    }
    public void setPictureId(int pictureid){ //picture
        this.pictureid = pictureid;
    }

    //GETTERS
    public String getName(){ // NAME
        return name;
    }
    public String getOrigin(){ // origin
        return origin;
    }
    public int getPrice(){ // price
        return price;
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
        dest.writeString(origin);
        dest.writeInt(price);
        dest.writeInt(pictureid);

    }


}