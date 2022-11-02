package com.phone;

import java.util.HashMap;
import java.util.Map;

public class ContactTrieFactory {

    private Map<SearchField, ContactTrie> contactTrieMap;

    public ContactTrieFactory() {
        this.contactTrieMap = new HashMap<>();
    }

    public void addTrie(SearchField searchField, ContactTrie contactTrie){
        this.contactTrieMap.put(searchField, contactTrie);
    }

    public ContactTrie getContactTrie(SearchField searchField){
        return this.contactTrieMap.get(searchField);
    }
}
