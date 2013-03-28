/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.StreamResource;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class ExportStreamResource implements
		StreamResource.StreamSource {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(ExportStreamResource.class);
	protected String[] visibleColumns;
	protected String[] headerNames;

	public ExportStreamResource(String[] visibleColumns, String[] headerNames) {
		this.visibleColumns = visibleColumns;
		this.headerNames = headerNames;
	}

	public static class ExcelOutput<S extends SearchCriteria> extends
			ExportStreamResource {
		private static final long serialVersionUID = 1L;
		private ISearchableService<S> searchService;
		private S searchCriteria;

		public ExcelOutput(String[] visibleColumns, String[] headerNames,
				ISearchableService searchService, S searchCriteria) {
			super(visibleColumns, headerNames);
			this.searchCriteria = searchCriteria;
			this.searchService = searchService;
		}

		@Override
		public InputStream getStream() {

			final int totalItems = searchService.getTotalCount(searchCriteria);
			
			final PipedInputStream inStream = new PipedInputStream();
			final PipedOutputStream outStream;

			try {
				outStream = new PipedOutputStream(inStream);
			} catch (IOException ex) {
				log.error("Can not create outstream file", ex);
				return null;
			}

			new Thread(new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				@Override
				public void run() {

					Workbook wb = new HSSFWorkbook();
					Sheet sheet = wb.createSheet("Bug Sheet");

					Row rowTitle = sheet.createRow((short) 1);
					
					String title = "Bugs of Project "
							+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
									.getProject().getName() != null) ? CurrentProjectVariables.getProject()
											.getName() : "");
					createCellTitle(wb, rowTitle, (short) 1,
							CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM,
							title.toUpperCase());

					sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));

					Row rowHeader = sheet.createRow((short) 4);
					for (int i = 0; i < headerNames.length; i++) {
						String headerName = headerNames[i];
						createCellHeader(wb, rowHeader, (short) i,
								CellStyle.ALIGN_CENTER,
								CellStyle.VERTICAL_BOTTOM, headerName);
						sheet.autoSizeColumn(i);
					}

					int numPage = totalItems
							/ SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS + 1;

					int currentWritingRow = 5;

					for (int i = 0; i < numPage; i++) {
						
						List data = searchService
								.findPagableListByCriteria(new SearchRequest<S>(
										searchCriteria,
										i,
										SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS));

						for (int j = 0; j < data.size(); j++) {
							Object rowObj = data.get(j);

							Row rowValue = sheet.createRow((short) j
									+ currentWritingRow);
							
							try {
								int line = (int) ((String) PropertyUtils.getProperty(rowObj,
										"summary")).length() / 35;
								
								if (line > 1) {
									rowValue.setHeightInPoints((line*sheet.getDefaultRowHeightInPoints())); 
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							for (int k = 0; k < visibleColumns.length; k++) {
								String visColumn = visibleColumns[k];
								Object value = null;
								try {
									value = PropertyUtils.getProperty(rowObj,
											visColumn);
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									e.printStackTrace();
								}
								if (value == null) {
									createCell(wb, rowValue, (short) k,
											CellStyle.ALIGN_LEFT,
											CellStyle.VERTICAL_BOTTOM, "");
								} else { 
									if (visColumn.equals("milestoneid")) {
										
										SimpleBug simple = (SimpleBug) rowObj;
										createCell(wb, rowValue, (short) k,
												CellStyle.ALIGN_LEFT,
												CellStyle.VERTICAL_BOTTOM, simple.getMilestoneName());
									} else {
										createCell(wb, rowValue, (short) k,
												CellStyle.ALIGN_LEFT,
												CellStyle.VERTICAL_BOTTOM, value);
									}
								}
							}
							
							if (j == data.size() - 1 && i < numPage) {
								currentWritingRow += data.size();
							}
						}
					}

					for (int i = 0; i < headerNames.length; i++) {
						if (i == 1) {
							sheet.setColumnWidth(i, calculateColWidth(40));
						} else {
							sheet.autoSizeColumn(i);
						}
					}

					// Write the output to a file
					try {
						wb.write(outStream);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							outStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}).start();

			return inStream;
		}
		
		 private int calculateColWidth(int width){ 
             if(width > 254) 
                     return 65280; // Maximum allowed column width. 
             if(width > 1){ 
                     int floor = (int)(Math.floor(((double)width)/5)); 
                     int factor = (30*floor); 
                     int value = 450 + factor + ((width-1) * 250); 
                     return value;	
             } 
             else 
                     return 450; // default to column size 1 if zero, one or negative number is passed. 
     } 

		private CellStyle createBasicCellStyle(Workbook wb, short halign,
				short valign, boolean useBorder) {
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(halign);
			cellStyle.setVerticalAlignment(valign);
			if (useBorder) {
				cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				cellStyle.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
				cellStyle.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			}
			return cellStyle;
		}

		private void createCellTitle(Workbook wb, Row row, short column,
				short halign, short valign, Object value) {
			Cell cell = row.createCell(column);
			CellStyle cellStyle = createBasicCellStyle(wb, halign, valign,
					false);
			cell.setCellValue(value.toString());
			cell.setCellStyle(cellStyle);
		}

		private void createCellHeader(Workbook wb, Row row, short column,
				short halign, short valign, Object value) {
			Cell cell = row.createCell(column);
			cell.setCellValue(value.toString());
			CellStyle cellStyle = createBasicCellStyle(wb, halign, valign, true);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

			HSSFFont font = (HSSFFont) wb.createFont();
			font.setColor(HSSFColor.WHITE.index);
			cellStyle.setFont(font);

			cell.setCellStyle(cellStyle);
		}

		private Cell createCell(Workbook wb, Row row, short column,
				short halign, short valign, Object value) {
			Cell cell = row.createCell(column);
			CellStyle cellStyle = createBasicCellStyle(wb, halign, valign, true);
			if (value instanceof Date) {
				value = AppContext.formatDate((Date) value);
			}
			cellStyle.setWrapText(true);
			cell.setCellValue(value.toString());
			cell.setCellStyle(cellStyle);
			return cell;
		}
	}

	public static class AllItems<S extends SearchCriteria> extends
			ExportStreamResource {
		private static final long serialVersionUID = 1L;
		private ISearchableService<S> searchService;
		private S searchCriteria;

		public AllItems(String[] visibleColumns, String[] headerNames,
				ISearchableService searchService, S searchCriteria) {
			super(visibleColumns, headerNames);
			this.searchCriteria = searchCriteria;
			this.searchService = searchService;
		}

		@Override
		public InputStream getStream() {

			final int totalItems = searchService.getTotalCount(searchCriteria);

			final PipedInputStream inStream = new PipedInputStream();
			final PipedOutputStream outStream;

			try {
				outStream = new PipedOutputStream(inStream);
			} catch (IOException ex) {
				log.error("Can not create outstream file", ex);
				return null;
			}

			new Thread(new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				@Override
				public void run() {
					OutputStreamWriter writer = null;
					try {
						writer = new OutputStreamWriter(outStream);

						// print header
						writer.write("###############\n\r");

						for (int i = 0; i < headerNames.length; i++) {
							String headerName = headerNames[i];
							writer.write(headerName);
							if (i < headerNames.length - 1) {
								writer.write(",");
							}
						}
						writer.write("\r\n");
						writer.write("###############\n\r");

						int numPage = totalItems
								/ SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS + 1;

						for (int i = 0; i < numPage; i++) {

							List data = searchService
									.findPagableListByCriteria(new SearchRequest<S>(
											searchCriteria,
											i,
											SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS));

							for (int j = 0; j < data.size(); j++) {
								Object rowObj = data.get(j);

								for (int k = 0; k < visibleColumns.length; k++) {
									String visColumn = visibleColumns[k];
									Object value = PropertyUtils.getProperty(
											rowObj, visColumn);
									if (value == null) {
										writer.write("");
									} else {
										writer.write(value.toString());
									}
									if (k < visibleColumns.length - 1) {
										writer.write(",");
									}

								}
								writer.write("\r\n");
							}

						}

					} catch (Exception e) {
						log.error("Failed to send GZIP XML data to the stream",
								e);
					} finally {
						if (writer != null) {
							try {
								writer.close();
							} catch (IOException ex) {
								log.error("Error while try to close resource",
										ex);
							}
						}
					}
				}
			}).start();

			return inStream;
		}
	}

	public static class ListData extends ExportStreamResource {
		private static final long serialVersionUID = 1L;
		private List data;
		private static final String selectedFieldName = "selected";

		public ListData(String[] visibleColumns, String[] headerNames, List data) {
			super(visibleColumns, headerNames);
			this.data = data;
		}

		@Override
		public InputStream getStream() {
			final PipedInputStream inStream = new PipedInputStream();
			final PipedOutputStream outStream;

			try {
				outStream = new PipedOutputStream(inStream);
			} catch (IOException ex) {
				log.error("Can not create outstream file", ex);
				return null;
			}

			new Thread(new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				@Override
				public void run() {
					OutputStreamWriter writer = null;
					try {
						writer = new OutputStreamWriter(outStream);

						// print header
						writer.write("###############\n\r");

						for (int i = 0; i < headerNames.length; i++) {
							String headerName = headerNames[i];
							writer.write(headerName);
							if (i < headerNames.length - 1) {
								writer.write(",");
							}
						}
						writer.write("\r\n");
						writer.write("###############\n\r");

						for (int j = 0; j < data.size(); j++) {
							Object rowObj = data.get(j);

							boolean iselected = (Boolean) PropertyUtils
									.getProperty(rowObj, selectedFieldName);
							if (iselected) {
								for (int k = 0; k < visibleColumns.length; k++) {
									String visColumn = visibleColumns[k];
									Object value = PropertyUtils.getProperty(
											rowObj, visColumn);
									if (value == null) {
										writer.write("");
									} else {
										writer.write(value.toString());
									}

									if (k < visibleColumns.length - 1) {
										writer.write(",");
									}

								}
								writer.write("\r\n");
							}
						}

					} catch (Exception e) {
						log.error("Failed to send GZIP XML data to the stream",
								e);
					} finally {
						if (writer != null) {
							try {
								writer.close();
							} catch (IOException ex) {
								log.error("Error while try to close resource",
										ex);
							}
						}
					}
				}
			}).start();

			return inStream;
		}
	}
}
