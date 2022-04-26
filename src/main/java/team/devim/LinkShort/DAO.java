package team.devim.LinkShort;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public interface DAO {

    void add(String short_url, String url, Date date);
    ResultSet getResultSet(String short_url) throws SQLException;
    String getUrl(String url) throws SQLException;
    boolean keyActive(String key);
}
