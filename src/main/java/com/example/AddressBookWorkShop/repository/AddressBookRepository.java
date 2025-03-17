package com.example.AddressBookWorkShop.repository;

import com.example.AddressBookWorkShop.models.AddressBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBookEntry, Integer> {
}
