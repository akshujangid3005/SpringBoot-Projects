package com.thinkconstructive.dao;

import com.thinkconstructive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User , Integer>{



}
