package com.example.pintasso.event;

import com.example.pintasso.model.ImageUrl;

import java.util.List;

public class SearchResultEvent {

    private List<ImageUrl> urlList;

    public SearchResultEvent(List<ImageUrl> urlList) {
        this.urlList = urlList;
    }

    public List<ImageUrl> getUrlList() {
        return urlList;
    }
}
