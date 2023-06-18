package com.exampleM.Minh.services;


import com.exampleM.Minh.entity.Product;
import com.exampleM.Minh.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

        
        public void addProduct(Product product){
            productRepository.save(product);
        }
        public void updateProduct(Product product){
            productRepository.save(product);
        }
        public void deleteProduct(Long id){
            productRepository.deleteById(id);
        }
	public List<Product> getAllProductByCategoryId(long id) {
        return productRepository.findAllByCategory_Id(id);
    }
}

