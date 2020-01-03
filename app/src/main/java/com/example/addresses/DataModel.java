package com.example.addresses;


import androidx.annotation.Nullable;

import com.google.firebase.firestore.GeoPoint;

public class DataModel {
    private String Name;
    private String Description;
    private String place;
    private @Nullable String image;
    GeoPoint Address;

    public DataModel() {
    }

    public DataModel(String title, String description, String place, @Nullable String idpic,GeoPoint Address) {
        this.Name = title;
        this.Description = description;
        this.place = place;

        if(idpic!=null)
        {
            this.image=idpic;
        }

        this.Address=Address;


    }


    public String getTitle()
    {
        return Name;
    }

    public String getDescription()
    {
        return Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    public void setAddress(GeoPoint address) {
        Address = address;
    }

    public String getPlace()
    {
        return place;
    }

    public String getIdpic()
    {
        return image;
    }

    public double getAddresslat()
    {

        return Address.getLatitude();
    }

    public double getAddresslong()
    {
        return Address.getLongitude();
    }

    public GeoPoint getAddress() {
        return Address;
    }
}
