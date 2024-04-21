package softuni.exam.database;
//TestDbColumnNames

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class

TestDbColumnNames {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Test
    void columnsNames() throws SQLException {
        DatabaseMetaData metaData = getDatabaseMetaData();

        List<String> expectedResult = new ArrayList<>();


        expectedResult.add("ID");
        expectedResult.add("FIRST_NAME");
        expectedResult.add("LAST_NAME");
        expectedResult.add("PERSONAL_NUMBER");
        expectedResult.add("ID");
        expectedResult.add("BRAND");
        expectedResult.add("DEVICE_TYPE");
        expectedResult.add("MODEL");
        expectedResult.add("PRICE");
        expectedResult.add("STORAGE");
        expectedResult.add("SALE_ID");
        expectedResult.add("ID");
        expectedResult.add("DISCOUNTED");
        expectedResult.add("NUMBER");
        expectedResult.add("SALE_DATE");
        expectedResult.add("SELLER_ID");



        ResultSet columns1 = metaData.getColumns(null, "PUBLIC", null, null);

        final List<String> actualResult = new ArrayList<>();

        while (columns1.next()) {
            actualResult.add(String.format("%s", columns1.getString("COLUMN_NAME")));
        }

        Assertions.assertArrayEquals(expectedResult.stream().sorted().toArray(), actualResult.stream().sorted().toArray());
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        DataSource dataSource = getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection.getMetaData();
    }
}