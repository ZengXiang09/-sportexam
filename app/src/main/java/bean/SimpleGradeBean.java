package bean;

import java.util.Date;

/**
 * Created by zx on 2016/10/12.
 */

public class SimpleGradeBean {
    private String name;
    private String no;
    private String date;
    private String score;

    public SimpleGradeBean() {
    }


    public SimpleGradeBean(String name, String no, String score, String date) {
        this.name = name;
        this.no = no;
        this.score =score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
