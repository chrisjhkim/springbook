package springbook.test;

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.dao.UserDaoJdbc;
import springbook.user.domain.User;


// 2-17
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
// 2-20
@DirtiesContext
public class UserDaoTest {
	// 2-15
	@Autowired
	private UserDao dao;
//	@Autowired
//	private ApplicationContext context;
	User user1;
	
	@Before
	public void setUp() {
		System.out.println("@Before - setting user1");
		user1 = new User("id", "name", "pw");
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
		
		User user = new User();
		user.setId("lynn@22");
		user.setName("������");
		user.setPassword("��������");
		
		dao.add(user);
		assertThat(dao.getCount() ,is(1));
		
		System.out.println(user.getId() + " ��� ����");
		
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " ��ȸ����");
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}

	
	
	@Test
	public void count() throws SQLException {
//		2-11
		User user1 = new User("chris1108", "������", "1q2w3e4r");
		User user2 = new User("lyn0522", "������", "1q2w3e4r");
		User user3 = new User("chrisjhkim", "��ũ����", "1q2w3e4r");
		
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
		
		dao.add(user1);
		dao.add(user1);
	}
}

