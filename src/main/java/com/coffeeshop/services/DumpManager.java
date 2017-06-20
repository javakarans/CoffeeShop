//package com.coffeeshop.services;
//
//
//import java.io.File;
//import java.io.FileOutputStream;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.util.List;
//import java.util.ArrayList;
//import org.apache.poi.hssf.model.*;
//import java.io.FileInputStream;
//import org.apache.poi.ss.usermodel.*;
//import java.io.InputStream;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//
//public class DumpManager {
//
//    /**
//     * return all epsProcedures in excel file
//     *
//     * @param epsProcedures
//     * @return
//     */
//    public InputStream getEpsProcedureExcelFile(ArrayList<EpsProcedure> epsProcedures) {
//        String fileLocation = AppConfig.EXCEL_OUTPUT_LOCATION + "/epsprocedure.xlsx";
//        InputStream stream = null;
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        CellType ct = CellType.NUMERIC;
//        Cell cell;
//        XSSFSheet sheet = workbook.careateSheet("روند EPS");
//        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
//        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
//        sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 11));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 12, 20));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 21, 29));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 30, 38));
//        Row header = sheet.createRow(0);
//        header.createCell(0).setCellValue("نماد");
//        header.createCell(1).setCellValue("سال");
//        header.createCell(2).setCellValue("پیش بینی اولیه");
//        header.createCell(3).setCellValue("سه ماه");
//        header.createCell(12).setCellValue("شش ماه");
//        header.createCell(21).setCellValue("نه ماه");
//        header.createCell(30).setCellValue("سالانه");
//        Row header1 = sheet.createRow(1);
//        for (int i = 2; i < 38;) {
//            header1.createCell(++i).setCellValue("Eps(واقعی");
//            header1.createCell(++i).setCellValue("قیمت قبل(واقعی)");
//            header1.createCell(++i).setCellValue("قیمت بعد(واقعی)");
//            header1.createCell(++i).setCellValue("درصد تغییر(واقعی)");
//            header1.createCell(++i).setCellValue("Eps (پیش بینی)");
//            header1.createCell(++i).setCellValue("قیمت قبل(پیش بینی)");
//            header1.createCell(++i).setCellValue("قیمت بعد(پیش بینی)");
//            header1.createCell(++i).setCellValue("درصد تغییر(پیش بینی)");
//            header1.createCell(++i).setCellValue("پوشش");
//        }
//
//        for (int i = 0; i < epsProcedures.size(); i++) {
//            EpsProcedure ep = epsProcedures.get(i);
//            Row row = sheet.createRow(i + 2);
//            row.createCell(0).setCellValue(String.valueOf(ep.getCompany().getSymbol()));
//            row.createCell(1).setCellValue(String.valueOf(ep.getYear()));
//
//            cell = row.createCell(2);
//
//            if (ep.getForecastedEPS() != null && ep.getForecastedEPS().get("0") != -1000000) {
//                cell.setCellType(ct);
//                cell.setCellValue(ep.getForecastedEPS().get("0"));
//            }
//
//            int k = 2;
//            for (int j = 3; j <= 12; j = j + 3) {
//
//                String period = String.valueOf(j);
//
//                cell = row.createCell(++k);
//                if (ep.getRealEPS() != null && ep.getRealEPS().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getRealEPS().get(period));
//                }
//
//                cell = row.createCell(++k);
//                if (ep.getRealBeforePrice() != null && ep.getRealBeforePrice().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getRealBeforePrice().get(period));
//                }
//
//                cell = row.createCell(++k);
//                if (ep.getRealAfterPrice() != null && ep.getRealAfterPrice().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getRealAfterPrice().get(period));
//                }
//
//                cell = row.createCell(++k);
//                if (ep.getRealPercentageChanges() != null && ep.getRealPercentageChanges().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getRealPercentageChanges().get(period));
//                }
//                cell = row.createCell(++k);
//                if (ep.getForecastedEPS() != null && ep.getForecastedEPS().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getForecastedEPS().get(period));
//                }
//
//                cell = row.createCell(++k);
//                if (ep.getForecastedBeforPrice() != null && ep.getForecastedBeforPrice().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getForecastedBeforPrice().get(period));
//                }
//
//                cell = row.createCell(++k);
//                if (ep.getForecastedAfterPrice() != null && ep.getForecastedAfterPrice().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getForecastedAfterPrice().get(period));
//                }
//
//                cell = row.createCell(++k);
//                if (ep.getForecastedPercentageChanges() != null && ep.getForecastedPercentageChanges().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getForecastedPercentageChanges().get(period));
//                }
//
//                cell = row.createCell(++k);
//                if (ep.getCovragePercent() != null && ep.getCovragePercent().get(period) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ep.getCovragePercent().get(period));
//                }
//
//            }
//        }
//        sheet.createFreezePane(3, 2, 0, 0);
//        sheet.getCTWorksheet().getSheetViews().getSheetViewArray(0).setRightToLeft(true);
//
//        try {
//            //Write the workbook in file system
//            FileOutputStream out = new FileOutputStream(new File(fileLocation));
//            workbook.write(out);
//            File excelFile = new File(fileLocation);
//            stream = new FileInputStream(excelFile);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stream;
//    }
//
//    /**
//     * return all convention in excel file
//     *
//     * @param conventions
//     * @return
//     */
//    public InputStream getConventionExcelFile(List<Convention> conventions) {
//
//        String fileLocation = AppConfig.EXCEL_OUTPUT_LOCATION+"/conventoinresult.xlsx";
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        InputStream stream = null;
//        //Create a blank sheet
//        XSSFSheet sheet = workbook.createSheet("مجمع");
//        //This data needs to be written (Object[])
//        String[] header = {"نماد", "شرکت", "Dps", "بازده", "روز مجمع", "سال مالی", "روز پرداخت سهم"};
//        Row row = sheet.createRow(0);
//        //
//        for (int i = 0; i < header.length; i++) {
//            row.createCell(i).setCellValue(header[i]);
//        }
//        CellType number = CellType.NUMERIC;
//
//        //insert covention to table
//        for (int i = 0; i < conventions.size(); i++) {
//            int columnNumber = -1;
//            Row r = sheet.createRow(i + 1);
//            Convention c = conventions.get(i);
//
//            Cell cell;
//            cell = r.createCell(++columnNumber);
//            cell.setCellValue(c.getCompany().getSymbol());
//
//            cell = r.createCell(++columnNumber);
//            cell.setCellValue(c.getCompany().getCompanyName());
//
//            cell = r.createCell(++columnNumber);
//            cell.setCellValue(c.getDPS());
//            cell.setCellType(number);
//
//            cell = r.createCell(++columnNumber);
//            cell.setCellValue(c.getConventionEfficiency());
//            cell.setCellType(number);
//
//            cell = r.createCell(++columnNumber);
//            cell.setCellValue(c.getConventionDate());
//
//            cell = r.createCell(++columnNumber);
//            cell.setCellValue(c.getYearEndToDate());
//
//            cell = r.createCell(++columnNumber);
//            cell.setCellValue(c.getDPSPaidDate());
//        }
//        //freez the header
//        sheet.createFreezePane(0, 1, 0, 0);
//        sheet.getCTWorksheet().getSheetViews().getSheetViewArray(0).setRightToLeft(true);
//
//        try {
//            //Write the workbook in file system
//            FileOutputStream out = new FileOutputStream(new File(fileLocation));
//            workbook.write(out);
//            File excelFile = new File(fileLocation);
//            stream = new FileInputStream(excelFile);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stream;
//    }
//
//    /**
//     * return all productionsSales in excel file
//     * @param productionsSaleses
//     * @return
//     */
//    public InputStream getProductionsAndSales(ArrayList<ProductionsSales> productionsSaleses) {
//        String fileLocation = AppConfig.EXCEL_OUTPUT_LOCATION + "/productionandsales.xlsx";
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        Cell cell;
//        XSSFSheet sheet = workbook.createSheet("صورت تولید و فروش");
//        for (int i = 0; i < 4; i++) {
//            sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
//        }
//        for (int i = 0, j = 4; i < 12; i++, j = j + 7) {
//            sheet.addMergedRegion(new CellRangeAddress(0, 0, j, j + 6));
//        }
//        String[] monthHeader = {"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "ابان", "آذر", "دی", "بهمن", "اسفند"};
//        Row friestHeader = sheet.createRow(0);
//        friestHeader.createCell(0).setCellValue("نماد");
//        friestHeader.createCell(1).setCellValue("سال");
//        friestHeader.createCell(3).setCellValue("اولین پیش بینی");
//        friestHeader.createCell(2).setCellValue("");
//        for (int i = 0, j = 4; i < 12; i++, j = j + 7) {
//            friestHeader.createCell(j).setCellValue(monthHeader[i]);
//        }
//        Row secondHeader = sheet.createRow(1);
//        //in this block set header of column
//        String[] details = {"فروش ماهیانه", "فروش تجمعی", "هدف", "درصد پوشش", "قیمت قبلی", "قیمت بعدی", "درصد تغییرات"};
//        for (int i = 0, j = 3; i < 12; i++) {
//            for (int k = 0; k < 7; k++) {
//                secondHeader.createCell(++j).setCellValue(details[k]);
//            }
//        }
//        int m = 1;
//        //in this block insert product to table
//        for (int i = 0; i < productionsSaleses.size(); i++) {
//            ProductionsSales ps = productionsSaleses.get(i);
//            Row row = sheet.createRow(++m);
//            Row row2 = sheet.createRow(++m);
//            sheet.addMergedRegion(new CellRangeAddress(m - 1, m, 0, 0));
//            sheet.addMergedRegion(new CellRangeAddress(m - 1, m, 1, 1));
//            row.createCell(0).setCellValue(ps.getCompany().getSymbol());
//            row.createCell(1).setCellValue(String.valueOf(ps.getYear()));
//            row.createCell(2).setCellValue("مقدار");
//            row2.createCell(2).setCellValue("مبلغ");
//            cell = row.createCell(3);
//            cell.setCellType(ct);
//            if (ps.getPrimarayForcastedAmount() != -1000000) {
//                cell.setCellValue(ps.getPrimarayForcastedAmount());
//            }
//            cell = row2.createCell(3);
//            cell.setCellType(ct);
//            if (ps.getPrimarayForcastedPrice() != -1000000) {
//                cell.setCellValue(ps.getPrimarayForcastedPrice());
//            }
//
//            int c = 3;
//            //insert productionsalest to table
//            for (int j = 1; j < 13; j++) {
//                String month = String.valueOf(j);
//
//                cell = row.createCell(++c);
//                if (ps.getMonthlySalesAmount() != null && ps.getMonthlySalesAmount().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getMonthlySalesAmount().get(month));
//
//                }
//                cell = row2.createCell(c);
//                if (ps.getMonthlySalesPrice() != null && ps.getMonthlySalesPrice().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getMonthlySalesPrice().get(month));
//
//                }
//
//                cell = row.createCell(++c);
//                if (ps.getAggregationSalesAmount() != null && ps.getAggregationSalesAmount().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getAggregationSalesAmount().get(month));
//                }
//                cell = row2.createCell(c);
//                if (ps.getAggregationSalesPrice() != null && ps.getAggregationSalesPrice().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getAggregationSalesPrice().get(month));
//                }
//                cell = row.createCell(++c);
//                if (ps.getPerpouseAmount() != null && ps.getPerpouseAmount().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getPerpouseAmount().get(month));
//                }
//                cell = row2.createCell(c);
//                if (ps.getPerpousePrice() != null && ps.getPerpousePrice().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getPerpousePrice().get(month));
//
//                }
//                cell = row.createCell(++c);
//                if (ps.getCoveragePercentAmount() != null && ps.getCoveragePercentAmount().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getCoveragePercentAmount().get(month));
//
//                }
//                cell = row2.createCell(c);
//                if (ps.getCoveragePercentPrice() != null && ps.getCoveragePercentPrice().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getCoveragePercentPrice().get(month));
//
//                }
//
//                sheet.addMergedRegion(new CellRangeAddress(m - 1, m, c + 1, c + 1));
//                cell = row.createCell(++c);
//                if (ps.getBeforePrice() != null && ps.getBeforePrice().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getBeforePrice().get(month));
//
//                }
//                sheet.addMergedRegion(new CellRangeAddress(m - 1, m, c + 1, c + 1));
//                cell = row.createCell(++c);
//                if (ps.getAfterPrice() != null && ps.getAfterPrice().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getAfterPrice().get(month));
//
//                }
//
//                sheet.addMergedRegion(new CellRangeAddress(m - 1, m, c + 1, c + 1));
//                cell = row.createCell(++c);
//                if (ps.getPercentageChanges() != null && ps.getPercentageChanges().get(month) != -1000000) {
//                    cell.setCellType(ct);
//                    cell.setCellValue(ps.getPercentageChanges().get(month));
//
//                }
//            }
//        }
//        //freez the the header
//        sheet.createFreezePane(3, 2, 0, 0);
//        sheet.getCTWorksheet().getSheetViews().getSheetViewArray(0).setRightToLeft(true);
//
//        try {
//            //Write the workbook in file system
//            FileOutputStream out = new FileOutputStream(new File(fileLocation));
//            workbook.write(out);
//            File excelFile = new File(fileLocation);
//            stream = new FileInputStream(excelFile);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stream;
//    }
//}
