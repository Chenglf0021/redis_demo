package com.lomgfei.util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SimplePdf {
    public static void main(String[] args) throws IOException,
            DocumentException {
        //第一步：创建一个文档实例 设置文档纸张为A4，文档排列方式为横向排列
        //实现A4纸页面 并且纵向排列（不设置则为横向
        Document document = new Document(PageSize.A4.rotate());
        // 第二步：创建PdfWriter对象，设置pdf生成路径
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E:/demo.pdf"));
        String userPassword = "123456";
        //拥有者密码
        String ownerPassword = "hd";
        pdfWriter.setEncryption(userPassword.getBytes(), ownerPassword.getBytes(), PdfWriter.ALLOW_PRINTING,
                PdfWriter.ENCRYPTION_AES_128);
        // 第三步：打开文档进行我们需要的操作
        document.open();
        // 第四步：创建第一页（如果只有一页的话，这一步可以省略）
        document.newPage();
        // 第五步：在文档中添加内容
        document.add(new Paragraph("my first pdf demo"));

        //=======================================  添加属性=====
        //标题
        document.addTitle("this is a title");
        //作者
        document.addAuthor("chenglf");
        //主题
        document.addSubject("this is subject");
        //关键字
        document.addKeywords("Keywords");
        //创建时间
        document.addCreationDate();
        //=======================================  添加图片=====
        //图片2https://static.cnblogs.com/images/adminlogo.gif
        Image image2 = Image.getInstance(new URL("https://static.cnblogs.com/images/adminlogo.gif"));
        //将图片2添加到pdf文件中
        document.add(image2);
        // 关闭文档
        document.close();
        // 关闭书写流
        pdfWriter.close();
    }

    @Test
    public void testWaterPrint() throws FileNotFoundException, DocumentException {
        //实现A4纸页面 并且横向显示（不设置则为纵向）
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E:/water.pdf"));
        // 打开文档
        document.open();
        // 创建第一页（如果只有一页的话，这一步可以省略）
        document.newPage();

        // 加入水印
        PdfContentByte waterMar = pdfWriter.getDirectContentUnder();
        // 开始设置水印
        waterMar.beginText();
        // 设置水印透明度
        PdfGState gs = new PdfGState();
        // 设置填充字体不透明度为0.4f
        gs.setFillOpacity(0.4f);
        try {
            // 设置水印字体参数及大小                                  (字体参数，字体编码格式，是否将字体信息嵌入到pdf中（一般不需要嵌入），字体大小)
            waterMar.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED), 60);
            // 设置透明度
            waterMar.setGState(gs);
            // 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度
            waterMar.showTextAligned(Element.ALIGN_RIGHT, "chenglf writer", 500, 430, 45);
            // 设置水印颜色
            waterMar.setColorFill(BaseColor.GRAY);
            //结束设置
            waterMar.endText();
            waterMar.stroke();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            waterMar = null;
            gs = null;
        }
        // 加入文档内容
        document.add(new Paragraph("my first pdf demo"));
        // 关闭文档
        document.close();
        pdfWriter.close();

    }

    @Test
    public void testWaterPrintInImage() throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("e:/waterImage.pdf"));
        // 打开文档
        document.open();
        // 创建第一页（如果只有一页的话，这一步可以省略）
        document.newPage();

        // 加入水印
        PdfContentByte waterMar = pdfWriter.getDirectContentUnder();
        // 开始设置水印
        waterMar.beginText();
        // 设置水印透明度
        PdfGState gs = new PdfGState();
        // 设置笔触字体不透明度为0.4f
        gs.setStrokeOpacity(0.4f);
        try {
            Image image = Image.getInstance("F:/timg.jpg");
            // 设置坐标 绝对位置 X Y
            image.setAbsolutePosition(200, 300);
            // 设置旋转弧度
            image.setRotation(30);// 旋转 弧度
            // 设置旋转角度
            image.setRotationDegrees(45);// 旋转 角度
            // 设置等比缩放
            image.scalePercent(90);// 依照比例缩放
            // image.scaleAbsolute(200,100);//自定义大小
            // 设置透明度
            waterMar.setGState(gs);
            // 添加水印图片
            waterMar.addImage(image);
            // 设置透明度
            waterMar.setGState(gs);
            //结束设置
            waterMar.endText();
            waterMar.stroke();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            waterMar = null;
            gs = null;
        }


        // 加入文档内容
        document.add(new Paragraph("my first pdf demo"));
        // 关闭文档
        document.close();
        pdfWriter.close();
    }
}
