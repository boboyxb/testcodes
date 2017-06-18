package com.baizhi.fifth.entity;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;


public class UserPOI {
	
	public void download(ServletOutputStream os,List<User> all) throws Exception, Exception {
		//创建一个工作空间
		Workbook workbook = new HSSFWorkbook();
		//创建工作簿
		Sheet sheet = workbook.createSheet("用户信息");
		//合并单元格
		//合并区域
		CellRangeAddress address = new CellRangeAddress(0, 1, 0, 9);
		sheet.addMergedRegion(address);
		//设置整个sheet宽度
		sheet.setDefaultColumnWidth(20);
		//创建一级标题
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		CellStyle cellStyle = workbook.createCellStyle();
		//cellStyle.setAlignment(HorizontalAlignment.CENTER);//居中对齐
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 18);
		cellStyle.setFont(font);//设置字体
		cell.setCellStyle(cellStyle );//设置样式
		cell.setCellValue("用户信息");
		//创建二级标题
		Row titleRow = sheet.createRow(2);
		//创建标题列
		Field[] declaredFields = User.class.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Cell colCell = titleRow.createCell(i);
			colCell.setCellValue(declaredFields[i].getAnnotation(ToExcel.class).value());
		}
		//添加每行的数据,从第三行开始
		for (int i = 0; i < all.size(); i++) {
			Row dataRow = sheet.createRow(i+3); //数据行
			//创建数据列
			for (int j = 0; j < declaredFields.length; j++) {
				Cell dataCell = dataRow.createCell(j);
				
				//通过反射调用类中的方法
				String getMethodName = "get" + 
										declaredFields[j].getName().substring(0, 1).toUpperCase()
										+declaredFields[j].getName().substring(1);
				
				//返回方法对象 //参数一:方法的名字   //参数二:方法参数的类型
				Method getMethod = User.class.getDeclaredMethod(getMethodName, new Class[]{});
				System.out.println(getMethod);
				//执行方法  参数一:执行哪个对象中的方法    参数二:该方法的参数
				Object invoke = getMethod.invoke(all.get(i), new Object[]{});
				System.out.println(invoke);
				//考虑日期类型
				if(declaredFields[j].getType()  == Date.class){
					dataCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(invoke));
				}
				//考虑集合
				if(declaredFields[j].getType() == List.class){
					List objs =(List)invoke;
					StringBuilder sb = new StringBuilder();
					//该集合为空,结束输出此集合
					if(objs==null){
						break;
					}
					for (Object obj : objs) {
						//判断是哪个集合
						if(obj.getClass()==Address.class){
							Address add=(Address)obj;
							sb.append(add.getPersonName()+"、");
						}else if(obj.getClass()==Info.class){
							Info info=(Info)obj;
							sb.append(info.getContent());
						}else{
							Order order=(Order)obj;
							sb.append(order.getNum());
						}
					}
					dataCell.setCellValue(sb.toString());
				}
				//考虑基本属性
				if(declaredFields[j].getType() == Integer.class 
							|| declaredFields[j].getType() == String.class  
							|| declaredFields[j].getType() == Double.class){
					dataCell.setCellValue(invoke.toString());
				}
			}
		}
		//写出Excel
		workbook.write(os);
	}
	
	public static String  getValue(String key){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "主键");
		map.put("name", "姓名");
		map.put("phone", "手机号");
		map.put("password", "密码");
		map.put("salt", "盐");
		map.put("registDate", "注册时间");
		map.put("status", "状态");
		map.put("addresses", "收货地址");
		map.put("infos", "评价");
		map.put("orders", "订单");
		return map.get(key);
	}
	
}
