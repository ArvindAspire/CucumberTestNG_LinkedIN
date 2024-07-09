package util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ExcelReader {

    static  String sheetName= "Sheet1";

    public static ConcurrentHashMap<String, HashMap<String,String>> getData() throws IOException {

        HashMap<String,String> userData = new HashMap<>();
        ConcurrentHashMap<String,HashMap<String,String>> userDataFile = new ConcurrentHashMap<>();
        File file= new File(System.getProperty("user.dir")+"/src/test/resources/TestData.xlsx");
        FileInputStream fis =new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet sh = wb.getSheet(sheetName);
        int attributeCount= sh.getRow(0).getLastCellNum();
        int dataCount = sh.getLastRowNum();
        int counter=0;
        for(int row=1;row<=dataCount;row++) {
            userDataFile.put(sh.getRow(row).getCell(0).toString(),readData(row,attributeCount,sh));

            counter=counter+1;

    }

            wb.close();
            fis.close();

            return userDataFile;
    }

    public static HashMap<String,String> readData(int row,int attributeCount,Sheet sh){
        HashMap<String,String> userData = new HashMap<>();

            for(int column=0;column<attributeCount;column++ ){
                if(!Objects.isNull(sh.getRow(row).getCell(column)))
                    sh.getRow(row).getCell(column).setCellType(CellType.STRING);
                userData.put(sh.getRow(0).getCell(column).toString(), Objects.isNull(sh.getRow(row).getCell(column))?"":sh.getRow(row).getCell(column).toString());
            }

        return userData;

}
}
