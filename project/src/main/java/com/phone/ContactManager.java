package com.phone;

public interface ContactManager {
    boolean add(Contact contact);

    boolean update(Contact contact);

    SearchResponse search(SearchRequest searchRequest);
}
