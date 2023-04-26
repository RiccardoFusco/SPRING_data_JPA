package it.aulab.springbootweb;

import java.util.List;
import org.assertj.core.api.Assertions;
// import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.context.SpringBootTest;

import it.aulab.springbootweb.model.Fornitore;
import it.aulab.springbootweb.model.Prodotto;
import it.aulab.springbootweb.model.Variante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringBootExampleApplicationTests {

	@Autowired
	private EntityManager entityManager;


	@Test
	void persistObjectProdotto() {
		TypedQuery<Prodotto> q = entityManager.createQuery("SELECT p FROM Prodotto p", Prodotto.class);

		Prodotto p = new Prodotto();

		p.setNome("Maglia Nike");
		p.setDescrizione("Maglia in cotone");
		p.setPrezzo(30F);

		List<Prodotto> allBeforePersist = q.getResultList();
		

		Assertions.assertThat(allBeforePersist).hasSize(0);

		entityManager.persist(p);

		List<Prodotto> allAfterPersist = q.getResultList();

		Assertions.assertThat(allAfterPersist).hasSize(1);
}


	@Test
	void checkPrezzoNetto(){
		Prodotto p = new Prodotto();

		p.setNome("Maglia Nike");
		p.setDescrizione("Maglia in cotone");
		p.setPrezzo(30F);
		p.setPrezzoNetto(20F);

		entityManager.persist(p);

		TypedQuery<Prodotto> q = entityManager.createQuery("SELECT p FROM Prodotto p WHERE p.id = 1", Prodotto.class);  

		Prodotto queryP = q.getSingleResult();

	Assertions.assertThat(queryP).extracting("prezzoNetto").isEqualTo(20F);

}


	@Test
	void checkManyToOneRelation() {
		TypedQuery<Prodotto> q1 = entityManager.createQuery("SELECT p FROM Prodotto p WHERE p.id = 1", Prodotto.class);
		Prodotto p = q1.getSingleResult();

		TypedQuery<Variante> q2 = entityManager.createQuery("SELECT v FROM Variante v WHERE v.id = 1", Variante.class);  
		Variante v = q2.getSingleResult();

		Assertions.assertThat(v).extracting("prodotto").extracting("id").extracting("id").isEqualTo(1L);
	}


	@Test
	void checkOneToManyRelation(){

		TypedQuery<Prodotto> q = entityManager.createQuery("SELECT p FROM Prodotto p WHERE p.id = 1", Prodotto.class);  

		Prodotto queryP = q.getSingleResult();

		Assertions.assertThat(p).extracting("varianti").asList().hasSize(4);

	
	}

	@Test
	void checkProdottiFromFornitori() {
		TypedQuery<Fornitore> q = entityManager.createQuery("SELEECT FROM Fornitore f", Fornitore.class);
		
		List<Fornitore> all = q.getResultList();

		Assertions.assertThat(all.get(0)).extracting("prodotti").asList().hasSize(1);
		Assertions.assertThat(all.get(1)).extracting("prodotti").asList().hasSize(4);
		Assertions.assertThat(all.get(2)).extracting("prodotti").asList().hasSize(2);
	}

	@Test
	void checkFornitoriFromProdotti() {
		String sql = "SELECT p FROM Prodotto p WHERE id = ";

		TypedQuery<Prodotto> q1 = entityManager.createQuery(sql + "1", Prodotto.class);
		Prodotto p1 = q1.getSingleResult();

		Assertions.assertThat(p1).extracting("fornitori").asList().hasSize(2);

		TypedQuery<Prodotto> q2 = entityManager.createQuery(sql + "3", Prodotto.class);
		Prodotto p3 = q2.getSingleResult();

		Assertions.assertThat(p3).extracting("fornitori").asList().hasSize(1);


	}


	@Test
	void checkRelationInsert() {
		String sql = "SELECT p FROM Prodotto p WHERE id = ";

		TypedQuery<Prodotto> q1 = entityManager.createQuery(sql + "1", Prodotto.class);
		Prodotto p1 = q1.getSingleResult();

		Variante newVariant1 = new Variante();
		newVariant1.setAttributo("taglia");
		newVariant1.setValore("xl");


		entityManager.persist(newVariant1);

		Variante newVariant2 = new Variante();
		newVariant2.setAttributo("taglia");
		newVariant2.setValore("xs");

		entityManager.persist(newVariant2);

		TypedQuery<Prodotto> queryAll = entityManager.createQuery("SELECT p FROM Prodotto p", Prodotto.class);
		List<Prodotto> prodotti = queryAll.getResultList();

		Assertions.assertThat(prodotti.get(0)).extracting("varianti").asList().hasSize(6);

	}


	@Test
	void checkDeleteRelation() {
		String sql = "SELECT p FROM Prodotto p WHERE id = ";

		TypedQuery<Fornitore> q1 = entityManager.createQuery(sql + "1", Fornitore.class);
		Fornitore f1 = q1.getSingleResult();

		Prodotto p = new Prodotto();
		p.setNome("X");
		p.setDescrizione("X");
		p.setPrezzo(1F);
		p.setPrezzoNetto(0.8F);

		entityManager.persist(p);

		f1.getProdotti().add(p);

		entityManager.persist(f1);


		TypedQuery<Fornitore> queryAll = entityManager.createQuery("SELECT f FROM Fornitore f", Fornitore.class);
		List<Fornitore> fornitori = queryAll.getResultList();


		Assertions.assertThat(fornitori.get(0)).extracting("prodotti").asList().hasSize(2);

		// entityManager.remove(p);

		// TypedQuery<Fornitore> queryAll2 = entityManager.createQuery("SELECT f FROM Fornitore f", Fornitore.class);
		// List<Fornitore> fornitori2 = queryAll2.getResultList();

		// Assertions.assertThat(fornitori2.get(0)).extracting("prodotti").asList().hasSize(2);


		entityManager.remove(f1);

		TypedQuery<Prodotto> queryProdotto = entityManager.createQuery("SELECT p FROM Prodotto p WHERE id =  " + p.getId(), Prodotto.class);
		Prodotto prodotto = queryProdotto.getSingleResult();


		Assertions.assertThat(prodotto).extracting("fornitori").asList().hasSize(0);



	}
}