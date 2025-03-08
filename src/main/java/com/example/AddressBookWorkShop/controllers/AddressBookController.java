package com.example.AddressBookWorkShop.controllers;

import com.example.AddressBookWorkShop.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @GetMapping({"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAddressBookData() {
        return ResponseEntity.ok(new ResponseDTO("fetch all address book data", null));
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable int contactId) {
        return ResponseEntity.ok(new ResponseDTO("fetch address book data for contact id: " +contactId , null));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ResponseDTO> addAddressBookData(@RequestBody Object contactData) {
        return new ResponseEntity<>(new ResponseDTO("contact added: " , contactData), HttpStatus.OK);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ResponseDTO> updateAddressBookData(@PathVariable int contactId, @RequestBody Object contactData) {
        return ResponseEntity.ok(new ResponseDTO("contact updated for id:"+contactId , contactData));
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<ResponseDTO> deleteAddressBookData(@PathVariable int contactId) {
        return ResponseEntity.ok(new ResponseDTO("contact deleted for id: " +contactId , null));
    }
}
