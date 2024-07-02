package com.example.productservice;

import com.example.productservice.practice.*;
import com.example.productservice.repositories.ProductRepositories;
import com.example.productservice.repositories.projections.ProductWithIdAnsDescription;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductserviceApplicationTests {

	@Autowired
	private ProductRepositories productRepositories;

	@Autowired
	ARepo aRepo;

	@Autowired
	BRepo bRepo;

	@Autowired
	CRepo cRepo;

	@Test
	void contextLoads() {

	}

	@Test
	void testQueries(){
		List<ProductWithIdAnsDescription> products =productRepositories.getJustIdAndDescriptionViaNative(1L, "changa");
		for(ProductWithIdAnsDescription product : products){
			System.out.println(product.getId());
			System.out.println(product.getDescription());
		}
	}

//	@Transactional
	@Test
//	@Rollback(false)
	void testCardinality(){

//		A a = new A();
//		a.setName("A");
//		B b1 = new B();
//		b1.setName("B1");
//		B b2 = new B();
//		b2.setName("B2");
//		b2.setA(a);
//		b1.setA(a);
//		a.setB(List.of(b1,b2));
//		aRepo.save(a);

//		a2.setName("A2");
//
//

//		C c1 = new C();
//		C c2 = new C();
//		c1.setName("C1");
//		c2.setName("C2");
//		A a1 = new A();
//		A a2 = new A();
//		a1.setName("A1");
//		a2.setName("A2");
//
//		a1.setC(List.of(c1,c2));
//		a2.setC(List.of(c1,c2));
//
//
//		aRepo.save(a1);
//		aRepo.save(a2);
//

////		Hibernate.initialize(a1.getC());
//		List<C> c= a1.getC();
//		c.get(0).setName("D1");
//		c.get(1).setName("D2");
//		a1.setName("Aero");
//
//		aRepo.save(a1);
//		aRepo.deleteById(1l);

		Optional<A> a = aRepo.findById(1L);
		A a1 = a.get();
		System.out.println(a1.getC().get(1).getName());



//
//		cRepo.deleteById(1L);
	}

}
