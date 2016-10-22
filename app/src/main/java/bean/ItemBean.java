package bean;

import java.io.Serializable;

/**
 * Created by zx on 2016/10/17.
 */

public class ItemBean implements Serializable {
    private String item;
    private String score;

    public ItemBean() {
    }

    public ItemBean(String item, String score) {
        this.item = item;
        this.score = score;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
