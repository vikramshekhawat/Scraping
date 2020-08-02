package org.example.repo;

import org.example.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository("myDAO")
public interface SchoolRepo extends JpaRepository<School, Integer> {
}
