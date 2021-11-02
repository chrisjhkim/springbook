package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 3-8
public interface StatementStrategy {
	PreparedStatement makeStatement(Connection c) throws SQLException;

}
