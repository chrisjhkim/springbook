package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

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
// 3-28
// 3-45
public class UserDao {
//	private Connection c;
//	private JdbcContext jdbcContext;
	private JdbcTemplate jdbcTemplate; // 3-45
//	private DataSource dataSource;
//	public UserDao(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
//	public UserDao() {
//		DaoFactory daoFactory = new DaoFactory();
//		this.connectionMaker = daoFactory.connectionMaker();
//	}

//	public void setJdbcContext(JdbcContext jdbcContext) {
//	}
	// 3-56
	private RowMapper<User> userMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};

	
//	public void setConnectionMaker(ConnectionMaker connectionMaker) {
//		this.connectionMaker = connectionMaker;
//	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		this.dataSource = dataSource;
	}
	
	public void add(final User user) throws SQLException {
		// 3-15
		// 3-16
		// 3-17
		// 3-18
		// 3-19
		// 3-22
//		this.jdbcContext.workWithStatementStrategy(
//			new StatementStrategy() {
//				@Override
//				public PreparedStatement makeStatement(Connection c) throws SQLException {
//					PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//					
//					ps.setString(1, user.getId());
//					ps.setString(2, user.getName());
//					ps.setString(3, user.getPassword());
//					return ps;
//				}
//			}
//		);
		// 3-48(text)
		this.jdbcTemplate.update("insert into users(id, name, password) values (?,?,?)", user.getId(), user.getName(), user.getPassword());
		
	}
	
	public User get(String id) throws SQLException, EmptyResultDataAccessException {
		// 2-14
//		this.c = connectionMaker.makeConnection();
//		Connection c = dataSource.getConnection();
//		
//		PreparedStatement ps = c.prepareStatement("select * from users where id = ? ");
//		ps.setString(1, id);
//		
//		User user = null;
//		
//		ResultSet rs = ps.executeQuery();
//		if ( rs.next()) {
//			user = new User();
//			user.setId(rs.getString("id"));
//			user.setName(rs.getString("name"));
//			user.setPassword(rs.getString("password"));
//		}
//		
//		rs.close();
//		ps.close();
//		c.close();
//		
//		if ( user == null ) throw new EmptyResultDataAccessException(1);
//		return user;
		// 3-51
		return this.jdbcTemplate.queryForObject(
				"select * from users where id = ?"
				, new Object[] {id}
				, this.userMapper);
	}

	public void deleteAll() {
		// 2-7
		// 3-2
		// 3-12
		// 3-20
		// 3-27
		// 3-28
		// 3-29
		// 3-46
//		this.jdbcTemplate.update(
//				new PreparedStatementCreator() {
//					@Override
//					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//						return con.prepareStatement("delete from users");
//					}});
		// 3-47
		this.jdbcTemplate.update("delete from users");
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
	
	public int getCount() {
		// 2-8
		// 3-3
		// 3-49
		// 3-50
		return this.jdbcTemplate.queryForInt("select count(*) from users");
	}
	
	
//	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
//		// 3-11
//		Connection c = null;
//		PreparedStatement ps =null;
//		try {
//			c = dataSource.getConnection();
//			
//			ps = stmt.makeStatement(c);
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			throw e;
//		} finally {
//			if ( ps != null ) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//				}
//			}
//			if ( c != null ) {
//				try {
//					c.close();
//				} catch (SQLException e) {
//				}
//			}
//			
//		}
//		
//	}
	
	public List<User> getAll(){
		return this.jdbcTemplate.query("select * from users order by id"
				, this.userMapper);
	}

}
