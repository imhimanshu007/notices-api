package com.relaxcoder.noticesapi.repository;

import com.relaxcoder.noticesapi.entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticesRepository extends JpaRepository<Notices, Long> {
}
