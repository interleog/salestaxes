package com.salestaxes.dao;

import com.salestaxes.entity.ShoppingCart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {

    ShoppingCart findById(Long id);
}
