/**
 * 
 */
package la.ipo.repository.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;

/**
 * 2014年2月12日 上午10:36:52
 * 
 * @author WANGJUN
 * 
 */
@MappedTypes(value = DateTime.class)
public class DatetimeHandler extends BaseTypeHandler<DateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null) {
			ps.setTimestamp(i, new Timestamp((parameter).getMillis()));
		} else {
			ps.setTimestamp(i, new Timestamp(new DateTime().getMillis()));
		}
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp timastamp = rs.getTimestamp(columnName);
		if (timastamp != null) {
			return new DateTime(timastamp.getTime());
		} else {
			return null;
		}
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp timastamp = rs.getTimestamp(columnIndex);
		if (timastamp != null) {
			return new DateTime(timastamp.getTime());
		} else {
			return null;
		}
	}

	@Override
	public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp timastamp = cs.getTimestamp(columnIndex);
		if (timastamp != null) {
			return new DateTime(timastamp.getTime());
		} else {
			return null;
		}
	}

}
