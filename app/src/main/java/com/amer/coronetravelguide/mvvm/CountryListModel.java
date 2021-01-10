package com.amer.coronetravelguide.mvvm;

import com.google.firebase.firestore.DocumentId;

public class CountryListModel {

    @DocumentId
    private String countryListId;
    private String name, number, link, image_url;

    public CountryListModel(String countryListId, String name, String number, String link, String image_url) {
        this.countryListId = countryListId;
        this.name = name;
        this.number = number;
        this.link = link;
        this.image_url = image_url;
    }

    public CountryListModel() {
    }

    public String getCountryListId() {
        return countryListId;
    }

    public void setCountryListId(String countryListId) {
        this.countryListId = countryListId;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
