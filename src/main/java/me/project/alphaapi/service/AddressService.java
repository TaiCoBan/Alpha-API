package me.project.alphaapi.service;

import me.project.alphaapi.entity.Address;

public interface AddressService {
    Address save(Address address);
    Address update(Address address);
}
