package com.taibov.vkr;

import com.taibov.vkr.entities.Address;
import com.taibov.vkr.entities.Client;
import com.taibov.vkr.entities.Order;
import com.taibov.vkr.repositories.AddressRepo;
import com.taibov.vkr.repositories.ClientRepo;
import com.taibov.vkr.repositories.CarRepo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@Aspect
class VkrApplicationTests {

	@Autowired
	private ClientRepo clientRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private CarRepo carRepo;

	@Test
	void contextLoads() {
		System.out.println(clientRepo.findAll());
	}

	@Test
	void CreateClient(){
		Client client = new Client("abc@mail.ru", "123");
		clientRepo.saveAndFlush(client);
	}

	@Test
	void GetClient(){
		Client client = clientRepo.findClientById(54);
		System.out.println(client);
	}

	@Test
	void SetAddress(){
		Client client = clientRepo.findClientById(54);
		Address address = new Address("Moscow", "Prospect Vernadskogo", "78", "12");
		client.setFk_address(address);
		clientRepo.saveAndFlush(client);
	}

	@Test
	void GetAddressByClient(){
		Client client = clientRepo.findClientById(54);
		Address address = addressRepo.findAddressByClient(client);
		System.out.println(address);
	}

	@Test
	void SetAddressBySession(){
		Client client = clientRepo.findClientById(54);
		System.out.println(client);
		Address address = new Address("Moscow", "Prospect Vernadskogo", "78", "12");
		client.setFk_address(address);
		Main.DoInSession(session -> {
			session.update(client);
		});
	}

	@Test
	void CreateNewOrder(){
		Client client = clientRepo.findClientById(54);
		Order order = new Order(new int[]{1, 1, 1, 1, 2, 2}, 1450, client);
		client.AddOrder(order);
		clientRepo.saveAndFlush(client);
	}

	@Test
	void testNameThyme(){
		String name = "Toyota Camry";
		System.out.println("'" + name + "'");
		System.out.println( name );
	}
}
