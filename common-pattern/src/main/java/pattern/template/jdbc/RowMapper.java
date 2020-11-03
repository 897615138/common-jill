package pattern.template.jdbc;

import java.sql.ResultSet;

/**
 * ORM映射定制化的接口
 *
 * @author jill
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws Exception;
}
