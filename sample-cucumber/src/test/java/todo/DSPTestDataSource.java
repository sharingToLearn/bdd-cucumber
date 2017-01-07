package todo;

import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DSPTestDataSource {

	public static DataSource getOracleDataSource() {
		OracleDataSource oracleDS = null;
		try {
			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@localhost:7777:CLAROCHILE");
			oracleDS.setUser("DSP_MAIN");
			oracleDS.setPassword("DSP_MAIN");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oracleDS;
	}
}
