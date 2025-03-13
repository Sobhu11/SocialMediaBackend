package com.saurav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurav.models.Story;

public interface StryRepository extends JpaRepository<Story,Integer> {
public List<Story>findByUserId(Integer userId);
}
