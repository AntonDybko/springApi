package com.ug.edu.pl.ap.lab9.repository;

import com.ug.edu.pl.ap.lab9.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Query("SELECT s FROM Shop s WHERE s.address = ?1 AND s.openingTime >= ?2 ORDER BY s.openingTime")
    List<Shop> findByAddressAndOpeningTime(String address, LocalTime openingTime);

    @Query("SELECT s FROM Shop s WHERE s.address = ?1 AND s.revenue >= ?2 ORDER BY s.revenue DESC")
    List<Shop> findByAddressAndRevenue(String address, BigDecimal revenue);

    @Query("SELECT s FROM Shop s WHERE s.revenue BETWEEN ?1 AND ?2 ORDER BY s.revenue DESC")
    List<Shop> findByRevenue(BigDecimal min, BigDecimal max);

    @Query("SELECT s FROM Shop s WHERE s.address = ?1 OR s.address = ?2 ORDER BY s.revenue DESC")
    List<Shop> findByTwoAddresses(String address1, String address2);

}
