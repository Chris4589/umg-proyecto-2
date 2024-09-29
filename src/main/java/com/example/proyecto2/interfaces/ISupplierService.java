package com.example.proyecto2.interfaces;

import com.example.proyecto2.domain.Supplier;

import java.util.List;

public interface ISupplierService {
    void createSupplier(Supplier supplier);

    void deleteSupplier(Long supplierId);

    Supplier getSupplier(Long supplierId);

    void updateSupplier(Supplier supplier);

    List<Supplier> getAllSuppliers();
}
