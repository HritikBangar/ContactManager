package com.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContactTrieNode {

    private Character currentChar;

    private TreeMap<Character, ContactTrieNode> childrenNodes;

    private ContactTrieNode parentNode;

    private Boolean isWord;

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    private ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public ContactTrieNode(Character currentChar, ContactTrieNode parentNode) {
        this.currentChar = currentChar;
        this.parentNode = parentNode;
        this.contacts = new ArrayList<>();
        this.childrenNodes = new TreeMap<>();
        this.isWord = false;
    }

    public void insert(String word, Contact contact){
        if (word == null || word.isEmpty())
            return;
        char firstChar = word.charAt(0);
        ContactTrieNode child = childrenNodes.get(firstChar);
        if (child == null) {
            child = new ContactTrieNode(firstChar, this);
            childrenNodes.put(firstChar, child);
        }

        if (word.length() > 1){
            child.insert(word.substring(1), contact);
        }
        else{
            child.setWord(true);
            child.getContacts().add(contact);
        }
    }

    public void remove(String word, Contact contact){
        // TODO: pending implement
    }

    public void addAllContactsBelow(ArrayList<Contact> contactArrayList){
        if(this.childrenNodes.size()==0) return;
        for(Map.Entry<Character, ContactTrieNode> entry: this.childrenNodes.entrySet()){
            if(entry.getValue().isWord){
                contactArrayList.addAll(entry.getValue().getContacts());
            }
            entry.getValue().addAllContactsBelow(contactArrayList);
        }
    }

    public TreeMap<Character, ContactTrieNode> getChildrenNodes() {
        return childrenNodes;
    }

    public Boolean getWord() {
        return isWord;
    }

    public void setWord(Boolean word) {
        isWord = word;
    }
}
