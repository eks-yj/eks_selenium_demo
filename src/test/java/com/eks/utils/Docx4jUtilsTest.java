package com.eks.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class Docx4jUtilsTest {
    private static File DOCX_FILE = EksFileUtils.getFileBaseProject("extra/temp/DOCX_FILE.docx");
    private static File PDF_FILE = EksFileUtils.getFileBaseProject("extra/temp/PDF_FILE.pdf");
    private final static File docx4jFile = EksFileUtils.getFileBaseProject("extra/temp/docx4j.docx");
    private final static File docx4jPdfFile = EksFileUtils.getFileBaseProject("extra/temp/docx4j.pdf");
    private final static File poiFile = EksFileUtils.getFileBaseProject("extra/temp/poi.docx");
    private final static File poi2docx4jPdfFile = EksFileUtils.getFileBaseProject("extra/temp/poi2docx4j.pdf");
    @Test
    public void test1() throws Exception {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        documentPart.addStyledParagraphOfText("Title", "Hello Word!");
        documentPart.addParagraphOfText("Hello Word!");
        wordMLPackage.save(docx4jFile);
        Docx4jUtils.convertDocxToPdf(wordMLPackage, docx4jPdfFile);
    }
    @Test
    public void test2() throws Exception {
        Docx4jUtils.convertDocxToPdf(docx4jFile, docx4jPdfFile);
    }
    @Test
    public void test4() throws Exception {
        //Blank Document
        XWPFDocument document = new XWPFDocument();
        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Hello World");
        //Write the Document in file system
        FileOutputStream fileOutputStream = new FileOutputStream(poiFile);
        document.write(fileOutputStream);
        fileOutputStream.close();
        Docx4jUtils.convertDocxToPdf(poiFile, poi2docx4jPdfFile);
    }
    @Test
    public void test5() throws Exception {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(DOCX_FILE);
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("Java PoI");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("Java POI 生成word文件");
        run.setColor("696969");
        run.setFontSize(16);
        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setFill("97FFFF");
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");
        XWPFTable infoTable = document.createTable();
        infoTable.getCTTbl().getTblPr().unsetTblBorders();
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072L));
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("职位");
        infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("姓名");
        infoTableRowTwo.getCell(1).setText(": seawater");
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("生日");
        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("性别");
        infoTableRowFour.getCell(1).setText(": 男");
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("现居地");
        infoTableRowFive.getCell(1).setText(": xx");
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
        XWPFParagraph pic = document.createParagraph();
        pic.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun picRun = pic.createRun();
        List<String> filePath = new ArrayList();
        filePath.add(EksFileUtils.getFileBaseProject("extra/temp/temp.jpg").getAbsolutePath());
        filePath.add(EksFileUtils.getFileBaseProject("extra/temp/temp.jpg").getAbsolutePath());
        Iterator var22 = filePath.iterator();

        while(var22.hasNext()) {
            String str = (String)var22.next();
            picRun.setText(str);
            picRun.addPicture(new FileInputStream(str), 5, str, Units.toEMU(450.0D), Units.toEMU(300.0D));
        }

        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "ctpHeader";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[]{headerParagraph};
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "ctpFooter";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[]{footerParagraph};
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
        document.write(out);
        out.close();
        Docx4jUtils.convertDocxToPdf(DOCX_FILE, PDF_FILE);
    }
    @Test
    public void test3() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(DOCX_FILE);
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("Title");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setBold(true);
        titleParagraphRun.setFontFamily("宋体");
        titleParagraphRun.setFontSize(20);
        XWPFParagraph firstParagraph = document.createParagraph();
        firstParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = firstParagraph.createRun();
        run.addBreak();
        run.setText("TEST");
        run.setColor("000000");
        titleParagraphRun.setFontFamily("宋体");
        run.setFontSize(16);
        XWPFParagraph secondParagraph = document.createParagraph();
        XWPFRun secondRun = secondParagraph.createRun();
        secondRun.setText("HELLO");
        secondRun.setColor("000000");
        titleParagraphRun.setFontFamily("宋体");
        secondRun.setFontSize(16);
        document.write(fileOutputStream);
        Docx4jUtils.convertDocxToPdf(DOCX_FILE, PDF_FILE);
    }
}
