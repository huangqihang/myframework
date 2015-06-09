package app.demo.jpa;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.demo.jpa.entity.Address;
import app.demo.jpa.persistenceutil.PersistenceManager;

public class Main {
	
	EntityManager em = null;
	
	@Before
	public void init() {
		em = PersistenceManager.INSTANCE.getEntityManager();
	}
	
	@After
	public void destory() {
		PersistenceManager.INSTANCE.close();
		if(em.isOpen())
			em.close();
	}
	
	@Test
	public void testSave() {

		Address address = new Address().setCity("Dhaka")
				.setCountry("Bangladesh").setPostcode("1000")
				.setStreet("Poribagh").setProvince("北京");
		em.getTransaction().begin();
		em.persist(address);
		em.getTransaction().commit();
		System.out.println("addess is saved! It has id: " + address.getId());

		Address anotherAddress = new Address()
				.setCity("Shinagawa-ku, Tokyo").setCountry("china")
				.setPostcode("140-0002").setStreet("Shinagawa Seaside Area").setProvince("上海");
		em.getTransaction().begin();
		em.persist(anotherAddress);
		em.getTransaction().commit();
		
		System.out.println("anotherAddress is saved! It has id: "
				+ anotherAddress.getId());

		
	}
}