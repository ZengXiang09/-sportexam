package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zx on 2016/10/17.
 */

public class PersonGradeBean  implements Serializable{
    private String birthday;
    private String no;
    private ArrayList<ItemBean>data;
    private String school;
    private String sex;
    private String name;

    public PersonGradeBean() {
    }

    public PersonGradeBean(String birthday, String no, ArrayList<ItemBean> data, String school, String sex, String name) {
        this.birthday = birthday;
        this.no = no;
        this.data = data;
        this.school = school;
        this.sex = sex;
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public ArrayList<ItemBean> getData() {
        return data;
    }

    public void setData(ArrayList<ItemBean> data) {
        this.data = data;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
