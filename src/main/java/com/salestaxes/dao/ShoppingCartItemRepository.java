package com.salestaxes.dao;

import com.salestaxes.entity.ShoppingCartItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ShoppingCartItemRepository extends PagingAndSortingRepository<ShoppingCartItem, Long> {

}
