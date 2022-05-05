package team.devim.LinkShort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class URLService {

    private  URLDAO urldao;

    private static final Logger log =  LogManager.getLogger(Main.class);

    public URLService() {
        this.urldao = new URLDAO();
    }
    DAO dao;


    String getCutUrl(String url){
        try {
            String str = urldao.findByURL(url);
            log.info("Ссылка уже записана");
            return str;
        } catch (Exception e) {
            log.info("Ссылка не записана");
            String shortURL = getShortUrl();
            Timestamp date = new Timestamp(new Date().getTime());
            urldao.add(new IncomingURL(shortURL, url, date));
            String res = String.format("http://localhost:4567/%s", shortURL);
            return res;
        }
    }

    String getShortUrl(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    String getForwarding(String key){
        if (!dao.keyActive(key)){
            return "Ссылка больше не активна или не записана";
        }
        ResultSet result;
        String url;
        try {
            result = dao.getResultSet(key);
            result.next();
            url = result.getString(1);
            log.info("Выдана ссылка");
        } catch (SQLException e) {
            log.error("Ошибка возвращения ссылки");
            throw new RuntimeException(e);
        }
        return url;
    }
}
