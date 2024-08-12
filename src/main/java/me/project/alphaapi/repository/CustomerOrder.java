package me.project.alphaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrder extends JpaRepository<CustomerOrder, Long> {
}
