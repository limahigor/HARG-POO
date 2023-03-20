package com.hargclinical.harg;

import com.hargclinical.harg.entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class appTest {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa("Anderson", "70850528488", 22, 'M');
		Pessoa p2 = new Pessoa("Higor", "12548965874", 23, 'M');
		Pessoa p3 = new Pessoa("Rita", "25687569856", 19, 'F');
		Pessoa p4 = new Pessoa("Guilherme", "12569854869", 19, 'F');

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("harg-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.getTransaction().commit();
		System.out.println("Showwwwwwwwwww--------------------");
		
	}
}