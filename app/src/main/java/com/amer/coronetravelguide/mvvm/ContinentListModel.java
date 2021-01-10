package com.amer.coronetravelguide.mvvm;

import com.google.firebase.firestore.DocumentId;

public class ContinentListModel {

    @DocumentId
    private String continentListId;
    private String name, number, total_country_number, image_url;

    public ContinentListModel(String continentListId, String name, String number, String total_country_number, String image_url) {
        this.continentListId = continentListId;
        this.name = name;
        this.number = number;
        this.total_country_number = total_country_number;
        this.image_url = image_url;
    }


    public ContinentListModel() {
    }

    public String getContinentListId() {
        return continentListId;
    }

    public void setContinentListId(String continentListId) {
        this.continentListId = continentListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTotal_country_number() {
        return total_country_number;
    }

    public void setTotal_country_number(String total_country_number) {
        this.total_country_number = total_country_number;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
