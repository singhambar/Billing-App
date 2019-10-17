package com.singhambar.repositories;

import org.springframework.stereotype.Repository;

import com.singhambar.beans.Bill;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */

@Repository
public interface BillRepository extends BaseRepository<Bill, Long> {

}
