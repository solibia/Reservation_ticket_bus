package com.ensat.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ensat.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Transactional(readOnly = true)
    @Query("select u from User u where u.ca = :ca AND u.passw = :passw")
    User findUserByInfo(@Param("ca") String ca, @Param("passw") String passw);
}
