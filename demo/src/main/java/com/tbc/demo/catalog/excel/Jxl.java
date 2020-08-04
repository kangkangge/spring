package com.tbc.demo.catalog.excel;

import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Clock;
import java.util.Date;


public class Jxl {

    @Test
    public void test() throws IOException, WriteException {


    }

    public File writeExcel() throws IOException, WriteException {
        File xlsFile = new File("C:\\Users\\gekang\\Desktop\\邮件发送失败列表.xls");
        // 创建一个工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        // 创建一个工作表
        WritableSheet sheet = workbook.createSheet("失败邮件详情", 0);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 5; col++) {
                // 向工作表中添加数据
                if (row == 0) {
                    setTitle(sheet);
                } else {
                    switch (col) {
                        case 0:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 1:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 2:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 3:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 4:
                            sheet.addCell(new Label(col, row, Clock.systemDefaultZone().instant().toString()));
                            break;
                    }

                }
            }
        }
        workbook.write();
        workbook.close();
        return xlsFile;
    }

    public static void setTitle(WritableSheet sheet) throws WriteException {
        WritableCellFormat writableCellFormat = new WritableCellFormat();
        //设置背景色
        writableCellFormat.setBackground(Colour.GREEN);
        //设置字体
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        writableCellFormat.setFont(font);
        //设置边框
        writableCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        //设置列宽
        sheet.getSettings().setDefaultColumnWidth(40);
        sheet.addCell(new Label(0, 0, "邮件标题", writableCellFormat));
        sheet.addCell(new Label(1, 0, "接收人姓名", writableCellFormat));
        sheet.addCell(new Label(2, 0, "邮箱账号", writableCellFormat));
        sheet.addCell(new Label(3, 0, "发送状态", writableCellFormat));
        sheet.addCell(new Label(4, 0, "发送状态", writableCellFormat));
    }

}
