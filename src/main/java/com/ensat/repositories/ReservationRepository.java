package com.ensat.repositories;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ensat.entities.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    @Transactional(readOnly = true)
    @Query("select r from Reservation r where size(r.payement)=0")
    Iterable <Reservation> findReservationsByParam();

    @Transactional(readOnly = true)
    @Query("select COUNT(h) from Reservation h where h.voyage.idvoyage = :idvoyage")
    Integer findNbreReservationByVoyageId(@Param("idvoyage") Integer idvoyage);
}