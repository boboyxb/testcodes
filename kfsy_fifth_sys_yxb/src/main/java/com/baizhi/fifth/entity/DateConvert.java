package com.baizhi.fifth.entity;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*全局的日期处理*/
public class DateConvert implements Converter<String,Date> {
    public Date convert(String s) {
        if(!StringUtils.isEmpty(s)){
            try {
                Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(s);
                System.out.println(s+"    "+parse);
                return parse;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
