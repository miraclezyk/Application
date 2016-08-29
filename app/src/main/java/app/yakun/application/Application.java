package app.yakun.application;

import java.util.Date;

/**
 * Created by Fire on 2016/7/11.
 */
public class Application {
    private int id;
    private String company;
    private String position;
    private Boolean ifInterviewed;
    private Date date;
    private String note;

    public Application(){
        this.company = "";
        this.position = "";
        this.date = new Date();
        this.ifInterviewed = false;
        this.note = "";
    }

    public Application(String company, String position) {
        this.company = company;
        this.position = position;
        this.date = new Date();
        this.ifInterviewed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setIfInterviewed(Boolean ifInterviewed) {
        this.ifInterviewed = ifInterviewed;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getIfInterviewed() {
        return ifInterviewed;
    }

    public String getPosition() {
        return position;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String comments) {
        this.note = comments;
    }
}
