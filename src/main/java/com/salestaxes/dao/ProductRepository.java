package com.salestaxes.dao;

import com.salestaxes.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
