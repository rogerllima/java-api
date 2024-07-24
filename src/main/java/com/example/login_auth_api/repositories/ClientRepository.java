package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, String> {
    List<Client> findByNameIgnoreCaseContaining(String name);

}
