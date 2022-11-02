package com.phone;

import java.util.ArrayList;
import java.util.TreeMap;

public class ContactRepo {

    private TreeMap<String, Contact> contactMap;

    private ContactTrie firstNameContactTrie;

    private ContactTrie lastNameContactTrie;

    private ContactTrie phoneNumberContactTrie;

    private ContactTrieFactory contactTrieFactory;

    public ContactRepo(){
        this.contactMap = new TreeMap<>();
        this.firstNameContactTrie = new ContactTrie(new ContactTrieNode('\0', null));
        this.lastNameContactTrie = new ContactTrie(new ContactTrieNode('\0', null));
        this.phoneNumberContactTrie = new ContactTrie(new ContactTrieNode('\0', null));
        this.contactTrieFactory = new ContactTrieFactory();
        contactTrieFactory.addTrie(SearchField.FIRST_NAME, firstNameContactTrie);
        contactTrieFactory.addTrie(SearchField.LAST_NAME, lastNameContactTrie);
        contactTrieFactory.addTrie(SearchField.PHONE, phoneNumberContactTrie);
    }

    public boolean addContact(Contact contact){
        if(contactMap.containsKey(contact.getId())) return false;
        this.contactMap.put(contact.getId(), contact);
        this.firstNameContactTrie.addToTrie(contact.getFirstName(), contact);
        this.lastNameContactTrie.addToTrie(contact.getLastName(), contact);
        this.phoneNumberContactTrie.addToTrie(contact.getPhoneNumber(), contact);
        return true;
    }

    public boolean updateContact(Contact contact){
        if(!contactMap.containsKey(contact.getId())) return false;
        Contact olderContact = contactMap.get(contact.getId());
        this.contactMap.put(contact.getId(), contact);
        this.firstNameContactTrie.removeFromTrie(olderContact.getFirstName(), olderContact);
        this.lastNameContactTrie.removeFromTrie(olderContact.getLastName(), olderContact);
        this.phoneNumberContactTrie.removeFromTrie(olderContact.getPhoneNumber(), olderContact);
        this.firstNameContactTrie.addToTrie(contact.getFirstName(), contact);
        this.lastNameContactTrie.addToTrie(contact.getLastName(), contact);
        this.phoneNumberContactTrie.addToTrie(contact.getPhoneNumber(), contact);
        return true;
    }


    public SearchResponse search(SearchRequest searchRequest){
        ContactTrie contactTrie = contactTrieFactory.getContactTrie(searchRequest.getSearchField());
        ArrayList<Contact> outputContactList;
        if(searchRequest.getSearchType().equals(SearchType.COMPLETE)){
            outputContactList = contactTrie.exactSearch(searchRequest.getInput());
        } else{
            outputContactList = contactTrie.partialSearch(searchRequest.getInput());
        }
        return new SearchResponse(outputContactList.size(), outputContactList);
    }

}
