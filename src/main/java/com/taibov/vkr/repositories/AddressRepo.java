package com.taibov.vkr.repositories;

import com.taibov.vkr.entities.Address;
import com.taibov.vkr.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>
{
    Address findAddressByClient(Client client);
}
