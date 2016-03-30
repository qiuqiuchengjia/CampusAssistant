package org.yanzi.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断一个字符串是不是一个电话号码
 */
public class IsPhoneNumberTools {
    public IsPhoneNumberTools() {
    }

    /**
     * 判断一个字符串是不是一个电话号码
     * @param phone
     * @return
     */
    public static boolean isPhoneNumber(String phone){
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher m = pattern.matcher(phone);
        boolean b = m.matches();
        return b;
    }
}
