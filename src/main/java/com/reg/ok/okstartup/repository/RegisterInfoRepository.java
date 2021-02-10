package com.reg.ok.okstartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reg.ok.okstartup.model.RegisterInfo;

public interface RegisterInfoRepository extends JpaRepository<RegisterInfo, Long> {

}
