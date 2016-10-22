package bean;

import java.io.Serializable;

/**
 * Created by zx on 2016/10/16.
 */

public class DetailGradeBean implements Serializable {
    private String no;
    private String num;
    private String date;
    private String score;
    private String foul;

    public DetailGradeBean() {
    }

    public DetailGradeBean(String no, String num, String date, String score, String foul) {
        this.no = no;
        this.num = num;
        this.date = date;
        this.score = score;
        this.foul = foul;
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

    public String getFoul() {
        return foul;
    }

    public void setFoul(String foul) {
        this.foul = foul;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
