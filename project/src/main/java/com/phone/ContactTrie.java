package com.phone;

import java.util.ArrayList;
import java.util.TreeMap;

public class ContactTrie {

    private ContactTrieNode rootNode;

    public ContactTrie(ContactTrieNode rootNode) {
        this.rootNode = rootNode;
    }

    public ArrayList<Contact> exactSearch(String prefix){

        prefix = prefix.toLowerCase();

        ArrayList<Contact> contactArrayList = new ArrayList<>();

        ContactTrieNode lastNode = rootNode;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getChildrenNodes().get(c);
            if (lastNode == null)
                return contactArrayList;
        }
        if(lastNode.getWord()){
            return lastNode.getContacts();
        }

        return contactArrayList;
    }

    public ArrayList<Contact> partialSearch(String prefix){
        prefix = prefix.toLowerCase();

        ArrayList<Contact> contactArrayList = new ArrayList<>();

        ContactTrieNode lastNode = rootNode;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getChildrenNodes().get(c);
            if (lastNode == null)
                return contactArrayList;
        }
        if(lastNode.getWord()){
            contactArrayList.addAll(lastNode.getContacts());
        }

        lastNode.addAllContactsBelow(contactArrayList);

        return contactArrayList;
    }

    public void addToTrie(String word, Contact contact){
        word = word.toLowerCase();
        this.rootNode.insert(word, contact);
    }

    public void removeFromTrie(String prefix, Contact contact){

        prefix = prefix.toLowerCase();

        ContactTrieNode lastNode = rootNode;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getChildrenNodes().get(c);
        }
        if(lastNode.getContacts().size()==1){
            lastNode.setContacts(new ArrayList<>());
            lastNode.setWord(false);
        } else{
            ArrayList<Contact> contactArrayList = lastNode.getContacts();
            for(int i=0;i< contactArrayList.size();i++){
                if(contactArrayList.get(i).getId().equals(contact.getId())){
                    Contact temp = contactArrayList.get(contactArrayList.size()-1);
                    contactArrayList.set(contactArrayList.size()-1, contactArrayList.get(i));
                    contactArrayList.set(i, temp);
                    break;
                }
            }
            contactArrayList.remove(contactArrayList.size()-1);
        }
    }

}
