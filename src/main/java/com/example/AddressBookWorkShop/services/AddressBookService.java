package com.example.AddressBookWorkShop.services;

import java.util.List;
import java.util.Optional;

import com.example.AddressBookWorkShop.models.AddressBookEntry;
import com.example.AddressBookWorkShop.interfaces.IAddressBookService;
import com.example.AddressBookWorkShop.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public List<AddressBookEntry> getAddressBookData() {
        return addressBookRepository.findAll();
    }

    public Optional<AddressBookEntry> getAddressById(int contactId) {
        return addressBookRepository.findById(contactId);
    }

    public Optional<AddressBookEntry> addAddressBookData(AddressBookEntry addressBookEntry) {
        return Optional.of(addressBookRepository.save(addressBookEntry));
    }

    public Optional<AddressBookEntry> updateAddressBookData(int contactId, AddressBookEntry addressBookEntryDTO) {
        Optional<AddressBookEntry> addressBookEntry = addressBookRepository.findById(contactId);
        if (addressBookEntry.isPresent()) {
            addressBookEntry.get().setName(addressBookEntryDTO.getName());
            addressBookEntry.get().setAddress(addressBookEntryDTO.getAddress());
            addressBookEntry.get().setEmail(addressBookEntryDTO.getEmail());
            addressBookEntry.get().setPhoneNumber(addressBookEntryDTO.getPhoneNumber());
            return Optional.of(addressBookRepository.save(addressBookEntry.get()));
        } else {
            return Optional.empty();
        }
    }

    public void deleteAddressBookData(int contactId) {
        addressBookRepository.deleteById(contactId);
    }
















}
