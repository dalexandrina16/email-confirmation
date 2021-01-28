package com.utm.tw.dao;

import com.utm.tw.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findByRandomUrl(String url);

}
