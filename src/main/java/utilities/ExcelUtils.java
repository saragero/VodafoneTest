package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    // return only one row
    public static List<String> getOneRowData(String fullExcelPath,String sheetName,List<String> columnsNameList, List<String> column1FiltrationValueList)throws IOException {
        List<String> rowData = new ArrayList<>();
        List<List<String>> filteredSheetData = getColumnData(fullExcelPath,sheetName,columnsNameList,column1FiltrationValueList);
        if (!filteredSheetData.isEmpty()){
            rowData = filteredSheetData.get(0);
        }
        return rowData;
    }
    //filter about the desired column [ specially first column ]
    public static List<List<String>> getColumnData(String fullExcelPath, String sheetName, List<String> columnsNameList, List<String> column1FiltrationValueList)throws IOException {
        List<List<String>> allSheetData = getData(fullExcelPath,sheetName,columnsNameList);
        List<List<String>> filteredSheetData = new ArrayList<>();
        for (List<String> rowDataList: allSheetData){
            String firstCellValue = rowDataList.get(0);
            if (column1FiltrationValueList.contains(firstCellValue)){
                rowDataList.remove(0);
                filteredSheetData.add(rowDataList);
            }
        }
        //System.out.println(filteredSheetData);
        return filteredSheetData;
    }

        //get all the excel data [ matrix array ]
        public static List<List<String>> getData(String fullExcelPath, String sheetName, List<String> columnsNameList)throws IOException {
        List<List<String>> data = new ArrayList<>();
        //fileInputStream argument
        FileInputStream file = new FileInputStream(fullExcelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int sheets = workbook.getNumberOfSheets();
        for (int i=0; i<sheets;i++){
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)){
                XSSFSheet sheet = workbook.getSheetAt(i);
                //identify testcases columns by scanning the entire 1st row
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next(); //get the first row in Excel sheet
                //rows.next(); //get the second row in Excel sheet
                Iterator <Cell> ce= firstRow.cellIterator();
                int k= 0; //index for while loop to get at which column we found the case we searched about[ same as for loop exactly ]
                List<Integer> columnList = new ArrayList<>();
                while (ce.hasNext()){
                    Cell value = ce.next();
                    for(String item: columnsNameList){
                        if (value.getStringCellValue().equalsIgnoreCase(item)){
                            //desired column
                            columnList.add(k); //actual column number
                        }
                    }
                    k++;
                }
                //scan the printed column to get a particular testCase
                while (rows.hasNext()){
                    Row r = rows.next();
                    List<String> currentRowData = new ArrayList<>();
                    for (int cellIndex:columnList){
                        currentRowData.add(getCellStringValue(r.getCell(cellIndex)));
                    }
                    data.add(currentRowData);
                }
            }
        }
        //System.out.println(data);
        return data;
    }

    //handling Cell type conversion to String
    static String getCellStringValue(Cell cell){
        String value = null;
        if (cell != null){
            switch (cell.getCellTypeEnum()){
                case STRING :
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
            }
        }
        return value;
    }

}

