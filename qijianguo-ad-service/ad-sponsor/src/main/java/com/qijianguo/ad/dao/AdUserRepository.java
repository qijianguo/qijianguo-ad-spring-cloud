package com.qijianguo.ad.dao;

import com.qijianguo.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    AdUser findByUsername(String username);


}
