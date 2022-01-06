package com.solarledger.app.repository;

import com.solarledger.app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    @Query(value = "from Address t where t.postCodes BETWEEN :start AND :end ORDER BY name ASC")
    public List<Address> getAllBetweenPostCodes(@Param("start")Integer start, @Param("end")Integer end);
}
