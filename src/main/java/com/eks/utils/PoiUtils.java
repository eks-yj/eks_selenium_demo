package com.eks.utils;

import lombok.Cleanup;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class PoiUtils {
    private static final String FOUR_SPACES_STRING = "    ";
    private static final BigInteger ZERO_BIGINTEGER = BigInteger.valueOf(0L);
    private static final Integer DEFAULT_TITLE_FONT_SIZE_INTEGER = 20;
    private static final Integer DEFAULT_PARAGRAPH_FONT_SIZE_INTEGER = 16;
    private static final String DEFAULT_RGB_STRING = "000000";
    private static final String DEFAULT_FONT_FAMILY_STRING = "宋体";

    public static CTSectPr getCtPageSz(XWPFDocument xwpfDocument){
        return xwpfDocument.getDocument().getBody().addNewSectPr();
    }
    public static void setPadding(CTSectPr ctSectPr, STPageOrientation.Enum stPageOrientationEnum, BigInteger widthBigInteger, BigInteger heightBigInteger) {
        CTPageSz ctPageSz = ctSectPr.addNewPgSz();
        ctPageSz.setOrient(stPageOrientationEnum);
        ctPageSz.setW(widthBigInteger);
        ctPageSz.setH(heightBigInteger);
    }
    public static void setPadding(CTSectPr ctSectPr, BigInteger leftAndRightBigIntegerBigInteger, BigInteger topAndBotomBigInteger) {
        setPadding(ctSectPr, leftAndRightBigIntegerBigInteger, leftAndRightBigIntegerBigInteger, topAndBotomBigInteger, topAndBotomBigInteger);
    }
    public static void setPadding(CTSectPr ctSectPr, BigInteger leftBigIntegerBigInteger, BigInteger rightBigInteger, BigInteger topBigInteger, BigInteger bottomBigInteger) {
        CTPageMar ctPageMar = ctSectPr.addNewPgMar();
        ctPageMar.setLeft(leftBigIntegerBigInteger);
        ctPageMar.setRight(rightBigInteger);
        ctPageMar.setTop(topBigInteger);
        ctPageMar.setBottom(bottomBigInteger);
    }
    public static void write(XWPFDocument xwpfDocument, File outFile) throws IOException {
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        xwpfDocument.write(fileOutputStream);
    }
    public static void setTitle(XWPFDocument xwpfDocument, String titleString){
        setTitle(xwpfDocument, titleString, DEFAULT_TITLE_FONT_SIZE_INTEGER);
    }
    public static void setTitle(XWPFDocument xwpfDocument, String titleString, int fontSizeInt){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(titleString);
        xwpfRun.setBold(true);
        xwpfRun.setFontSize(fontSizeInt);
        xwpfRun.setColor(DEFAULT_RGB_STRING);
        xwpfRun.setFontFamily(DEFAULT_FONT_FAMILY_STRING);
    }
    public static void setParagraph(XWPFDocument xwpfDocument, String paragraphString){
        setParagraph(xwpfDocument, paragraphString, DEFAULT_PARAGRAPH_FONT_SIZE_INTEGER, ZERO_BIGINTEGER, ZERO_BIGINTEGER, ZERO_BIGINTEGER);
    }
    public static void setParagraph(XWPFDocument xwpfDocument, String paragraphString, int fontSizeInt, BigInteger spacingBefareBigInteger, BigInteger spacingAfterBigInteger, BigInteger lineBigInteger){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        CTP ctp = xwpfParagraph.getCTP();
        CTPPr ctpPr = ctp.getPPr();
        if (ctpPr == null){
            ctpPr = ctp.addNewPPr();
        }
        CTSpacing spacing = ctpPr.isSetSpacing() ? ctpPr.getSpacing() : ctpPr.addNewSpacing();
        spacing.setBefore(spacingBefareBigInteger);
        spacing.setAfter(spacingAfterBigInteger);
        spacing.setLineRule(STLineSpacingRule.AUTO);
        spacing.setLine(lineBigInteger);
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(paragraphString);
        xwpfRun.setFontSize(fontSizeInt);
        xwpfRun.setColor(DEFAULT_RGB_STRING);
        xwpfRun.setFontFamily(DEFAULT_FONT_FAMILY_STRING);
    }
}
