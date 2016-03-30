package org.yanzi.bean;

import java.io.Serializable;

/**
 * 用户类,已经被序列号了，可以保存在手机本地内存中
 */
public class User implements Serializable {
    private int id;//用户的id
    private String username;//用户名
    private String password;//用户密码
    private String school;//学校
    private String token;
    private String touxiang;
    private String startTime;//入学时间
    private String banji;//班级
    private int sex;
    private String phonenumber;
    private String college;//学院
    private int age;
    private String email;
    private String city;
    private int activetime;//活跃次数
    private String name;//真实名字
    private String nickname;//昵称
    private String province;//省份
    private String career;//专业
    private String studentnumber;//学号
    private String studentnumberpassword;//学号密码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getActivetime() {
        return activetime;
    }

    public void setActivetime(int activetime) {
        this.activetime = activetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber;
    }

    public String getStudentnumberpassword() {
        return studentnumberpassword;
    }

    public void setStudentnumberpassword(String studentnumberpassword) {
        this.studentnumberpassword = studentnumberpassword;
    }




}
