package team.devim.LinkShort;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectToBase implements Constants{

    private static final Logger log =  LogManager.getLogger(Main.class);
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER_NAME);
        config.setPassword(STRING_PASS);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(5);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            log.info("Успешное подключение к базе");
            return connection;
        }catch (SQLException e) {
            log.error("Ошибка подключения");
            e.printStackTrace();
            return null;
        }
    }
}
