package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.entity.OnlineBankingEntity;

public interface OnlineBankingRepository extends JpaRepository<OnlineBankingEntity, String> {

}
