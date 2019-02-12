package com.xumou.test.web.cl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class CLUtils {

    public static boolean isNumeric(String s) {
        Matcher matcher = PatternItem.number.matcher(s);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public static Date toDate(long time){
        return new Date(time);
    }

    public static class PatternItem{
        public static Pattern number = Pattern.compile("[\\d]+");
    }

}