//package me.project.alphaapi.controller;
//
//import me.project.alphaapi.entity.Address;
//import me.project.alphaapi.service.AddressService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/")
//public class AddressController {
//
//    @Autowired
//    private AddressService addressService;
//
//    @PostMapping("customers/{cusId}/address")
//    public ResponseEntity<?> saveAddress(@PathVariable("cusId") Long cusId,
//                                         @RequestBody Address address) {
//        return ResponseEntity.ok(addressService.save(address));
//    }
//
//    @PutMapping("customers/{cusId}/")
//    public ResponseEntity<?> updateAddress(@PathVariable("cusId") Long cusId,
//                                           @RequestBody Address address) {
//        return ResponseEntity.ok(addressService.update(address));
//    }
//}
