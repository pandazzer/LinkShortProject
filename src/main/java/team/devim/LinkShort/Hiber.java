package team.devim.LinkShort;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "shorturl")
public class Hiber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    String url;
    String short_url;
    Date time;

    public Hiber() {}

    public Hiber(String url, String short_url, Date time) {
        this.url = url;
        this.short_url = short_url;
        this.time = time;
    }

    public int getId() {
        return id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
