package com.example.proyecto2.service;

import com.example.proyecto2.domain.Supplier;
import com.example.proyecto2.exception.BadRequestException;
import com.example.proyecto2.exception.NoContentException;
import com.example.proyecto2.interfaces.ISupplierService;
import com.example.proyecto2.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public void createSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new BadRequestException("Supplier is required");
        }
        supplier.validate();
        this.supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        if (supplierId == null || supplierId <= 0) {
            throw new BadRequestException("Supplier ID is required");
        }
        var supplier = this.supplierRepository.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new NoContentException("Supplier not found");
        }
        this.supplierRepository.delete(supplier);
    }

    @Override
    public Supplier getSupplier(Long supplierId) {
        if (supplierId == null || supplierId <= 0) {
            throw new BadRequestException("Supplier ID is required");
        }
        var supplier = this.supplierRepository.findById(supplierId).orElse(null);
        if (supplier == null) {
            throw new NoContentException("Supplier not found");
        }
        return supplier;
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new BadRequestException("Supplier ID is required");
        }
        supplier.validate();
        supplier.validateSupplierId();

        var supplierToUpdate = this.supplierRepository.findById(supplier.getSupplierId()).orElse(null);
        if (supplierToUpdate == null) {
            throw new NoContentException("Supplier not found");
        }
        this.supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        var suppliers = this.supplierRepository.findAll();
        if (suppliers.isEmpty()) {
            throw new NoContentException("No suppliers found");
        }
        return suppliers;
    }
}
