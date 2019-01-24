package com.xumou.test.regexp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的妙用
 */
public class RegexpTest {

    public static final String ENTER_LINE = "\r\n";
    private long count;

    public String readFile(String path) throws IOException {
        return readFile(path, false);
    }

    public String readFileAddBr(String path) throws IOException {
        return readFile(path, true);
    }

    public String readFile(String path, boolean isBr) throws IOException {
        String rootPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        File file = new File(rootPath,path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder sb = new StringBuilder();
        while(true){
            String str = br.readLine();
            if(str == null)
                break;
            sb.append(str);
            if(isBr)
                sb.append(ENTER_LINE);
        }
        if(isBr)
            sb.delete(sb.length() - ENTER_LINE.length(), sb.length());
        return sb.toString();
    }

    //测试时间
    @Test
    public void executeTimer() throws IOException {
        long s1 = System.currentTimeMillis();
        test2();
        long s2 = System.currentTimeMillis();
        System.out.println(count);
        System.out.println(s2-s1);
    }

    //===========================================================================================

    /**
     * 根据正则表达式获取文本中唯一号
     */
    @Test
    public void test() throws IOException {
        String str = readFileAddBr("file/uniq.txt");
        Pattern pattern = Pattern.compile("[\\d]{5,}");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }

    /**
     * 根据正则表达式拆分字符串
     */
    @Test
    public void test2() throws IOException {
        String str = readFileAddBr("file/uniq.txt");
        Pattern patternBr = Pattern.compile(ENTER_LINE);//匹配回车换行
        Pattern patternNum = Pattern.compile("[\\d]+");//匹配1个或多个数字
        Pattern patternNumStart = Pattern.compile("[\\d]+[\\s\\S]*");//匹配数字开头
        //利用正则表达式拆分
        String[] split = patternBr.split(str);
        for (String s : split) {
            // 判断是否以数字开头
            if(patternNumStart.matcher(s).matches()){
                Matcher matcher = patternNum.matcher(s);
                // 获取第一个数字串
                if(matcher.find())
                    System.out.println(matcher.group());
            }
        }
    }

}