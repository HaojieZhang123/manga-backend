package org.project.java.manga_backend.repository;

import java.util.Optional;

import org.project.java.manga_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
