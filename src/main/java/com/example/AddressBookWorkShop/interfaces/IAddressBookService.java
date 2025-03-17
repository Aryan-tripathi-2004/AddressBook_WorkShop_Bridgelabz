package com.example.AddressBookWorkShop.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.AddressBookWorkShop.models.AddressBookEntry;

public interface IAddressBookService {

    public List<AddressBookEntry> getAddressBookData();
    public Optional<AddressBookEntry> getAddressById(int contactId);
    public Optional<AddressBookEntry> addAddressBookData(AddressBookEntry addressBookData);
    public Optional<AddressBookEntry> updateAddressBookData(int contactId, AddressBookEntry addressBookData);
    public void deleteAddressBookData(int contactId);
}
