package com.lomgfei.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class PdfUtil {
    public void htmlConvertPdf(String outPutFile, String html) throws ParserConfigurationException, IOException, SAXException {
        File outFile = new File(outPutFile);
        if (!outFile.exists()) {
            outFile.getParentFile().mkdirs();
        }
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(html.getBytes("UTF-8")));


        System.out.println("文件转换成功");


    }
}
