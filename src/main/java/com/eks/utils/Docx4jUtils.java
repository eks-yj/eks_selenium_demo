package com.eks.utils;

import lombok.Cleanup;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;

public class Docx4jUtils {
    public static void convertDocxToPdf(File docxFile, File pdfFile) throws Exception {
        @Cleanup InputStream inputStream = new FileInputStream(docxFile);
        WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.load(inputStream);
        wordprocessingMLPackage.setFontMapper(getFontMapper());
        convertDocxToPdf(wordprocessingMLPackage, pdfFile);
    }
    public static void convertDocxToPdf(OpcPackage opcPackage, File pdfFile) throws Exception {
        FOSettings foSettings = Docx4J.createFOSettings();
        foSettings.setWmlPackage(opcPackage);
        @Cleanup OutputStream outputStream = new FileOutputStream(pdfFile);
        Docx4J.toFO(foSettings, outputStream, Docx4J.FLAG_EXPORT_PREFER_XSL);
    }
    public static Mapper getFontMapper() {
        Mapper mapper = new IdentityPlusMapper();
        mapper.put("隶书", PhysicalFonts.get("LiSu"));
        mapper.put("宋体", PhysicalFonts.get("SimSun"));
        mapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
        mapper.put("黑体", PhysicalFonts.get("SimHei"));
        mapper.put("楷体", PhysicalFonts.get("KaiTi"));
        mapper.put("新宋体", PhysicalFonts.get("NSimSun"));
        mapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
        mapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
        mapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
        mapper.put("仿宋", PhysicalFonts.get("FangSong"));
        mapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
        mapper.put("幼圆", PhysicalFonts.get("YouYuan"));
        mapper.put("华文宋体", PhysicalFonts.get("STSong"));
        mapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));
        mapper.put("等线", PhysicalFonts.get("SimSun"));
        mapper.put("等线 Light", PhysicalFonts.get("SimSun"));
        mapper.put("华文琥珀", PhysicalFonts.get("STHupo"));
        mapper.put("华文隶书", PhysicalFonts.get("STLiti"));
        mapper.put("华文新魏", PhysicalFonts.get("STXinwei"));
        mapper.put("华文彩云", PhysicalFonts.get("STCaiyun"));
        mapper.put("方正姚体", PhysicalFonts.get("FZYaoti"));
        mapper.put("方正舒体", PhysicalFonts.get("FZShuTi"));
        mapper.put("华文细黑", PhysicalFonts.get("STXihei"));
        mapper.put("宋体扩展",PhysicalFonts.get("simsun-extB"));
        mapper.put("仿宋_GB2312",PhysicalFonts.get("FangSong_GB2312"));
        mapper.put("新細明體",PhysicalFonts.get("SimSun"));
        //解决宋体(正文)和宋体(标题)的乱码问题
        PhysicalFonts.put("PMingLiU", PhysicalFonts.get("SimSun"));
        PhysicalFonts.put("新細明體", PhysicalFonts.get("SimSun"));
        return mapper;
    }
}
