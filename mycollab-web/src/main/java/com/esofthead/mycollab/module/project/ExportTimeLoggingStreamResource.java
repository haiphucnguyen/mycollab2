package com.esofthead.mycollab.module.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.file.ExportExcelStreamResource;
import com.esofthead.mycollab.module.file.FieldExportColumn;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class ExportTimeLoggingStreamResource extends
		ExportExcelStreamResource<ItemTimeLoggingSearchCriteria> {

	private static Logger log = LoggerFactory
			.getLogger(ExportTimeLoggingStreamResource.class);

	public ExportTimeLoggingStreamResource(String title,
			FieldExportColumn[] exportColumns,
			ISearchableService searchService,
			ItemTimeLoggingSearchCriteria searchCriteria) {
		super(title, exportColumns, searchService, searchCriteria);
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
				Sheet sheet = wb.createSheet("New Sheet");

				Row rowTitle = sheet.createRow((short) 1);

				createCellTitle(wb, rowTitle, (short) 1,
						CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM,
						title.toUpperCase());

				sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 8));

				Row rowHeader = sheet.createRow((short) 4);
				for (int i = 0; i < exportColumns.length; i++) {
					FieldExportColumn headerName = exportColumns[i];
					createCellHeader(wb, rowHeader, (short) i,
							CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM,
							headerName.getDisplayName());
				}

				int numPage = totalItems
						/ SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS + 1;

				int currentWritingRow = 5;

				for (int i = 0; i < numPage; i++) {
					List data = searchService
							.findPagableListByCriteria(new SearchRequest(
									searchCriteria, (i + 1),
									SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS));

					for (int j = 0; j < data.size(); j++) {
						Object rowObj = data.get(j);
						Row rowValue = sheet.createRow((short) j
								+ currentWritingRow);

						for (int k = 0; k < exportColumns.length; k++) {
							String visColumn = exportColumns[k].getName();
							Object value = null;
							int idRow = -1;
							try {
								value = PropertyUtils.getProperty(rowObj,
										visColumn);
								idRow = (Integer) PropertyUtils.getProperty(
										rowObj, "typeid");
							} catch (Exception e) {
								throw new MyCollabException(
										"Having error when exporting records to excel file.",
										e);
							}

							if (value == null) {
								createCell(wb, rowValue, (short) k,
										CellStyle.ALIGN_LEFT,
										CellStyle.VERTICAL_BOTTOM, "", true);
							} else {

								if (visColumn.equals("type")
										&& exportColumns[k].getDisplayName()
												.equals("Summary")
										&& idRow > -1) {
									try {
										value = (String) PropertyUtils
												.getProperty(rowObj, "summary");
									} catch (Exception e) {
										log.error(
												"Error while export time logging",
												e);
									}
								}

								createCell(wb, rowValue, (short) k,
										CellStyle.ALIGN_LEFT,
										CellStyle.VERTICAL_BOTTOM, value, true);
							}
						}

						if (j == data.size() - 1 && i < numPage) {
							currentWritingRow += data.size();
						}
					}
				}

				for (int i = 0; i < exportColumns.length; i++) {
					if (DEFAULT_COLUMN_WIDTH == exportColumns[i]
							.getColumnWidth()) {
						sheet.autoSizeColumn(i);
					} else {
						sheet.setColumnWidth(i,
								calculateColWidth(exportColumns[i]
										.getColumnWidth()));
					}
				}

				// Write the output to a file
				try {
					wb.write(outStream);
				} catch (IOException e) {
					e.printStackTrace();
					log.error("Error when write excel stream", e);
				} finally {
					try {
						outStream.close();
					} catch (IOException e) {
						log.error("Error when close excel stream", e);
						throw new MyCollabException(
								"Having error when exporting records to excel file.");
					}
				}

			}
		}).start();

		return inStream;
	}

	protected Cell createCell(Workbook wb, Row row, short column, short halign,
			short valign, Object value, boolean displayBorder) {
		Cell cell = row.createCell(column);
		CellStyle cellStyle = createBasicCellStyle(wb, halign, valign,
				displayBorder);
		if (value instanceof Date) {
			value = AppContext.formatDateTime((Date) value);
		}
		cellStyle.setWrapText(true);
		cell.setCellValue(value != null ? value.toString() : "");
		cell.setCellStyle(cellStyle);
		return cell;
	}

}
