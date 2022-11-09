package com.proshore.vpps.repository;

import com.proshore.vpps.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BatteryRepository extends JpaRepository<Battery, Long> {

    @Query(Queries.BATTERIES_LIST)
    List<Battery> findByPostCodeBetween(Integer from, Integer to);

    final class Queries {

        public static final String BATTERIES_LIST = "SELECT b FROM Battery b WHERE b.postCode BETWEEN :from AND :to";

        private Queries() {
        }
    }

}
