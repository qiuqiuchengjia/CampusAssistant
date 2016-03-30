package org.yanzi.bean;

import java.io.Serializable;

/**
 * 这个是课程的一个对象,已经被序列化，可以保存在手机内存中
 */
public class Course implements Serializable {
    private String name;//课程的名字
    private String credit;//课程的学分数
    private String period;//课程的总学时
    private String teachPeriod;//课程老师上课的学时
    private String computerPeriod;//课程的上机学时
    private String teachWay;//老师上课的方式
    private String checkStudent;//考查的方式
    private String teacher; //任课老师
    private String[] week;//课程的周数
    private String[] classTime ;//课程是第几节课上
    private String place;//课程的地点
    private String serialNumber;//课程序号
    private String dayOfWeek;//课程是一周中的星期几上
    private String classes;//课程所属类型
    private String[] formatWeek;//格式化的课表周数数据



    public String[] getWeek() {
        return week;
    }

    public void setWeek(String[] week) {
        this.week = week;
    }

    public String[] getFormatWeek() {
        return formatWeek;
    }

    public void setFormatWeek(String[] formatWeek) {
        this.formatWeek = formatWeek;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTeachPeriod() {
        return teachPeriod;
    }

    public void setTeachPeriod(String teachPeriod) {
        this.teachPeriod = teachPeriod;
    }

    public String getComputerPeriod() {
        return computerPeriod;
    }

    public void setComputerPeriod(String computerPeriod) {
        this.computerPeriod = computerPeriod;
    }

    public String getTeachWay() {
        return teachWay;
    }

    public void setTeachWay(String teachWay) {
        this.teachWay = teachWay;
    }

    public String getCheckStudent() {
        return checkStudent;
    }

    public void setCheckStudent(String checkStudent) {
        this.checkStudent = checkStudent;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }




    public String[] getClassTime() {
        return classTime;
    }

    public void setClassTime(String[] classTime) {
        this.classTime = classTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
