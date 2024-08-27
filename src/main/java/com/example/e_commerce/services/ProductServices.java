package com.example.e_commerce.services;

import com.example.e_commerce.document.Merchant;
import com.example.e_commerce.document.Product;
import com.example.e_commerce.dto.ManifactureDto;
import com.example.e_commerce.dto.MerchantDto;
import com.example.e_commerce.dto.ProductDto;
import com.example.e_commerce.exceptions.CustomExceptions;
import com.example.e_commerce.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductServices {
    @Autowired
    private ProductRepository prepo;

    @Autowired
    ManifactureService manifactureService;
    @Autowired
    MerchantService merchantService;

    public String save(ProductDto pdto) {
        if (! manifactureService.manufaturerExists(pdto.getManifacture()))
            throw new CustomExceptions("manufacturer.not.found", HttpStatus.BAD_REQUEST);
        if (!merchantService.merchantExists(pdto.getMerchent()))
            throw new CustomExceptions("merchant.not.found", HttpStatus.BAD_REQUEST);
        return prepo.save(new Product(pdto)).getId();
    }

    public List<ProductDto> getAll() {
        List<Product> products = prepo.findAll();
        List<MerchantDto> merchants = merchantService.getAll();
        List<ManifactureDto> manifacture = manifactureService.getAll();
        List<ProductDto> prdto = new ArrayList<>();
        for (Product entity : products) {
            ProductDto productDto = new ProductDto(entity);
            if (productDto.getMerchent() != null)
                for (MerchantDto dto : merchants) {
                    if (dto.getId().equals(entity.getMerchent()))
                        productDto.setMerchantName(dto.getName());
                }
            if (productDto.getManifacture() != null)
                for (ManifactureDto dto : manifacture) {
                    if (dto.getId().equals(entity.getManifacture()))
                        productDto.setManifactureName(dto.getName());
                }
            prdto.add(productDto);
        }
        return prdto;
    }

    public ProductDto getById(String id) {

        Optional<Product> entity = prepo.findById(id);
        if (entity.isPresent()) {
            ProductDto dto = new ProductDto(entity.get());
            if (entity.get().getMerchent() != null && !entity.get().getMerchent().isEmpty())
                dto.setMerchantName(merchantService.getById(entity.get().getMerchent()).getName());
            if (entity.get().getManifacture() != null && !entity.get().getManifacture().isEmpty())
                dto.setManifactureName(manifactureService.getById(entity.get().getManifacture()).getName());
            return dto;
        }
        throw new CustomExceptions("product.not.found", HttpStatus.NOT_FOUND);
    }

    public void deleteByIs(String id) {
        if (!prepo.existsById(id))
            throw new CustomExceptions("product.not.found", HttpStatus.NOT_FOUND);

        prepo.deleteById(id);
    }

    public ProductDto updateById(String id, ProductDto dto) {
        if (dto.getManifacture() != null && !manifactureService.manufaturerExists(dto.getManifacture()))
            throw new CustomExceptions("manufacturer.not.found", HttpStatus.BAD_REQUEST);
        if (dto.getMerchent() != null && !merchantService.merchantExists(dto.getMerchent()))
            throw new CustomExceptions("merchant.not.found", HttpStatus.BAD_REQUEST);

        Product oldproduct = new Product(getById(id));
        oldproduct.setName(dto.getName());
        oldproduct.setManifacture(dto.getMerchent());
        oldproduct.setManifacture(dto.getManifacture());
        oldproduct.setColor(dto.getColor());
        oldproduct.setStock(dto.getStock());
        oldproduct.setIsOffered(dto.getIsOffered());
        oldproduct.setPrice(dto.getPrice());
        oldproduct.setDescription(dto.getDescription());
        return new ProductDto(prepo.save(oldproduct));

    }

}
