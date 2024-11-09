package com.ayni.backhacka.infraestructure.adapters.controller;

import com.ayni.backhacka.application.service.SaleService;
import com.ayni.backhacka.domain.model.Sale;
import com.ayni.backhacka.infraestructure.adapters.dto.SaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // Crear una nueva venta
    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequest request) {
        try {
            Sale sale = saleService.createSale(request);
            return ResponseEntity.ok(sale);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Obtener todas las ventas
    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable String id) {
        Optional<Sale> sale = saleService.getSaleById(id);
        return sale.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una venta
    @PutMapping("/update/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable String id, @RequestBody SaleRequest request) {
        Optional<Sale> updatedSale = saleService.updateSale(id, request.getClientId(), request.getProductId(), request.getQuantity());
        return updatedSale.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una venta
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable String id) {
        if (saleService.deleteSale(id)) {
            return ResponseEntity.ok("Sale deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
