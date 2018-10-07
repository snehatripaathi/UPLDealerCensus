package com.upl.upl_survey.Manager;


import com.upl.upl_survey.Dao.UserDao;
import com.upl.upl_survey.Model.CallStatusData;
import com.upl.upl_survey.reports.XlsxBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class CallStatusSerivce {

    @Autowired
    private UserDao userDao;
    private static final Logger logger=LoggerFactory.getLogger(CallStatusSerivce.class);

    @Value("${callStatusReportPath}")
    String callStatusReportPath;


    public byte[] createCallStatusReport(){

        List<CallStatusData> data=userDao.getCallStatusData();
        Map<String, Map<String, List<CallStatusData>>>dataMap=new HashMap<>();
        dataMap=massageDataForReport(data);
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
        String creationDate=sdf.format(new Date());
        byte[] report =null;
        XlsxBuilder reportBuilder=new XlsxBuilder().
                startSheet("Call Status Report").                            // start with sheet
                startRow().                                          // then go row by row
                setRowTitleHeight().                                 // set row style, add borders and so on
                addTitleTextColumn("Call Status Report").                    // add columns
                startRow().                                          // another row
                setRowTitleHeight().                                 // row styling
                setRowThinBottomBorder().
                addBoldTextLeftAlignedColumn("Creation Date:").
                addTextLeftAlignedColumn(creationDate).
                startRow().                                          // empty row
                startRow().                                          // header row
                setRowTitleHeight().
                setRowThickTopBorder().
                setRowThickBottomBorder().
                addBoldTextCenterAlignedColumn("State").
                addBoldTextCenterAlignedColumn("District").
                addBoldTextCenterAlignedColumn("Sub District").
                addBoldTextCenterAlignedColumn("Call Status").
                addBoldTextCenterAlignedColumn("Count");

                for(Map.Entry<String, Map<String, List<CallStatusData>>>  enrty:dataMap.entrySet()) {
                    String stateName=enrty.getKey();
                    Map<String, List<CallStatusData>> distrtWiseMap=enrty.getValue();
                    reportBuilder.startRow()
                    .addBoldTextCenterAlignedColumn(stateName);
                    Long stateCount=new Long("0");
                    for(Map.Entry<String,List<CallStatusData>> districtMap:distrtWiseMap.entrySet()){
                        reportBuilder.startRow()
                                     .addTextLeftAlignedColumn("")
                                     .addBoldTextCenterAlignedColumn(districtMap.getKey());
                        List<CallStatusData> subdistrictData=districtMap.getValue();
                        Long districtCount=new Long("0");
                        for(CallStatusData subdistrict:subdistrictData){
                            districtCount=districtCount+subdistrict.getStatusCount();
                            reportBuilder.startRow()
                                    .addTextLeftAlignedColumn("")
                                    .addTextLeftAlignedColumn("")
                                    .addTextLeftAlignedColumn(subdistrict.getSubDistrict())
                                    .addTextLeftAlignedColumn(subdistrict.getCallStatus())
                                    .addTextLeftAlignedColumn(String.valueOf(subdistrict.getStatusCount()));
                        }
                        stateCount=stateCount+districtCount;
                        StringBuilder countStr=new StringBuilder(districtMap.getKey());
                        countStr.append(" count is:");
                        reportBuilder.startRow()
                        .setRowTitleHeight()                                 // row styling
                                .setRowThinBottomBorder()
                                .addTextLeftAlignedColumn("")
                                .addTextLeftAlignedColumn(countStr.toString())
                                .addTextLeftAlignedColumn("")
                                .addTextLeftAlignedColumn("")
                                .addTextLeftAlignedColumn(String.valueOf(districtCount));

                    }
                    StringBuilder countStr=new StringBuilder(stateName);
                    countStr.append(" count is:");

                    reportBuilder.startRow()
                            .setRowTitleHeight()                                 // row styling
                            .setRowThickBottomBorder()
                            .addTextLeftAlignedColumn(countStr.toString())
                            .addTextLeftAlignedColumn("")
                            .addTextLeftAlignedColumn("")
                            .addTextLeftAlignedColumn("")
                            .addTextLeftAlignedColumn(String.valueOf(stateCount));


                }
               reportBuilder.startRow().
                setRowThinTopBorder().
                startRow().
                startRow().                                          // footer row
              //  addTextLeftAlignedColumn("This is just a footer and I use it instead of 'Lorem ipsum dolor...'").
                setColumnSize(0, "XXXXXXXXXXXXX".length()).          // setting up column sizes at the end of the sheet
                setAutoSizeColumn(1).
                setAutoSizeColumn(2);
             report=reportBuilder.build();
             /*try{
                 StringBuilder fileName=new StringBuilder("Call_Status_");
                 SimpleDateFormat sdfName=new SimpleDateFormat("dd_MMM_YYYY_HH_mm_ss");
                 fileName.append(sdfName.format(new Date()));
                 fileName.append("xlsx");
                 FileOutputStream fos=new FileOutputStream(fileName.toString());
                 fos.write(report);
             }
             catch (Exception ex){
                 logger.error("Exception occurred:{}",ex);
             }*/


        return report;
    }

    private Map<String, Map<String, List<CallStatusData>>> massageDataForReport(List<CallStatusData> data) {
        Map<String, Map<String, List<CallStatusData>>> dataMap=new HashMap<>();

        Map<String,List<CallStatusData>> stateWiseDataMap=new HashMap<>();
        for(CallStatusData callStatusData:data){
            if(StringUtils.isNotBlank(callStatusData.getState())){
                List<CallStatusData> stateWiseData=stateWiseDataMap.get(callStatusData.getState());
                if(null==stateWiseData){
                    stateWiseData=new ArrayList<>();
                }
                stateWiseData.add(callStatusData);
                stateWiseDataMap.put(callStatusData.getState(),stateWiseData);
            }
        }

        for(Map.Entry<String,List<CallStatusData>> enrty:stateWiseDataMap.entrySet()){
            List<CallStatusData> districtData= enrty.getValue();
            String stateName=enrty.getKey();
            Map<String,List<CallStatusData>> districtWiseDataMap=new HashMap<>();
            if(StringUtils.isNotBlank(stateName)){
                for(CallStatusData callStatusData:districtData){
                    if(StringUtils.isNotBlank(callStatusData.getDistrict())){
                        List<CallStatusData> districtWiseData=stateWiseDataMap.get(callStatusData.getDistrict());
                        if(null==districtWiseData){
                            districtWiseData=new ArrayList<>();
                        }
                        districtWiseData.add(callStatusData);
                        districtWiseDataMap.put(callStatusData.getDistrict(),districtWiseData);
                    }
                }
                dataMap.put(stateName,districtWiseDataMap);
            }
        }
        return dataMap;
    }
}
