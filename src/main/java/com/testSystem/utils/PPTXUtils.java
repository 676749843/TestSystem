package com.testSystem.utils;

import com.google.common.base.Splitter;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;


import java.io.File;
import java.io.FileInputStream;


public class PPTXUtils {
    public static String getToc(File file) throws Exception {
        //创建一个新的空幻灯片
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
        // 拼接字符串
        StringBuilder ret = new StringBuilder();
        // 第一张幻灯片
        XSLFSlide slide = ppt.getSlides().get(0);
        int currCha = -1;
        int count = 1;
        String title = null;
        boolean firstLine = true;

        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape textShape = (XSLFTextShape) shape;
                String text = textShape.getText();
                try {
                    currCha = Integer.parseInt(text);
                    if (currCha >= 0 && title != null) {
                        ret.append("# ").append(currCha).append('.').append(title).append('\n');
                    }
                    continue;
                } catch (Exception ex) {
                }
                for (String line : Splitter.on("\n").omitEmptyStrings().trimResults().split(text)) {
                    if (firstLine) {
                        title = line;
                        firstLine = false;
                    } else {
                        ret.append(count).append(". ").append(line).append('\n');
                        count++;
                    }
                }
            }
        }
        return ret.toString();
    }
}
