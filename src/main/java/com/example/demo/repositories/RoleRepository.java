package com.example.demo.repositories;


import com.example.demo.entities.ERole;
import com.example.demo.entities.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
	 Optional<Role> findByName(ERole name);
}
