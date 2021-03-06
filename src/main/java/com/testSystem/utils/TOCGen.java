package com.testSystem.utils;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.*;
// 编码
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

public class TOCGen {
    public static void main(String[] args) throws Exception {
        // toc 所在pptx的路径
        testPpt();
    }



    public static void testPpt() {
        String path = "D:\\用户目录\\我的文档\\WeChat Files\\UinqueYang\\FileStorage\\File\\2020-01\\test.pptx";
        // String path = "D:\\temp\\temp\\test.pptx";
        File file = new File(path);
        InputStream is = null;
        SlideShow slideShow = null;
        try {
            is = new FileInputStream(file);
            if (path.endsWith(".ppt")) {
            } else if (path.endsWith(".pptx")) {
                slideShow = new XMLSlideShow(is);
            }
            if (slideShow != null) {
                // 文本内容
                StringBuilder content = new StringBuilder();
                // 一页一页读取
                for (Slide slide : (List<Slide>) slideShow.getSlides()) {
                    List shapes = slide.getShapes();
                    if (shapes != null) {
                        for (int i = 0; i < shapes.size(); i++) {
                            Shape shape = (Shape) shapes.get(i);

                            if (shape instanceof XSLFTextShape) {// 文本框
                                String text = ((XSLFTextShape) shape).getText();
                                content.append(text);
                            }

                            if (shape instanceof XSLFTable) {// 表格
                                int rowSize = ((XSLFTable) shape).getNumberOfRows();
                                int columnSize = ((XSLFTable) shape).getNumberOfColumns();
                                for (int rowNum = 0; rowNum < rowSize; rowNum++) {
                                    for (int columnNum = 0; columnNum < columnSize; columnNum++) {
                                        XSLFTableCell cell = ((XSLFTable) shape).getCell(rowNum, columnNum);
                                        if (cell != null) {
                                            String text = cell.getText();
                                            content.append(text);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (content.length() > 0) {
                        System.out.println(content);
                        content.delete(0, content.length());
                    }
                }

                // 图片内容
                List pictures = slideShow.getPictureData();
                for (int i = 0; i < pictures.size(); i++) {
                    PictureData picture = (PictureData) pictures.get(i);
                    byte[] data = picture.getData();
                    FileOutputStream out = new FileOutputStream("D:\\temp\\temp\\" + UUID.randomUUID() + ".jpg");
                    out.write(data);
                    out.close();
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (slideShow != null) {
                    slideShow.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
    }


}
