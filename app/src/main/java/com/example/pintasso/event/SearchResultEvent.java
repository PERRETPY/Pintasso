package com.example.pintasso.event;

import com.example.pintasso.model.ImageItem;
import com.example.pintasso.model.ImageUrl;

import java.util.List;

public class SearchResultEvent {

    private List<ImageItem> itemList;

    public SearchResultEvent(List<ImageItem> itemList) {
        this.itemList = itemList;
    }

    public List<ImageItem> getItemList() {
        return itemList;
    }
}
