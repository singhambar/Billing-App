package com.singhambar.repositories;

import org.springframework.stereotype.Repository;

import com.singhambar.beans.Product;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

}
