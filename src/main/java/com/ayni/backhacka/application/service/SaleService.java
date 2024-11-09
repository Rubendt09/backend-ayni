package com.ayni.backhacka.application.service;

import com.ayni.backhacka.domain.model.Client;
import com.ayni.backhacka.domain.model.Product;
import com.ayni.backhacka.domain.model.Sale;
import com.ayni.backhacka.infraestructure.adapters.dto.SaleRequest;
import com.ayni.backhacka.infraestructure.adapters.ports.out.ClientRepository;
import com.ayni.backhacka.infraestructure.adapters.ports.out.ProductRepository;
import com.ayni.backhacka.infraestructure.adapters.ports.out.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    // Crear una venta
    public Sale createSale(SaleRequest request) {
        Optional<Client> client = clientRepository.findById(request.getClientId());
        Optional<Product> product = productRepository.findById(request.getProductId());

        if (client.isPresent() && product.isPresent()) {
            Sale sale = new Sale();
            sale.setClient(client.get());
            sale.setProduct(product.get());
            sale.setQuantity(request.getQuantity());
            sale.setDate(new Date());
            sale.setTotalAmount(request.getQuantity() * product.get().getPrice());

            return saleRepository.save(sale);
        } else {
            throw new IllegalArgumentException("Cliente o producto no encontrado");
        }
    }

    // Obtener todas las ventas
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Obtener una venta por ID
    public Optional<Sale> getSaleById(String id) {
        return saleRepository.findById(id);
    }

    // Actualizar una venta
    public Optional<Sale> updateSale(String id, String clientId, String productId, int quantity) {
        Optional<Sale> existingSale = saleRepository.findById(id);
        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Product> product = productRepository.findById(productId);

        if (existingSale.isPresent() && client.isPresent() && product.isPresent()) {
            Sale sale = existingSale.get();
            sale.setClient(client.get());
            sale.setProduct(product.get());
            sale.setQuantity(quantity);
            sale.setTotalAmount(quantity * product.get().getPrice());

            return Optional.of(saleRepository.save(sale));
        } else {
            return Optional.empty();
        }
    }

    // Eliminar una venta
    public boolean deleteSale(String id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}