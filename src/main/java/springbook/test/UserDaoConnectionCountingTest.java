package springbook.test;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.dao.CountingConnectionMaker;
import springbook.user.dao.CountingDaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoConnectionCountingTest {

	public static void main(String[] args ) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
//		System.out.println("Connection counter : " + ccm.getCounter());
		
		User user = new User();
		user.setId("lynn@15");
		user.setName("이인혜");
		user.setPassword("준현조아");
		
		dao.add(user);
		
		System.out.println(user.getId() + " 등록 성공");
		
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " 조회성공");
	}
}
