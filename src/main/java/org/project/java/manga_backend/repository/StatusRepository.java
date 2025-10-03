package org.project.java.manga_backend.repository;

import java.io.ObjectInputFilter.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
