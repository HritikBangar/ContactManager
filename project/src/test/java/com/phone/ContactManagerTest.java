package com.phone;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactManagerTest {

    Contact contact1 = new Contact("1", "Hritik", "Singh", "123456789");
    Contact contact2 = new Contact("2", "Hritik", "Bangar", "123456234789");
    Contact contact3 = new Contact("3", "Hardik", "Singh", "123456412789");
    Contact contact4 = new Contact("4", "Anmol", "Singh", "123456789");

    @Test
    void test1() {
        ContactManagerImpl contactManager = new ContactManagerImpl(new ContactRepo());
        contactManager.add(contact1);
        contactManager.add(contact2);
        contactManager.add(contact3);
        contactManager.add(contact4);

        List<Contact> resp =contactManager.search(new SearchRequest(SearchField.FIRST_NAME, SearchType.COMPLETE, "Hritik")).getResults();

        ArrayList<String> expectedIds = new ArrayList<>();
        expectedIds.add("1");
        expectedIds.add("2");

        ArrayList<String> presentIds = new ArrayList<>();
        for(Contact contact: resp){
            presentIds.add(contact.getId());
        }
        Collections.sort(presentIds);
        Collections.sort(expectedIds);
        assert presentIds.equals(expectedIds);
    }

    @Test
    void test2() {
        ContactManagerImpl contactManager = new ContactManagerImpl(new ContactRepo());
        contactManager.add(contact1);
        contactManager.add(contact2);
        contactManager.add(contact3);
        contactManager.add(contact4);

        Contact contact4 = new Contact("1", "Hritik", "asdasdasdasd", "123456789");
        contactManager.update(contact4);
        List<Contact> resp =contactManager.search(new SearchRequest(SearchField.LAST_NAME, SearchType.PARTIAL, "Singh")).getResults();

        ArrayList<String> expectedIds = new ArrayList<>();
        expectedIds.add("3");
        expectedIds.add("4");

        ArrayList<String> presentIds = new ArrayList<>();
        for(Contact contact: resp){
            presentIds.add(contact.getId());
        }
        Collections.sort(presentIds);
        Collections.sort(expectedIds);
        assert presentIds.equals(expectedIds);
    }

    @Test
    void test3() {
        ContactManagerImpl contactManager = new ContactManagerImpl(new ContactRepo());
        contactManager.add(contact1);
        contactManager.add(contact2);
        contactManager.add(contact3);
        contactManager.add(contact4);

        List<Contact> resp =contactManager.search(new SearchRequest(SearchField.PHONE, SearchType.COMPLETE, "123456789")).getResults();

        ArrayList<String> expectedIds = new ArrayList<>();
        expectedIds.add("1");
        expectedIds.add("4");

        ArrayList<String> presentIds = new ArrayList<>();
        for(Contact contact: resp){
            presentIds.add(contact.getId());
        }
        Collections.sort(presentIds);
        Collections.sort(expectedIds);
        assert presentIds.equals(expectedIds);
    }

    @Test
    void test4() {
        ContactManagerImpl contactManager = new ContactManagerImpl(new ContactRepo());
        contactManager.add(contact1);
        contactManager.add(contact2);
        contactManager.add(contact3);
        contactManager.add(contact4);

        List<Contact> resp =contactManager.search(new SearchRequest(SearchField.LAST_NAME, SearchType.COMPLETE, "Hritik")).getResults();

        ArrayList<String> expectedIds = new ArrayList<>();
        expectedIds.add("1");
        expectedIds.add("2");

        ArrayList<String> presentIds = new ArrayList<>();
        for(Contact contact: resp){
            presentIds.add(contact.getId());
        }
        Collections.sort(presentIds);
        Collections.sort(expectedIds);
        assert !presentIds.equals(expectedIds);
    }

    @Test
    void test5() {
        ContactManagerImpl contactManager = new ContactManagerImpl(new ContactRepo());
        contactManager.add(contact1);
        contactManager.add(contact2);
        contactManager.add(contact3);
        contactManager.add(contact4);

        List<Contact> resp =contactManager.search(new SearchRequest(SearchField.LAST_NAME, SearchType.COMPLETE, "ppppp")).getResults();

        ArrayList<String> expectedIds = new ArrayList<>();

        ArrayList<String> presentIds = new ArrayList<>();
        for(Contact contact: resp){
            presentIds.add(contact.getId());
        }
        Collections.sort(presentIds);
        Collections.sort(expectedIds);
        assert presentIds.equals(expectedIds);
    }
}
