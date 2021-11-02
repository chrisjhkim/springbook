package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

import springbook.exception.EmptyResultDataAccessException;
import springbook.user.domain.User;

// 3-10
// 3-11
// 3-12
// 3-15
// 3-16
// 3-17
// 3-18
// 3-19
// 3-20
// 3-22
// 3-25
// 3-27
//3-28
public class UserDao {
//	private Connection c;
	private JdbcContext jdbcContext;
	
//	public UserDao(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
//	public UserDao() {
//		DaoFactory daoFactory = new DaoFactory();
//		this.connectionMaker = daoFactory.connectionMaker();
//	}

	public void setJdbcContext(JdbcContext jdbcContext) {
	}
	
//	public void setConnectionMaker(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
	}
	
	public void add(final User user) throws SQLException {
		// 3-15
		// 3-16
		// 3-17
		// 3-18
		// 3-19
		// 3-22
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				@Override
				public PreparedStatement makeStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
					
					ps.setString(1, user.getId());
					ps.setString(2, user.getName());
					ps.setString(3, user.getPassword());
					return ps;
				}
			}
		);
	}
	
	public User get(String id) throws SQLException, EmptyResultDataAccessException {
		// 2-14
//		this.c = connectionMaker.makeConnection();
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ? ");
		ps.setString(1, id);
		
		User user = null;
		
		ResultSet rs = ps.executeQuery();
		if ( rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if ( user == null ) throw new EmptyResultDataAccessException(1);
		return user;
	}

	public void deleteAll() throws SQLException {
		// 2-7
		// 3-2
		// 3-12
		// 3-20
		// 3-27
		// 3-28
		// 3-29
		this.jdbcContext.executeSql("delete from users");
	}

	
	// 3-7
//	abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
////	private PreparedStatement makeStatement(Connection c) throws SQLException {
////		// 3-6
////		PreparedStatement ps;
////		ps = c.prepareStatement("delete from users");
////		return ps;
////	}
//	
	
	public int getCount() throws SQLException {
		// 2-8
		// 3-3
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
			
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if ( ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if ( c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		// 3-11
		Connection c = null;
		PreparedStatement ps =null;
		try {
			c = dataSource.getConnection();
			
			ps = stmt.makeStatement(c);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if ( ps != null ) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if ( c != null ) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
			
		}
		
	}
}
