package pattern.template.jdbc;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jill
 */
public abstract class AbstractJdbcTemplate {
    private final DataSource dataSource;

    public AbstractJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static void closeConnection(Connection conn) throws Exception {
        //数据库连接池，我们不是关闭
        conn.close();
    }

    private static void closeStatement(PreparedStatement pstm) throws Exception {
        pstm.close();
    }

    private static void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

    private static List<?> paresResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) result.add(rowMapper.mapRow(rs, rowNum++));
        return result;
    }

    private static ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws Exception {
        for (int i = 0; i < values.length; i++) pstm.setObject(i, values[i]);
        return pstm.executeQuery();
    }

    private static PreparedStatement createPrepareStatement(Connection conn, String sql) throws Exception {
        return conn.prepareStatement(sql);
    }

    protected List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        try {
            //1、获取连接
            Connection conn = getConnection();
            //2、创建语句集
            PreparedStatement pstm = createPrepareStatement(conn, sql);
            //3、执行语句集
            ResultSet rs = executeQuery(pstm, values);
            //4、处理结果集
            List<?> result = paresResultSet(rs, rowMapper);
            //5、关闭结果集
            closeResultSet(rs);
            //6、关闭语句集
            closeStatement(pstm);
            //7、关闭连接
            closeConnection(conn);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}
