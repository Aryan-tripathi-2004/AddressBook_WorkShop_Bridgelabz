package com.example.AddressBookWorkShop.controllers;

import com.example.AddressBookWorkShop.dto.AddressBookEntryDTO;
import com.example.AddressBookWorkShop.dto.ResponseDTO;
import com.example.AddressBookWorkShop.interfaces.IAddressBookService;
import com.example.AddressBookWorkShop.models.AddressBookEntry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping({"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAddressBookData() {
        List<AddressBookEntry> addressBookEntries = addressBookService.getAddressBookData();
        return ResponseEntity.ok(new ResponseDTO("fetch all address book data", addressBookEntries));
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable int contactId) {
        Optional<AddressBookEntry> addressBookEntry = addressBookService.getAddressById(contactId);
        return ResponseEntity.ok(new ResponseDTO("fetch address book data for contact id: " +contactId , addressBookEntry));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ResponseDTO> addAddressBookData(@RequestBody AddressBookEntryDTO contactData) {
        Optional<AddressBookEntry> addressBookEntry = addressBookService.addAddressBookData(modelMapper.map( contactData, AddressBookEntry.class));
        return new ResponseEntity<>(new ResponseDTO("contact added: " , addressBookEntry), HttpStatus.OK);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ResponseDTO> updateAddressBookData(@PathVariable int contactId, @RequestBody AddressBookEntry contactData) {
        Optional<AddressBookEntry> addressBookEntry = addressBookService.updateAddressBookData(contactId, modelMapper.map( contactData, AddressBookEntry.class));
        return ResponseEntity.ok(new ResponseDTO("contact updated for id:"+contactId , contactData));
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<ResponseDTO> deleteAddressBookData(@PathVariable int contactId) {
        Optional<AddressBookEntry> addressBookEntry= addressBookService.getAddressById(contactId);
        addressBookService.deleteAddressBookData(contactId);
        return ResponseEntity.ok(new ResponseDTO("contact deleted for id: " +contactId , addressBookEntry));
    }
}
