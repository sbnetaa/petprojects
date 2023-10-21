package com.terentyev.transportation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terentyev.transportation.entities.Waybill.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
