package com.ensat.repositories;

import com.ensat.entities.Voyage;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VoyageRepository extends CrudRepository<Voyage, Integer> {

    @Transactional(readOnly = true)
    @Query("select v from Voyage v where v.vDep.id = :idvdep AND v.vAriv.id = :idvariv AND v.datDep = :dat_dep")
    Iterable <Voyage>  findVoyageByParam(@Param("idvdep") Integer idvdep, @Param("idvariv") Integer idvariv, @Param("dat_dep") Date dat_dep);
}
