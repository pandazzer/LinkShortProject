package team.devim.LinkShort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DAOImpl implements DAO{

    Connection connection;
    private static final Logger log =  LogManager.getLogger(Main.class);

    public DAOImpl() {
        this.connection = ConnectToBase.getConnection();
    }

    @Override
    public void add(String short_url, String url, java.util.Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            PreparedStatement prstm = connection.prepareStatement("insert into shorturl (short_url, time, url)\n" +
                    "values ( ? , ? , ? )");
            prstm.setString(1, short_url);
            prstm.setString(2, dateFormat.format(date));
            prstm.setString(3, url);
            prstm.executeUpdate();
        } catch (SQLException e) {
            log.error("Ошибка добавления данных в базу", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getResultSet(String short_url) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select *\n" +
                "from shorturl s \n" +
                "where s.short_url = ?");
        statement.setString(1, short_url);
        ResultSet result = statement.executeQuery();
        return result;
    }

    @Override
    public String getUrl(String url) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select *\n" +
                "from shorturl s \n" +
                "where s.url = ?");
        statement.setString(1, url);
        ResultSet result = statement.executeQuery();
        result.next();
        String res = String.format("http://localhost:4567/%s", result.getString(1));
        return res;
    }
}
