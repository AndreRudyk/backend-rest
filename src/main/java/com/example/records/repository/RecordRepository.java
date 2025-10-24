package com.example.records.repository;

import com.example.records.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    
    /**
     * Finds all records sorted by creation date in descending order (newest first)
     */
    @Query("SELECT r FROM Record r ORDER BY r.createdAt DESC")
    List<Record> findAllOrderByCreatedAtDesc();
}