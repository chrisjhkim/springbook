package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/springbook");
		dataSource.setUsername("user01");
		dataSource.setPassword("user01");
		return dataSource;
	}
	
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}

//	public AccountDao accountDao() {
//		return new AccountDao(connectionMaker());
//	}

//	@Bean
//	public ConnectionMaker connectionMaker() {
//		System.out.println("ConnectionMaker Bean create");
//		return new LocalDBConnectionMaker();
//	}
	

	
	
}
