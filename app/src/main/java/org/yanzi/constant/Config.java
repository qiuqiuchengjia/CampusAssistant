package org.yanzi.constant;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {
	//这个是git服务器的



	//这个是专门用来检查用户登录
	private static String ip="59.71.5.24";
	public static final String LOGIN_URL="http://"+ip+"/PHP1000/login.php";//登录的时候检查用户登录
	public static final String LOGIN_GET_USER_PICTURE_URL = "http://"+ip+"/PHP1000/getusertouxiang.php";//这个是登录的时候获取用户的头像
	public static final String REGISTER_GET_CITY_URL = "http://"+ip+"/PHP1000/registergetcity.php";//这个是注册的时候
	public static final String REGISTER_GET_SCHOOL_URL = "http://"+ip+"/PHP1000/registergetschool.php";//这个是注册的时候
	public static final String REGISTER_GET_COLLEGE_URL = "http://"+ip+"/PHP1000/registergetcollege.php";//这个是获取学院
	public static final String REGISTER_GET_CAREER_URL = "http://"+ip+"/PHP1000/registergetcareer.php";//这个是获取专业
	public static final String REGISTER_FILL_GET_CODE_URL = "http://"+ip+"/PHP1000/registergetcode.php";//这个是注册时候获取验证码
	public static final String REGISTER_SUBMIT_USER_INFO = "http://"+ip+"/PHP1000/registergetsubmituserinfo.php";//这个是用户注册的信息提交
	public static final String KEBIAO_GET_COURSE_URL ="http://"+ip+"/PHP1000/getcourseinfo.php" ;
	public static final String KEY_CODE="utf-8";//传数据给服务器使用的字符编码
	public static final String APP_ID = "com.qiu.xiaoyuan";//这个是我APP的ID

	//底部控制栏条目Btn的标识
	public static final int BTN_FLAG_MESSAGE = 0x01;
	public static final int BTN_FLAG_CONTACTS = 0x01 << 1;
	public static final int BTN_FLAG_NEWS = 0x01 << 2;
	public static final int BTN_FLAG_SETTING = 0x01 << 3;

	//Fragment的标识
	public static final String FRAGMENT_FLAG_MESSAGE = "动态";
	public static final String FRAGMENT_FLAG_CONTACTS = "消息";
	public static final String FRAGMENT_FLAG_NEWS = "发现";
	public static final String FRAGMENT_FLAG_SETTING = "我";
	private static final String KEY_SET_KECHENG_YEAR = "kechengyear";//设置课程年份
	private static final String KEY_SET_WEEK_SPACE = "weekspace";
	public static final String LOGIN_GET_USER_TOUXIANG_STATUE = "0";//这个是登录界面获取头像的状态。，1为用户没有头像
	public static final String LOGIN_GET_USER_TOUXIANG_KEY = "touxiang";//这个是登录界面获取头像的KEY
	public static final String LOGIN_USER_TOKEN_UPDATE_STATUE = "usertokenstatus";//这个是登录的时候更新课表的token的状态
	public static final String CONNECTION_KEY = "KEY";//这个是客户端发送给服务器网络请求的KEY
	public static final String LOGIN_CONNECTION_KEY = "login";//这个是登录界面请求的值
	public static final String REGISTER_GET_CITY_KEY = "registercity";//这个是注册界面中获取城市
	public static final String USERNAME = "username";//这个是用户名
	public static final String LOGIN_PASSWORD_MD5_KEY = "password_md5";//这个是登录界面请求的用户名
	public static final String KEY_TOKEN = "token";//这个我本机的token
	public static final String KEY_LOGIN_TOKENSTATUS = "tokenstatus";//这个我本机的token
	public static final String KEY_LOGIN_SUCCESS_TOKENSTATUS = "1";//这个我本机的token在服务器更新成功的返回值
	public static final String USER_ID = "id";//这个是用户的id的键
	public static final String KEY_SCHOOL = "school";//这个是用户的学校
	public static final String KEY_USER_TOUXIANG = "touxiang";//这个是用户头像
	public static final String KEY_START_TIME = "starttime";//这个是用户的入学时间
	public static final String KEY_BANJI = "banji";//这个是用户的专业班级
	public static final String KEY_USER_SEX = "sex";//这个是用户性别，0为男，1为女
	public static final String KEY_USER_PHONE_NUMBER = "phonenumber";//这个是用户的电话
	public static final String KEY_COLLEGE = "college";//这个用户的学院
	public static final String KEY_USER_AGE = "age";//这个是用户的年纪
	public static final String KEY_USER_CITY = "city";//这个用户所在城市
	public static final String KEY_USER_ACTIE_TIME = "activetime";//这个是用户活跃次数
	public static final String KEY_USER_EMAIL = "email";//用户email
	public static final String KEY_USER_TURE_NAME = "name";//这个是用户的真实名字
	public static final String SAVE_USER_OBJECT_KEY = "userobjectkey";//这个保存user到SharedPreferences所用的key
	public static final String LOGIN_GET_USER_PICTURE = "logingetuserpicture";//在用户登录输入密码的时候获取用户的头像
	private static final String KEY_USER_TOUXIANG_PNG = "usertouxiangpng";//这个是将头像存储在SharedPreferences中
	private static final String KEY_USER_NAME = "password";
	public static final String REGISTER_SEND_PROVINCE = "register_send_province";//这个是注册界面想服务器发送省份的值
	public static final String REGISTER_SEND_CITY = "register_send_city";//这个是注册界面想服务器发送城市的值
	public static final String REGISTER_GET_CITY_STATUS = "citystatus";//这个是注册的时候获取城市的状态
	public static final String REGISTER_GET_SCHOOL_STATUS = "schoolstatus";//这个是注册的时候获取学校的状态
	public static final String REGISTER_GET_SCHOOL_KEY = "register_get_school";//这个是注册界面获取学校列表的key
	public static final String REGISTER_GET_COLLEGE_KEY = "register_get_college";//这个是注册时用来获取学院列表
	public static final String REGISTER_GET_CAREER_KEY = "register_get_career";//这个是注册时用来获取专业列表
	public static final String REGISTER_SEND_SCHOOL = "register_send_school";//这个是注册时候获取学院列表界面发送的学校信息
	public static final String REGISTER_GET_COLLEGE_STATUE = "collegestatus";//这个是获取学院列表的状态
	public static final String REGISTER_GET_CAREER_STATUE = "careerstatus";//这个是获取专业列表的状态
	public static final String REGISTER_SEND_COLLEGE = "register_send_college";//这个是注册界面用来获取专业列表
	public static final String REGISTER_GET_CAREER_STATUS = "careerstatus";//这个是获取专业的状态
	public static final String REGISTER_FILL_USERNAME_GET_CODE_KEY = "register_fill_username_get_code";//注册界面用来获取验证码
	public static final String REGISTER_FILL_GET_CODE_STATUS = "codestatus";//这个是注册界面中获取验证码的json数据中的键
	public static final String REGISTER_FILL_GET_CODE = "code";//这个是注册界面中获取验证码的json数据中的键
	public static final String REGISTER_FILL_TO_SUBMIT = "register_to_submit_user_data";//这个是注册界面用于提交用户数据的key
	public static final String REGISTER_SUBMIT_USER_NAME = "register_to_submit_username";//注册界面提交注册用户的用户名
	public static final String REGISTER_SUBMIT_PASSWORD_MD5 = "register_to_submit_password_md5";//注册界面提交注册用户的密码的md5
	public static final String REGISTER_SUBMIT_SCHOOL = "register_to_submit_school";//注册界面提交注册用户的学校
	public static final String REGISTER_SUBMIT_COLLEGE = "register_to_submit_college";//注册界面提交注册用户的学院
	public static final String REGISTER_SUBMIT_CAREER = "register_to_submit_career";//注册界面提交注册用户的专业
	public static final String REGISTER_SUBMIT_OPEN_TIME = "register_to_submit_open_time";//注册界面提交注册用户的入学年份
	public static final String REGISTER_SUBMIT_CITY = "register_to_submit_city";//注册界面提交注册用户的城市
	public static final String REGISTER_SUBMIT_PROVINCE = "register_to_submit_province";//注册界面提交注册用户的省份
	public static final String REGISTER_SUBMIT_USERNAME_STATUS = "userstatus";//注册界面提交注册用户的用户名的状态
	public static final String REGISTER_SUBMIT_STATUS = "submitstauts";//注册界面提交注册用户的注册信息的状态
	public static final String KEY_USER_PROVINCE = "province";
	public static final String KEY_USER_CAREER = "career";
	public static final String KEY_USER_STUDENT_NUMBER = "studentnumber";//这个是存储学号的SharedPreferences
	public static final String KEY_USER_STUDENT_NUMBER_PASSWORD = "studentnumberpassword";//
	public static final String THIS_WEEK_TEXTVIEW = "(本周)";
	public static final String NO_THIS_WEEK_TEXTVIEW = "(非本周)";
	public static final String NET_KEBIAO_GET_COURSE = "kebiao_get_course";//这个是课程表页面用来获取课程表的key
	public static final String STUDENT_NUMBER = "studentnumber";//这个是用户的学号
	public static final String STUDENT_NUMBER_PASSWORD = "studentnumberpassword";//这个是用户的学号密码
	private static final String KEY_KEBIAO_CACHED = "key_cached_kebiao";//这个是用来缓存课表的key
	private static final String KEY_THIS_WEEK = "thisweek";//缓存当前周的key
	public static final String GET_KECHENG_STATUE = "kechengstatue";//这个是获取课表的状态
	public static final String GET_COURSE_CREDIT = "credit";//学分数据的key
	public static final String GET_COURSE_NAME = "name";//获取课程的课程的名字
	public static final String GET_COURSE_PEROPD = "period";//获取课程的总学时
	public static final String GET_COURSE_TEACH_PERIOD = "teachPeriod";//获取课程的老师授课学时
	public static final String GET_COURSE_COMPUTER_PERIOD = "computerPeriod";//获取课程的上机授课学时
	public static final String GET_COURSE_TEACH_WAY = "teachWay";//获取课程的授课方式
	public static final String GET_COURSE_CHECK_STUDENT = "checkStudent";//获取课程的考查方式
	public static final String GET_COURSE_PLACE = "place";//课程地点
	public static final String GET_COURSE_SERIAL_NUMBER = "serialNumber";//课程序号
	public static final String GET_COURSE_DAY_OF_WEEK = "dayOfWeek";//一周中的哪一天
	public static final String GET_COURSE_CLASSES = "classes";//课程类型
	public static final String GET_COURSE_TEACHER = "teacher";//获取课程的老师
	public static final String GET_COURSE_CLASS_TIME = "classTime";//课程时间
	public static final String GET_COURSE_WEEK = "week";//周数
	public static final String GET_COURSE_SCHOOL_YEAR = "schoolYear";//获取课程学年
	public static final String GET_COURSE_START_WEEK = "startWeek";//开学是一年中的第几周
	public static final String COURSE_SET_FAIL_STRING = "失败";//这个是课程数据库填写失败之后的提示
	public static final String GET_COURSE_SEMESTER = "semester";//获取课程学期
	public static final String SAVE_KECHENG_OBJECT_KEY = "key_save_kecheng_object";//保存课程集合对象的key
	private static final String KEY_SCHOOL_YEAR = "key_school_year";//当前学年的key
	private static final String KEY_SEMESTER = "key_school_semester";//当前学期的key
	public static final String KEBIAO_LOGIN_TOKEN_STATUES = "kebiaotokenstatus";
	public static final String OK_TO_QUIT_TITLE = "确认退出";//这个是设置界面确定是否退出账号的标题
	public static final String OK_TO_QUIT_CONTENT = "您是否确认退出账号？";//这个是设置界面确定是否退出账号的内容
	private static final String KEY_SET_SHOW_WEEK = "showweek";


	//路径
	public static final String SAVE_COURSE_TO_FILE_PATH = "/Qiuqian/xiaoyuan/Course";//将课程信息字符串存进SD卡文件中
	public static String  KEY_USER_HEAD_PATH="/Qiuqian/xiaoyuan/head";//这个是将头像存储在本地的本地文件路径
	public static String  KEY_USER_USER_PATH="/Qiuqian/xiaoyuan/user";//这个是将头像存储在本地的本地文件路径
	public static String  KEY_IMAGE_CACHE_PATH="/Qiuqian/xiaoyuan/Cache/image";//这个图片缓存路径
	public static String  KEY_KEBIAO_BACKGROUND_PATH="/Qiuqian/xiaoyuan/Course/background";//这个课表背景


     //activity的头信息
	public static final String HEAD_FILL_USER_INFO = "填写信息";//
	public static final String HEAD_COURSE_SETTING = "设置";
	public static final String HEAD_REGISTER_CITY = "选择城市";
	public static final String HEAD_REGISTER_SCHOOL = "选择学校";
	public static final String HEAD_REGISTER_CAREER = "选择专业";
	public static final String HEAD_REGISTER_COLLEGE = "选择学院";
	public static final String HEAD_REGISTER_PROVINCE = "选择省份";
	public static final String HEAD_REGISTER_SHOW_INFO = "确认信息";
	public static final String HEAD_SCHOOL_OPEN_TIME = "选择入学年份";


	//file 文件
	public static final String FILE_YEAR_SEMESTER = "yearSemester";//用于存储用户最近一次课表是哪个学期和学年的文件
	public static final String FILE_USERNAME = "username";//通过userbitmap文件去找到最后一次登录的用户名



	//这个是课程表表头的月份
	public static final String DATE_ONE_MONTH = "1月";
	public static final String DATE_TWO_MONTH = "2月";
	public static final String DATE_THREE_MONTH = "3月";
	public static final String DATE_FOUR_MONTH = "4月";
	public static final String DATE_FIVE_MONTH = "5月";
	public static final String DATE_SIX_MONTH = "6月";
	public static final String DATE_SEVEN_MONTH = "7月";
	public static final String DATE_EIGHT_MONTH = "8月";
	public static final String DATE_NINE_MONTH = "9月";
	public static final String DATE_TEN_MONTH = "10月";
	public static final String DATE_ELEVEN_MONTH = "11月";
	public static final String DATE_TWELVE_MONTH = "12月";
	public static final String DATE_WEEK_MONDAY = "周一";
	public static final String DATE_WEEK_TUESDAY = "周二";
	public static final String DATE_WEEK_WEDNESDAY = "周三";
	public static final String DATE_WEEK_THURSDAY = "周四";
	public static final String DATE_WEEK_FRIDAY = "周五";
	public static final String DATE_WEEK_STAURDAY = "周六";
	public static final String DATE_WEEK_SUNDAY = "周日";


	//这个是课表下拉选择周数
	public static final String KEBIAO_WEEK_1 = "第一周";
	public static final String KEBIAO_WEEK_2 = "第二周";
	public static final String KEBIAO_WEEK_3 = "第三周";
	public static final String KEBIAO_WEEK_4 = "第四周";
	public static final String KEBIAO_WEEK_5 = "第五周";
	public static final String KEBIAO_WEEK_6 = "第六周";
	public static final String KEBIAO_WEEK_7 = "第七周";
	public static final String KEBIAO_WEEK_8 = "第八周";
	public static final String KEBIAO_WEEK_9 = "第九周";
	public static final String KEBIAO_WEEK_10 = "第十周";
	public static final String KEBIAO_WEEK_11 = "第十一周";
	public static final String KEBIAO_WEEK_12 = "第十二周";
	public static final String KEBIAO_WEEK_13 = "第十三周";
	public static final String KEBIAO_WEEK_14 = "第十四周";
	public static final String KEBIAO_WEEK_15 = "第十五周";
	public static final String KEBIAO_WEEK_16 = "第十六周";
	public static final String KEBIAO_WEEK_17 = "第十七周";
	public static final String KEBIAO_WEEK_18 = "第十八周";
	public static final String KEBIAO_WEEK_19 = "第十九周";
	public static final String KEBIAO_WEEK_20 = "第二十周";
	public static final String KEBIAO_WEEK_21 = "第二十一周";
	public static final String KEBIAO_WEEK_22 = "第二十二周";
	public static final String KEBIAO_WEEK_23 = "第二十三周";
	public static final String KEBIAO_WEEK_24 = "第二十四周";
	public static final String KEBIAO_WEEK_25 = "第二十五周";


	//颜色
	public static final int COLOR_KEBIAO_BULE_TEXT = 0xFF0079FF;//这个是课表的白色字体的颜色
	public static final int COLOR_KEBIAO_BULT_BACKGROUND = 0x660079FF;//这个是课表的蓝色的背景
	public static final int COLOR_KEBIAO_WHITE_TEXT = 0xFFFFFFFF;

	//图片
	public static final String KEY_USER_TOUXIANG_STATUE = "1";//用户的头像状态，1是没有头像



	//按钮
	public static final String BTN_OK_TO_QUIT = "确认";//设置界面弹出窗口的确认按钮
	public static final String BTN_CANCEL_TO_QUIT = "取消";//设置界面弹出窗口的取消按钮

	/**
 * 这个是将手机的token缓存在手机中
 * */
	public static String getCachedToken(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_TOKEN,null);
	}
	public static void cacheToken(Context context,String token){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(KEY_TOKEN, token);
		e.commit();
	}

	/**
	 * 这个是将学号缓存在手机中
	 * */
	public static String getCachedStudentNumber(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_USER_STUDENT_NUMBER,null);
	}
	public static void cacheStudentNumber(Context context,String token){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(KEY_USER_STUDENT_NUMBER, token);
		e.commit();
	}

	/**
	 * 这个是用户名
	 *
	 */
	public static String getCachedUsername(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_USER_NAME,null);
	}
	public static void cacheUsername(Context context,String url){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(KEY_USER_NAME, url);
		e.commit();
	}


	/**
	 * 这个是开学周是一年中的第几周
	 *
	 */
	public static String getCachedThisWeek(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_THIS_WEEK,null);
	}
	public static void cacheThisWeek(Context context,String url){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(KEY_THIS_WEEK, url);
		e.commit();
	}
	/**
	 * 例如  2015-2016学年
	 * 当前学年
	 *
	 */
	public static String getCachedSchoolYear(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_SCHOOL_YEAR,null);
	}
	public static void cacheSchoolYear(Context context,String url){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(KEY_SCHOOL_YEAR, url);
		e.commit();
	}
	/**
	 * 当前是上学期还是下学期
	 *
	 */
	public static String getCachedSemester(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_SEMESTER,null);
	}
	public static void cacheSemester(Context context,String url){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(KEY_SEMESTER, url);
		e.commit();
	}


	/**
	 * 这个头像的bitmap的字符串
	 *
	 */
	public static String getCachedUsernametouxiang(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(KEY_USER_TOUXIANG_PNG,null);
	}
	public static void cacheUsernametouxiang(Context context,String touxiang){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(KEY_USER_TOUXIANG_PNG, touxiang);
		e.commit();
	}

	/**
	 * 这个是设置周数间隔
	 *
	 */
	public static String getCachedWeekSpace(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(Config.KEY_SET_WEEK_SPACE,null);
	}
	public static void cacheWeekSpace(Context context,String touxiang){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(Config.KEY_SET_WEEK_SPACE, touxiang);
		e.commit();
	}

	/**
	 * 这个是设置课程的年份
	 *
	 */
	public static String getCachedKechengYear(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(Config.KEY_SET_KECHENG_YEAR,null);
	}
	public static void cacheWeekKechengYear(Context context,String touxiang){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(Config.KEY_SET_KECHENG_YEAR, touxiang);
		e.commit();
	}

	/**
	 * 这个是设置课程当前显示的是第几周
	 *
	 */
	public static String getCachedShowWeek(Context context){
		return context.getSharedPreferences(APP_ID,Context.MODE_APPEND).getString(Config.KEY_SET_SHOW_WEEK,null);
	}
	public static void cacheWeekShowWeek(Context context,String touxiang){
		SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putString(Config.KEY_SET_SHOW_WEEK, touxiang);
		e.commit();
	}
}
