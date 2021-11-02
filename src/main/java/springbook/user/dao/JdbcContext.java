package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


// 3-21
//3-28
public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		// 3-21
		this.dataSource = dataSource;
	}
	
	public void executeSql(String query) throws SQLException {
		// 3-28
		workWithStatementStrategy(
			new StatementStrategy() {
				@Override
				public PreparedStatement makeStatement(Connection c) throws SQLException {
					return c.prepareStatement(query);
				}
			} );
	}
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		// 3-21
		Connection c = null;
		PreparedStatement ps =null;
		try {
			c = dataSource.getConnection();
			
			ps = stmt.makeStatement(c);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if ( ps != null ) {try {ps.close();} catch (SQLException e) {}}
			if ( c != null ) {try {c.close();} catch (SQLException e) {}}
		}

	}

}
