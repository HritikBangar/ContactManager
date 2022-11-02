package com.phone;

import java.util.ArrayList;
import java.util.List;

public class ContactManagerImpl implements ContactManager{

    private ContactRepo contactRepo;

    public ContactManagerImpl(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Override
    public boolean add(Contact contact) {
        return this.contactRepo.addContact(contact);
    }

    @Override
    public boolean update(Contact contact) {
        return this.contactRepo.updateContact(contact);
    }

    @Override
    public SearchResponse search(SearchRequest searchRequest) {
        return this.contactRepo.search(searchRequest);
    }
}
