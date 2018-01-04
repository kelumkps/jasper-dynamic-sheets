package com.blogspot.mytechnicaldocs.jasper;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.*;

public class JasperExample {

    public static void main(String[] args) throws Exception {
        JasperExample example = new JasperExample();
        example.generateReport();
    }

    private void generateReport() throws Exception {
        JasperReport report = JasperCompileManager.compileReport("src/main/resources/templates/base_template.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("templateDirectory", "src/main/resources/templates/");
        parameters.put("customParameters", new HashMap<>());
        JRDataSource ds = populateDataSource();

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);
        String outputFile = "My_Report_" + LocalDate.now().toString();

        xlsx(jasperPrint, outputFile);
        pdf(jasperPrint, outputFile);
        docx(jasperPrint, outputFile);
        pptx(jasperPrint, outputFile);
        csv(jasperPrint, outputFile);
    }

    private void pdf(JasperPrint jasperPrint, String output) throws Exception {
        long start = System.currentTimeMillis();
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        jasperPrintList.add(jasperPrint);
        FileOutputStream oStream = new FileOutputStream(output + ".pdf");
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(oStream));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCreatingBatchModeBookmarks(true);
        /* Enable Encryption */
        /*configuration.setEncrypted(true);
        configuration.set128BitKey(true);
        configuration.setUserPassword("jasper");
        configuration.setOwnerPassword("reports");
        configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);*/
        /* Enable Encryption */
        exporter.setConfiguration(configuration);

        exporter.exportReport();
        oStream.close();
        System.out.println("PDF creation time : " + (System.currentTimeMillis() - start));
    }

    private void xlsx(JasperPrint jasperPrint, String output) throws Exception {
        long start = System.currentTimeMillis();
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        jasperPrintList.add(jasperPrint);
        FileOutputStream oStream = new FileOutputStream(output + ".xlsx");
        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(oStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        oStream.close();
        System.out.println("XLSX creation time : " + (System.currentTimeMillis() - start));
    }

    private void docx(JasperPrint jasperPrint, String output) throws Exception {
        long start = System.currentTimeMillis();
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        jasperPrintList.add(jasperPrint);
        FileOutputStream oStream = new FileOutputStream(output + ".docx");
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(oStream));
        exporter.exportReport();
        System.out.println("DOCX creation time : " + (System.currentTimeMillis() - start));
    }

    private void pptx(JasperPrint jasperPrint, String output) throws Exception {
        long start = System.currentTimeMillis();
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        jasperPrintList.add(jasperPrint);
        FileOutputStream oStream = new FileOutputStream(output + ".pptx");
        JRPptxExporter exporter = new JRPptxExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(oStream));
        exporter.exportReport();
        System.out.println("PPTX creation time : " + (System.currentTimeMillis() - start));
    }

    private void csv(JasperPrint jasperPrint, String output) throws Exception {
        long start = System.currentTimeMillis();
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        jasperPrintList.add(jasperPrint);
        FileOutputStream oStream = new FileOutputStream(output + ".csv");
        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(oStream));
        exporter.exportReport();
        System.out.println("CSV creation time : " + (System.currentTimeMillis() - start));
    }

    private JRDataSource populateDataSource() {
        List<Map<String, Object>> data = new ArrayList<>();
        data.add(createDummySheetData(1));
        data.add(createDummySheetData(2));
        data.add(createDummySheetData(3));
        return new JRMapArrayDataSource(data.toArray());
    }

    private List<Map<String, Object>> createDummyRecords(int sheetNumber) {
        List<Map<String, Object>> data = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(10) + 5;
        for (int i = 0; i < size; i++) {
            Map<String, Object> record = new HashMap<>();
            record.put("USER_NAME", "My User Name " + sheetNumber + "_" + random.nextInt(10));
            record.put("FIRST_NAME", "First Name " + sheetNumber + "_" + random.nextInt(10));
            record.put("LAST_NAME", "Last Name " + sheetNumber + "_" + random.nextInt(10));
            record.put("EMAIL", "My Email " + sheetNumber + "_" + random.nextInt(10));
            record.put("ADDRESS", "My Address " + sheetNumber + "_" + random.nextInt(10));
            data.add(record);
        }
        return data;
    }

    private Map<String, Object> createDummySheetData(int sheetNumber) {
        List<Map<String, Object>> sheetData = createDummyRecords(sheetNumber);
        Map<String, Object> sheet = new HashMap<>();
        sheet.put("SHEET_NAME", "My Sheet 0" + sheetNumber);
        sheet.put("DATA_SOURCE", sheetData);
        return sheet;
    }
}