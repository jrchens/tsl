package poi;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;

public class ReadFile {

    public static void main(String[] args) {
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(ResourceUtils.getFile("classpath:tmp/info_category_type.xlsx"));
            Map<String, String> categorys = Maps.newLinkedHashMap();
            Map<String, String> types = Maps.newLinkedHashMap();
            Map<String, String> depts = Maps.newLinkedHashMap();

            int sheetNumber = wb.getNumberOfSheets();
            for (int i = 0; i < sheetNumber; i++) {
                Sheet sheet = wb.getSheetAt(i);
                String name = sheet.getSheetName();
                depts.put(name, name);

                int rowNumber = sheet.getLastRowNum() + 1;
                for (int j = 0; j < rowNumber; j++) {
                    Row row = sheet.getRow(j);

                    short cellNumber = row.getLastCellNum();
                    for (int k = 0; k < cellNumber; k++) {
                        Cell cell = row.getCell(k);
                        String value = null;
                        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                            continue;
                        }

                        if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }

                        value = cell.getStringCellValue();

                        if (StringUtils.hasText(value)) {
                            if (k == 0) {
                                categorys.put(value, value);
                            } else if (k == 1) {
                                types.put(value, value);
                            }
                        }

                    }
                }
            }

            Iterator<Entry<String, String>> iterator = depts.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                System.out.println(String.format("dept: %s", entry.getKey()));
            }

            iterator = categorys.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                System.out.println(String.format("category: %s", entry.getKey()));
            }

            iterator = types.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                System.out.println(String.format("type: %s", entry.getKey()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(wb);
        }
    }

}
