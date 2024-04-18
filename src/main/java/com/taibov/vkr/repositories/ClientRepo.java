package com.taibov.vkr.repositories;

import com.taibov.vkr.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> //, ClientCustomRepo
{
    List<Client> findClientsByEmail(String email);
    Client findClientById(int id);

    Client findClientByEmailAndAndPsswrd(String email, String psswrd);

    List<Client> findAll();

    int deleteClientById(int id);
}
