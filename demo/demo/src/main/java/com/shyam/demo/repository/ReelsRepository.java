package com.shyam.demo.repository;

import com.shyam.demo.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {
   public  List<Reels> findByUserId(Integer userId);
}
