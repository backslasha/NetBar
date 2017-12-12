package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gacl
 * 把request对象中的请求参数封装到bean中
 */
public class WebUtils {

    /**
     * 将request对象转换成T对象
     * @param request 
     * @param clazz
     * @return
     */
    public static <T> T request2Bean(HttpServletRequest request,Class<T> clazz){
        try{
            T bean = clazz.newInstance();
            Enumeration<String> e = request.getParameterNames(); 
            while(e.hasMoreElements()){
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 生成UUID
     * @return
     */
    public static String makeId(){
        return UUID.randomUUID().toString();
    }
    

    public static String standard(String givingTime){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
        givingTime = givingTime.replaceAll("([^0-9] )|([^0-9])"," ");
        givingTime = givingTime.replaceAll("\\s{1,}", " ");
        String[] numbers = givingTime.split(" ");
        if (numbers.length == 1){
            String time = numbers[0];
            SimpleDateFormat format;

            if (time.length() == 8)
                format = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
            else if (time.length() == 14)
                format = new SimpleDateFormat("yyyyMMddHHmmss",Locale.ENGLISH);
            else if (time.length() > 8){
                time = time.substring(0, 8);
                format = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
            }
            else{
                format = null;
                return sdf.format(new Date(0));
            }
            Date date = null;
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return sdf.format(date);
        }
        Vector<Integer> nums = new Vector<Integer>();
        for (int i = 0; i < 6; i++)
            nums.add(0);

        if (numbers.length > 3){
            if(Integer.parseInt(numbers[2]) > 31){
                String tmp = numbers[2].substring(0, 2);
                if (Integer.parseInt(tmp) > 31)
                    tmp = numbers[2].substring(0, 1);
                numbers[3] = numbers[2].substring(tmp.length());
                numbers[2] = tmp;
            }
        }
        for (int i = 0; i < numbers.length; i++){
            nums.setElementAt(Integer.parseInt(numbers[i]), i);
            System.out.println("i = " + i + ": nums = " + nums.get(i));
        }

        int year = nums.get(0);
        int month = nums.get(1);
        int day = nums.get(2);

        int hour = nums.get(3);
        if (hour > 24) hour = 0;
        int min = nums.get(4);
        if (min > 60) min = 0;
        int sec = nums.get(5);
        if (sec > 60) sec = 0;

        Calendar calendar = Calendar.getInstance();
        System.out.println(year+ " " + (month-1)+ " " + day+ " " + hour+ " " + min+ " " + sec);
        calendar.set(year, month-1, day, hour, min, sec);
        return sdf.format(calendar.getTime());
    }
    
}