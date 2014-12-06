/*
package com.gao.fileparse;

import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

*/
/**
 * User: wangchen
 * Date: 14/10/24
 * Time: 16:16
 *//*

public class ExcelParseTest {
    public static void main(String[] args) {
        Path path = Paths.get("/Users/wangchen/Downloads/result.txt");

        String regex = "mm_\\d+_\\d_\\d+";

        int index = 0;
        int count = 0;

        int rIndex = 1;
        int qIndex = 1;
        try {

            SXSSFWorkbook qWorkbook = new SXSSFWorkbook();
            Sheet qSheet = qWorkbook.createSheet("Q-args0");
            Row qRow = qSheet.createRow(0);
            qRow.createCell(0).setCellValue("Type");
            qRow.createCell(1).setCellValue("Q-args");
            qRow.createCell(2).setCellValue("Q-Orgi URL");
            qRow.createCell(3).setCellValue("Q-URL");
            qRow.createCell(4).setCellValue("Q-Match URL");
            qRow.createCell(5).setCellValue("Q-TaokeID");

            SXSSFWorkbook rWorkbook = new SXSSFWorkbook();
            Sheet rSheet = rWorkbook.createSheet("R-args0");
            Row rRow = rSheet.createRow(0);
            rRow.createCell(0).setCellValue("Type");
            rRow.createCell(1).setCellValue("R-args");
            rRow.createCell(2).setCellValue("R-Orgi URL");
            rRow.createCell(3).setCellValue("R-URL");
            rRow.createCell(4).setCellValue("R-Match URL");
            rRow.createCell(5).setCellValue("R-TaokeID");

            BufferedReader bufferedReader = Files.newBufferedReader(path);

            String str = null;
            int qRowIndex = 1;
            int rRowIndex = 1;


            String first = "";
            String second = "";
            String third = "";
            String fouth = "";
            String fifth = "";
            String sixth = "";

            String title = "";

            while ((str = bufferedReader.readLine()) != null) {


                count++;
                */
/*System.out.println(count);
                System.out.println(index);
                System.out.println(str);*//*

                if (index == 0) {
                    String[] split = str.split(":", 2);
//                    System.out.println(split[0] + "::" + split[1]);
                    if (split[0].equals("Type")){

                        first = split[1];
                    }else {
                        index = 0;
                        continue;
                    }
                }else if (index == 1) {
//                    System.out.println(str.substring(0,7) + "::" + str.substring(7));
                    second = str.substring(7);
                    title = str.substring(0,7);
                }else if (index == 2) {
                    String[] split = str.split(":", 2);
//                    System.out.println(split[0] + "::" + split[1]);
                    third = split[1];
                }else if (index == 3) {
//                    System.out.println(str.substring(0,5) + "::" +str.substring(5));
                    if (!str.contains("-URL")) {
                        index = 0;
                        continue;
                    }
                    fouth = str.substring(5);
                }else if (index == 4) {
//                    System.out.println(str.substring(0, 11) + "::" + str.substring(11));
                    if (str.length() <= 10 || !str.contains("-Match URL")) {
                        index = 0;
                        continue;
                    }
                    fifth = str.substring(11);

                } else if (index == 5) {
                    String[] split = str.split(":", 2);
//                    System.out.println(split[0] + "::" + split[1]);
                    sixth = split[1];
                }

                if (str.isEmpty()) {

                    if (title.equals("Q-args")) {

                        if (qRowIndex > 65535) {
                            System.out.println("qindex:" + qIndex);
                            qSheet = qWorkbook.createSheet("Q-args" + qIndex);
                            qRow = qSheet.createRow(0);
                            qRow.createCell(0).setCellValue("Type");
                            qRow.createCell(1).setCellValue("Q-args");
                            qRow.createCell(2).setCellValue("Q-Orgi URL");
                            qRow.createCell(3).setCellValue("Q-URL");
                            qRow.createCell(4).setCellValue("Q-Match URL");
                            qRow.createCell(5).setCellValue("Q-TaokeID");
                            qIndex++;
                        }

                        Row row = qSheet.createRow(qRowIndex);
                        row.createCell(0).setCellValue(first);
                        row.createCell(1).setCellValue(second);
                        row.createCell(2).setCellValue(third);
                        row.createCell(3).setCellValue(fouth);
                        Pattern pattern = Pattern.compile(regex);
                        java.util.regex.Matcher matcher = pattern.matcher(third);

                        while (matcher.find()) {
                            sixth += sixth + " ";
                        }
                        row.createCell(4).setCellValue(fifth);
                        row.createCell(5).setCellValue(sixth);
                        qRowIndex++;


                    }else {

                        if (rRowIndex > 65534) {
                            System.out.println("rIndex:" + rIndex);
                            rSheet = rWorkbook.createSheet("R-args" + rIndex);
                            rRow = rSheet.createRow(0);
                            rRow.createCell(0).setCellValue("Type");
                            rRow.createCell(1).setCellValue("R-args");
                            rRow.createCell(2).setCellValue("R-Orgi URL");
                            rRow.createCell(3).setCellValue("R-URL");
                            rRow.createCell(4).setCellValue("R-Match URL");
                            rRow.createCell(5).setCellValue("R-TaokeID");
                            rIndex++;
                        }

                        System.out.println(rRowIndex);

                        Row rrRow = rSheet.createRow(rRowIndex);
                        rrRow.createCell(0).setCellValue(first);
                        rrRow.createCell(1).setCellValue(second);
                        rrRow.createCell(2).setCellValue(third);
                        rrRow.createCell(3).setCellValue(fouth);
                        Pattern pattern = Pattern.compile(regex);
                        java.util.regex.Matcher matcher = pattern.matcher(third);

                        while (matcher.find()) {
                            sixth += sixth + " ";
                        }
                        rrRow.createCell(4).setCellValue(fifth);
                        rrRow.createCell(5).setCellValue(sixth);
                        rRowIndex++;
                    }
                    index = 0;
                }

                index++;
            }

            FileOutputStream qoutputStream = new FileOutputStream("/Users/wangchen/Downloads/qsheet.xlsx");
            FileOutputStream routputStream = new FileOutputStream("/Users/wangchen/Downloads/rsheet.xlsx");

            qWorkbook.write(qoutputStream);
            rWorkbook.write(routputStream);

            qoutputStream.close();
            routputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
*/
