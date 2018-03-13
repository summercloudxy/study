package excel.service;


import excel.BigExcelReader;
import excel.CSVUtils;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xiayun on 2017/6/2.
 */

public class BigExcelToCsvService {


    public void DBToCsv() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String dbPath=path+"/excel/点表测试.xlsx";
        final Map<String,List<String>> signalMap=new HashMap<>(); //key为信号channel
        BigExcelReader reader = new BigExcelReader() {
            @Override
            public void getRows(int sheetIndex, int curRow, List<String> rowList) {
                if(rowList.size()<11){
                    return;
                }
                String channel=rowList.get(4);
                String description=rowList.get(3);
                String label=rowList.get(6);
                String plcLabel=rowList.get(7);
                String type=rowList.get(8);
                if(!("".equals(channel)||"所属PLC channel".equals(channel))) {
                    if (!signalMap.containsKey(channel)) {
                        String columnName="Tag Name,Address,Data Type,Description,Negate Value";
                        ArrayList<String> records=new ArrayList<>();
                        records.add(columnName);
                        signalMap.put(channel, records);
                    }
                    String dbCsv="\""+label+"\",\""+plcLabel+"\","+type+",\""+description+"\"";
                    List<String> signalList = signalMap.get(channel);
                    signalList.add(dbCsv);
                }
            }
        };

        reader.process(dbPath,1);
        String date=new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        for (Map.Entry<String,List<String>> entry:signalMap.entrySet()) {
            String channel=entry.getKey();
            File file=new File("D:\\csv\\"+date);
            file.mkdirs();
            CSVUtils.exportCsv(new File("D:\\csv\\"+date+"\\"+channel+".csv"),entry.getValue());
        }
    }

    public static void main(String[] args) throws Exception{
        BigExcelToCsvService service = new BigExcelToCsvService();
        service.DBToCsv();
    }
}

