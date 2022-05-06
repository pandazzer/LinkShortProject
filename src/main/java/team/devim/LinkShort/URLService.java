package team.devim.LinkShort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class URLService implements Constants {

    private static final Logger log = LogManager.getLogger(Main.class);
    private final URLDAO urldao;

    public URLService() {
        this.urldao = new URLDAO();
    }

    String getCutUrl(String url) {
        try {
            IncomingURL inUrl = urldao.findByURL(url);
            String short_url = inUrl.getShort_url();
            Timestamp timestamp = new Timestamp(new Date().getTime());
            urldao.update(new IncomingURL(url, short_url, timestamp));
            log.info("Ссылка уже записана");
            String res = String.format("http://localhost:4567/%s", short_url);
            return res;
        } catch (Exception e) {
            log.info("Ссылка не записана");
            String shortURL = getShortUrl();
            Timestamp date = new Timestamp(new Date().getTime());
            urldao.add(new IncomingURL(url, shortURL, date));
            String res = String.format("http://localhost:4567/%s", shortURL);
            return res;
        }
    }

    String getShortUrl() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    String getForwarding(String key) {
        if (!keyActive(key)) {
            return "Ссылка больше не активна или не записана";
        }
        IncomingURL result;
        String url;
        try {
            result = urldao.findByKey(key);
            url = result.getUrl();
            log.info("Выдана ссылка");
        } catch (Exception e) {
            log.error("Ошибка возвращения ссылки");
            throw new RuntimeException(e);
        }
        return url;
    }

    public boolean keyActive(String key) {
        IncomingURL result;
        Date date;
        try {
            result = urldao.findByKey(key);
            date = result.getTime();
            System.out.println(date);
        } catch (Exception e) {
            log.error("Ошибка проверки активности ссылки", e);
            return false;
        }
        Date date1 = new Date();
        int secondLive = SECOND_LIVE * 1000;
        if ((date1.getTime() - date.getTime()) > secondLive) {
            log.info("Ссылка НЕ активна");
            return false;
        } else {
            log.info("Ссылка активна");
            return true;
        }
    }
}
