package org.yanzi.bean;

import org.json.JSONException;
import org.json.JSONObject;
import org.yanzi.Utils.SaveAndGetObject;
import org.yanzi.activity.ActivityKebiao;
import org.yanzi.constant.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类专门用来解析课程的josn数据
 */
public class KechengDataAnalysis {
    private ActivityKebiao context;
    private String content;
    public KechengDataAnalysis(ActivityKebiao context,String content){
        this.content= content;
        this.context= context;
    }

    public List<Course> initData() {
        List<Course> list = null;
        try {
            JSONObject jsonObject = new JSONObject(content);
           list = new ArrayList<Course>();
            String kechengStatue = jsonObject.getString(Config.GET_KECHENG_STATUE);
            if(kechengStatue.equals("1")){
                //获取当前是第几周
                Config.cacheThisWeek(context,jsonObject.getString(Config.GET_COURSE_START_WEEK));//存储是每年的第几周开学
                String [] nameArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_NAME));//课程名字数据
                String [] creditArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_CREDIT));//学分数据
                String [] periodArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_PEROPD));//总学时数据
                String [] teachPeriodArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_TEACH_PERIOD));//老师授课数据
                String [] computerPeriodArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_COMPUTER_PERIOD));//上机学时数据
                String [] teachWayArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_TEACH_WAY));//授课方式数据
                String [] checkStudentArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_CHECK_STUDENT));//考查方式数据
                String [] teacherArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_TEACHER));//任课老师数据
                String [] placeArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_PLACE));//地点数据
                String [] serialNumberArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_SERIAL_NUMBER));//序号数据
                String [] dayOfWeekArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_DAY_OF_WEEK));//一周中的第几天数据
                String [] classesArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_CLASSES));//课程类型数据
                String [] classTimeArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_CLASS_TIME));//课程是每周中的第几天
                String [] weekArray = initCommonJsonData(jsonObject.getString(Config.GET_COURSE_WEEK));//周数
                String schoolYear = jsonObject.getString(Config.GET_COURSE_SCHOOL_YEAR);//获取学年
                String semester = jsonObject.getString(Config.GET_COURSE_SEMESTER);//获取学期
                Config.cacheSchoolYear(context, schoolYear);
                Config.cacheSemester(context,semester);

                for(int x=0 ;x<nameArray.length && nameArray!=null;x++){
                    Course course = new Course();
                    course.setName(nameArray[x]);

                    //课程是每周中的第几天
                    if(x<classTimeArray.length && classTimeArray!=null){
                        String[] ct = classTimeArray[x].split(",");
                        course.setClassTime(ct);
                    }
                    //周数
                    if(x<weekArray.length && weekArray!=null){
                        String[] ct = weekArray[x].split(",");
                        course.setFormatWeek(ct);
                        String [] result = new String[15];
                        int  index=0;
                        for(int g =0;g<ct.length;g++){
                            String[] we = ct[g].split("-");
                            if(we.length==2){
                                int start =Integer.parseInt(we[0]);
                                int end =Integer.parseInt(we[1]);
                                while(start<=end){
                                    if(index<result.length){
                                        result[index] = String.valueOf(start);
                                        index++;
                                        start++;
                                    }else{
                                        String []copy =new String[result.length+5];
                                        System.arraycopy(result,0, copy,0,result.length);
                                        result = copy;
                                        result[index] = String.valueOf(start);
                                        index++;
                                        start++;
                                    }
                                }

                            }else{
                                if(index<result.length){
                                    result[index] = we[0];
                                    index++;
                                }else{
                                    String []copy =new String[result.length+5];
                                    System.arraycopy(result,0, copy,0,result.length);
                                    result = copy;
                                    result[index] = we[0];
                                    index++;
                                }

                            }

                        }
                        course.setWeek(result);

                    }
                    //学分数据
                    if(x<creditArray.length &&creditArray!=null){
                        course.setCredit(creditArray[x]);
                    }else{
                        course.setCredit(Config.COURSE_SET_FAIL_STRING);
                    }
                    //总学时数据
                    if(x<periodArray.length &&periodArray!=null ){
                        course.setPeriod(periodArray[x]);
                    }else{
                        course.setPeriod(Config.COURSE_SET_FAIL_STRING);
                    }
                    //老师授课数据
                    if(x<teachPeriodArray.length&&teachPeriodArray!=null){
                        course.setTeachPeriod(teachPeriodArray[x]);
                    }else{
                        course.setTeachPeriod(Config.COURSE_SET_FAIL_STRING);
                    }
                    //上机学时数据
                    if(x<computerPeriodArray.length&& computerPeriodArray!=null){
                        course.setComputerPeriod(computerPeriodArray[x]);
                    }else{
                        course.setComputerPeriod(Config.COURSE_SET_FAIL_STRING);
                    }
                    //授课方式数据
                    if(x<teachWayArray.length&&teachWayArray!=null){
                        course.setTeachWay(teachWayArray[x]);
                    }else{
                        course.setTeachWay(Config.COURSE_SET_FAIL_STRING);
                    }
                    //考查方式数据
                    if(x<checkStudentArray.length && checkStudentArray!=null){
                        course.setCheckStudent(checkStudentArray[x]);
                    }else{
                        course.setCheckStudent(Config.COURSE_SET_FAIL_STRING);
                    }
                    //任课老师数据
                    if(x<teacherArray.length&& teacherArray!=null){
                        course.setTeacher(teacherArray[x]);
                    }else{
                        course.setTeacher(Config.COURSE_SET_FAIL_STRING);
                    }
                    //地点数据
                    if(x<placeArray.length&&placeArray!=null){
                        course.setPlace(placeArray[x]);
                    }else{
                        course.setPlace(Config.COURSE_SET_FAIL_STRING);
                    }
                    //序号数据
                    if(x<serialNumberArray.length&&serialNumberArray!=null){
                        course.setSerialNumber(serialNumberArray[x]);
                    }else{
                        course.setSerialNumber(Config.COURSE_SET_FAIL_STRING);
                    }
                    //一周中的第几天数据
                    if(x<dayOfWeekArray.length&&dayOfWeekArray!=null){
                        course.setDayOfWeek(dayOfWeekArray[x]);
                    }else{
                        course.setDayOfWeek(Config.COURSE_SET_FAIL_STRING);
                    }
                    //课程类型数据
                    if(x<classesArray.length&&classesArray!=null){
                        course.setClasses(classesArray[x]);
                    }else{
                        course.setClasses(Config.COURSE_SET_FAIL_STRING);
                    }

                    list.add(course);

                }
                //这里将课程集合对象保存在SharedPreferences中
                SaveAndGetObject.saveObject(context, Config.SAVE_KECHENG_OBJECT_KEY, list);
            }else{
                context.failToShow();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            context.failToShow();
        }
        return list;
    }


    /**
     * 用来处理类似 [ 体育:大学英语:大学英语:大学物理 ] 类型
     * 的数据
     * @param string
     */
    private String [] initCommonJsonData(String string) {
        if(!string.equals("")){
            String [] strs = string.split(":");
            return strs;
        }else{
            return null;
        }
    }


}
