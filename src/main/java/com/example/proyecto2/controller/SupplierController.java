package com.example.proyecto2.controller;

import com.example.proyecto2.domain.Supplier;
import com.example.proyecto2.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/supplier")
@RestController
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping("/")
    public ResponseEntity<Void> createSupplier(@RequestBody Supplier supplier) {
        this.supplierService.createSupplier(supplier);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(this.supplierService.getSupplier(supplierId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(this.supplierService.getAllSuppliers());
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateSupplier(@RequestBody Supplier supplier) {
        this.supplierService.updateSupplier(supplier);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long supplierId) {
        this.supplierService.deleteSupplier(supplierId);
        return ResponseEntity.ok().build();
    }
}
