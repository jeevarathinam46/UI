package com.ll.iod.testCases;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class SlowQueryLogParser {
    public static void main(String[] args) {
        String logFilePath = "D:\\Users\\anandhan.s\\Downloads\\mysql-slowquery.log.2024-10-21.13"; // Change this to your log file path
        String outputCsvPath = "D:\\Users\\anandhan.s\\Downloads\\slow_queries.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath));
             FileWriter csvWriter = new FileWriter(outputCsvPath)) {
            // Write CSV header
            csvWriter.append("Query Time,Query\n");
            String line,qt = null;
            String query = null;
            String queryTime = null;
            int i=0,t=0,j=0;
            while ((line = br.readLine()) != null) {
                // Check for query time line
                if (line.startsWith("# Query_time:")) {
                    queryTime = line.split(":")[1].trim();
                     qt = StringUtils.substring(queryTime, 0, 6);
                     float f=Float.parseFloat(qt);
                    j=(int)f;
                   }
                // Check for query line

                if (line.startsWith("SET timestamp=")) {
                t=i+1;
                System.out.println("t = "+t);
                }

               // if (line.startsWith("SELECT") || line.startsWith("INSERT") || line.startsWith("UPDATE") || line.startsWith("DELETE")) {

                   if(i==t){
                       query = line;
                    if (j>10) {
                        // Write to CSV
                        csvWriter.append(String.valueOf(j)).append(",").append(query).append("\n");
                        queryTime = null; // Reset after writing
                    }
                    t=0;
                }
                i++;
            }
            System.out.println("Slow query log parsing complete. Output written to " + outputCsvPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}