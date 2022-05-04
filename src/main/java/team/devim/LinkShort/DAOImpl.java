package team.devim.LinkShort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DAOImpl implements DAO, Constants{

    Connection connection;
    private static final Logger log =  LogManager.getLogger(Main.class);

    public DAOImpl() {
        this.connection = ConnectToBase.getConnection();
    }

    @Override
    public void add(String short_url, String url, java.util.Date date) {
        try {
            PreparedStatement prstm = connection.prepareStatement("insert into shorturl (short_url, time, url)\n" +
                    "values ( ? , ? , ? )");
            Timestamp timestamp = new Timestamp(date.getTime());
            prstm.setString(1, short_url);
            prstm.setTimestamp(2, timestamp);
            System.out.println(timestamp);
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
    public boolean keyActive(String key){
        ResultSet result;
        Date date;
        try {
            result = getResultSet(key);
            result.next();
            date = result.getTimestamp(3);
            System.out.println(date);
        } catch (SQLException e) {
            log.error("Ошибка проверки активности ссылки", e);
            return false;
        }
        Date date1 = new Date();
        int secondLive = SECOND_LIVE * 1000;
        if ((date1.getTime() - date.getTime()) > secondLive){
            return false;
        }else return true;
    }

    @Override
    public String getUrl(String url) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select *\n" +
                "from shorturl s \n" +
                "where s.url = ?");
        statement.setString(1, url);
        ResultSet result = statement.executeQuery();
        result.next();
        String res = String.format("http://localhost:4567/%s", result.getString(2));

        Timestamp timestamp = new Timestamp(new Date().getTime());
        PreparedStatement statement1 = connection.prepareStatement("update shorturl set \"time\"=? where url = ?");
        statement1.setTimestamp(1,timestamp);
        statement1.setString(2, url);
        statement1.executeUpdate();
        return res;
    }
}
