package com.singhambar.app;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.singhambar.beans.User;
import com.singhambar.services.UserService;

public class main {

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		User user = new User();

		user.setName("Ambar Singh");
		user.setEmailId("ambar.kumar@adeptia.com");
		user.setPassword("P@ssw0rd");

		try {
			UserService userService = (UserService) appContext.getBean("userService");
			userService.createUser(user);

			SessionFactory sessionFactory = (SessionFactory) appContext.getBean("sessionFactory");
			Session session = sessionFactory.openSession();
			
			luceneSearch(session);
			luceneSearch(session);
			luceneSearch(session);
			luceneSearch(session);
			normalSearch(session);
			normalSearch(session);
			normalSearch(session);
			normalSearch(session);
			
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void luceneSearch(Session session) {
		FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
//		fullTextSession.createIndexer().startAndWait();
		Instant start = Instant.now();
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
		org.apache.lucene.search.Query query = qb.keyword().onFields("name").matching("result").createQuery();

		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, User.class);

		List result = hibQuery.list();

		Instant finish = Instant.now();

		long timeElapsed = Duration.between(start, finish).toMillis();

		System.out.println("Lucene:: -> "+timeElapsed + " ms");
	}

	
	public static void normalSearch(Session session) {
		Instant start = Instant.now();
		Query query1 = session.createQuery("from User where name=:name");
		query1.setParameter("name", "result");

		query1.getResultList();
		Instant finish = Instant.now();

		long timeElapsed = Duration.between(start, finish).toMillis();

		System.out.println("Normal::-> "+timeElapsed + " ms");
	}
	
}
