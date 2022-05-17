package team.devim.LinkShort;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "shorturl")
public class IncomingURL {
    @Id
    @Column
    String url;
    @Column
    String short_url;
    @Column
    Timestamp time;

    public IncomingURL() {
    }

    public IncomingURL(String url, String short_url, Timestamp time) {
        this.url = url;
        this.short_url = short_url;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
