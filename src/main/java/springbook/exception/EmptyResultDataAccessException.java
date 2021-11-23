package springbook.exception;

import java.sql.SQLException;


public class EmptyResultDataAccessException extends org.springframework.dao.EmptyResultDataAccessException{

	public EmptyResultDataAccessException(int expectedSize) {
		super(expectedSize);
		// TODO Auto-generated constructor stub
	}


}
