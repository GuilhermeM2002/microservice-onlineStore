package br.com.onlineStore.catalogms.infra;

import br.com.onlineStore.catalogms.application.dto.ProductDto;
import br.com.onlineStore.catalogms.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductRepositoryService{
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper mapper;

    public void deleteProduct(Long code){
        repository.deleteById(code);
    }

    public Page<ProductDto> findAllProduct(Pageable pageable){
        return repository.findAll(pageable)
                .map(product -> mapper.map(product, ProductDto.class));
    }

    public ProductDto findByCodeProduct(Long code){
        var product = repository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException());
        return mapper.map(product, ProductDto.class);
    }
}
