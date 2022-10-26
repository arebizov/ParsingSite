package ru.parsing.files.doc;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.parsing.SourceData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class SourceExcel {

    private String fileLocation = "d:\\parsing.xlsx";
    FileInputStream file = new FileInputStream(new File(fileLocation));
    Workbook workbook = new XSSFWorkbook(file);
    Sheet sheet = workbook.getSheet("Сводная");
    private  String storeName = "ExcelDocumentDUSS";
    public List<SourceData> listSource = new ArrayList<>();
    Date start;
    Date end;
    long dateNow = Instant.now().toEpochMilli();


    public List<SourceData> parsData () {

        for (Row row : sheet) {

            if (row.getRowNum() == 0) {

            } else {
                String offerName = row.getCell(1).getStringCellValue();
                String unit = row.getCell(2).getStringCellValue();
                Double price = row.getCell(4).getNumericCellValue();
                Date date = row.getCell(3).getDateCellValue();
                Calendar vStart = Calendar.getInstance();
                vStart.setTime(date);
                vStart.set(Calendar.DAY_OF_MONTH,
                        vStart.getActualMinimum(Calendar.DAY_OF_MONTH));
                start = vStart.getTime();

                Calendar vEnd = Calendar.getInstance();

                vEnd.set(Calendar.DAY_OF_MONTH,
                        vEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
                end = vEnd.getTime();

                long period = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) + 1;

                for(int j=0;j<period;j++){

                    Calendar vStart2 = Calendar.getInstance();
                    vStart2.setTime(start);
                    vStart2.add(Calendar.DAY_OF_MONTH, j);
                    Date date1 = vStart2.getTime();

                    if( date1.getTime()<=dateNow){
                        listSource.add(new SourceData(storeName ,offerName,unit,price,vStart2.getTime()) );
                    }

                }

            }
        }
        return listSource;
    }
    public SourceExcel() throws IOException {
    }
}