package springbook.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.Spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.dao.UserDaoJdbc;
import springbook.user.domain.Level;
import springbook.user.domain.User;


// 2-17
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
// 2-20
@DirtiesContext
public class UserDaoTest {
	// 2-15
	@Autowired
	UserDao dao;
	@Autowired
	DataSource dataSource;
//	@Autowired
//	private ApplicationContext context;
	User user1;
	User user2;
	User user3;
	
	@Before
	public void setUp() {
		System.out.println("@Before - setting user1");
		this.user1 = new User("chris1108", "김준현", "pass321", Level.BASIC, 1, 0);
		this.user2 = new User("inhye0522", "이인혜", "pass123", Level.SILVER, 55, 10);
		this.user3 = new User("chrisjhkim", "홍길동", "asdf1234", Level.GOLD, 100, 40);
		
		// 2-15
		// 2-17
		// 2-20
//		this.dao = context.getBean("userDao", UserDao.class);
		DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/springbook?serverTimezone=UTC", "user01", "user01", true);
//		dao.setDataSource(dataSource);																serverTimezone=Asia/Seoul
	}
	@Test
	public void addAndGet() throws SQLException {
		// 2-5 
		// 2-9
		
		
		dao.deleteAll();
		assertThat(dao.getCount() ,is(0));
		
		dao.add(user1);
		assertThat(dao.getCount() ,is(1));
		
		System.out.println(user1.getId() + " 등록 성공");
		
		
		User userGet1 = dao.get(user1.getId());
		System.out.println(userGet1.getName());
		System.out.println(userGet1.getPassword());
		
		System.out.println(userGet1.getId() + " 조회성공");
		checkSameUser(user1, userGet1);
		
		dao.add(user2);
		User userGet2 = dao.get(user2.getId());
		checkSameUser(user2, userGet2);
//		checkSameUser(user, userget1);
	}

	
	
	@Test
	public void count() throws SQLException {
//		2-11
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		//2-14
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unkown_ID");
	}
	
	@Test
	public void getAll() {
		// 3-52 skip
		
	}
	
	public static void main(String[] args ) throws ClassNotFoundException, SQLException {
		// 2-6
		JUnitCore.main("springbook.test.UserDaoTest");
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void duplicateKey() {
		dao.deleteAll();
		
		try {
			dao.add(user1);
			dao.add(user1);
		} catch (DuplicateKeyException ex) {
			SQLException sqlEx = (SQLException)ex.getRootCause();
			SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
			
			assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
		}
	}
	
	private void checkSameUser(User user1, User user2) {
		assertEquals(user1.getId(), user2.getId());
		assertEquals(user1.getName(), user2.getName());
		assertEquals(user1.getPassword(), user2.getPassword());
		assertEquals(user1.getLevel(), user2.getLevel());
		assertEquals(user1.getLogin(), user2.getLogin());
		assertEquals(user1.getRecommend(), user2.getRecommend());
	}
}

