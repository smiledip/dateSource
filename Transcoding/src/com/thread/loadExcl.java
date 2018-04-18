package thread;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author smile_dip
 * @Date 2017/4/18 12:36
 * @Describe 读取数据到map，用于做转换表
 */
public class loadExcl {

    public Map<String, String> findlist() {
        Map<String, String> maps = new HashMap<String, String>();
        try {
            Workbook book = Workbook.getWorkbook(new File("D://县及县以上行政区划分代码.xls"));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            // 得到第一列第一行的单元格
            System.out.println("数据添加到列表begin");
            for (int i = 0; i < 3224; i++) {
                int stat = 3;
                Cell cell1 = sheet.getCell(0, i + 2);
                Cell cell2 = sheet.getCell(3, i + 2);

                String result = cell1.getContents();
                String result2 = cell2.getContents();

                if (result == null || result == "" || "".equals(result)) {
                    break;
                }
                if (result2 == null || result2 == "" || "".equals(result2)) {
                    cell2 = sheet.getCell(2, i + 2);
                    result2 = cell2.getContents();
                    stat = 2;
                    if (result2 == null || result2 == "" || "".equals(result2)) {
                        cell2 = sheet.getCell(1, i + 2);
                        result2 = cell2.getContents();
                        stat = 1;
                    }
                }
                String pid = "0";
                switch (stat) {
                    case 1:
                        pid = "0";
                        break;
                    case 2:
                        pid = result.toString().substring(0, 2) + "0000";
                        break;
                    case 3:
                        pid = result.toString().substring(0, 4) + "00";
                        break;

                }
                maps.put(result2, result);
                //   lis.add(result + "#" + result2 + "#" + pid + "#" + stat);
            }

            System.out.println("数据添加到列表end");
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // return lis;
        return maps;
    }


}
