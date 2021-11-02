package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
//	@Bean
//	public UserDao userDao() {
//		System.out.println("Bean create - UserDao from CountingDaoFactory");
//		return new UserDao(connectionMaker());
//	}
//	
//	
//	@Bean
//	public ConnectionMaker connectionMaker() {
//		System.out.println("Bean create - ConnectionMaker from CountingDaoFactory");
//		return new CountingConnectionMaker(realConnectionMaker());
//	}
//	
//	@Bean
//	public ConnectionMaker realConnectionMaker() {
//		System.out.println("Bean create - realConnectionMaker from CountingDaoFactory");
//		return new DConnectionMaker();
//	}

}
