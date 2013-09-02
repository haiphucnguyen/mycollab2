package com.esofthead.mycollab.module.file.resource;

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
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.MyCollabThread;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("serial")
public class ExportTaskExcelStreamResource extends
		ExportExcelStreamResource<TaskListSearchCriteria> {

	private static Logger log = LoggerFactory
			.getLogger(ExportTaskExcelStreamResource.class);

	public ExportTaskExcelStreamResource(String title,
			FieldExportColumn[] exportColumns,
			ISearchableService searchService,
			TaskListSearchCriteria searchCriteria) {
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
		
		Thread threadExport = new MyCollabThread(new Runnable() {
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

				int numPage = totalItems
						/ SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS + 1;

				int currentWritingRow = 3;

				for (int i = 0; i < numPage; i++) {
					List data = searchService
							.findPagableListByCriteria(new SearchRequest<TaskListSearchCriteria>(
									searchCriteria, (i + 1),
									SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS));
					int numWritingRow = 0;
					for (int j = 0; j < data.size(); j++) {
						int rowWriting = j + currentWritingRow + numWritingRow;
						SimpleTaskList tasklist = (SimpleTaskList) data.get(j);
						createTaskGroupInfo(wb, sheet, tasklist, rowWriting);

						ProjectTaskService taskService = AppContext
								.getSpringBean(ProjectTaskService.class);
						TaskSearchCriteria criteria = new TaskSearchCriteria();
						criteria.setProjectid(searchCriteria.getProjectId());
						criteria.setTaskListId(new NumberSearchField(tasklist
								.getId()));

						List tasks = taskService
								.findPagableListByCriteria(new SearchRequest<TaskSearchCriteria>(
										criteria, 0, Integer.MAX_VALUE));
						
						Row rowTaskHeader = sheet.createRow((short) (rowWriting + 6));
					
						for (int l = 0; l < exportColumns.length; l++) {
							FieldExportColumn headerName = exportColumns[l];
							createCellHeader(wb, rowTaskHeader, (short) (l + 2),
									CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM,
									headerName.getDisplayName());
						}
						
						// create tast table
						for (int k = 0; k < tasks.size(); k++) {
							Object rowObj = tasks.get(k);
							Row rowValue = sheet.createRow((short) (rowWriting + 7 + k));

							for (int m = 0; m < exportColumns.length; m++) {
								String visColumn = exportColumns[m].getName();
								Object value = null;
								try {
									value = PropertyUtils.getProperty(rowObj,
											visColumn);
								} catch (Exception e) {
									log.error("Error when geting values for excel stream", e);
									throw new MyCollabException("Having error when exporting records to excel file.");
								}
								
								if (value == null) {
									createCell(wb, rowValue, (short) (m + 2),
											CellStyle.ALIGN_LEFT,
											CellStyle.VERTICAL_BOTTOM, "", true);
								} else {
									createCell(wb, rowValue, (short) (m + 2),
											CellStyle.ALIGN_LEFT,
											CellStyle.VERTICAL_BOTTOM, value, true);
								}
							}

						}
						
						numWritingRow += (8 + tasks.size());

						if (j == data.size() - 1 && i < numPage) {
							currentWritingRow += (data.size() + numWritingRow);
						}
					}
				}

				//Set auto size for all columns
				for (int i = 0; i < 10; i++) {
					sheet.autoSizeColumn(i);
				}

				sheet.setColumnWidth((short) 3, calculateColWidth(40));

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
		});

		threadExport.start();

		return inStream;
	}

	private void createTaskGroupInfo(Workbook wb, Sheet sheet,
			SimpleTaskList tasklist, int rowWriting) {
		Row rowTitleTasklist = sheet.createRow((short) rowWriting);
		createCellHeader(wb, rowTitleTasklist, (short) 1, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, tasklist.getName());
		sheet.addMergedRegion(new CellRangeAddress((short) rowWriting,
				(short) rowWriting, 1, 8));

		Row rowDescription = sheet.createRow((short) ((short) rowWriting + 2));
		if (tasklist.getDescription() != null) {
			int lineBreak = tasklist.getDescription().length()/60;
			if (lineBreak > 1) {
				rowDescription.setHeightInPoints((lineBreak*sheet.getDefaultRowHeightInPoints()));
			}
		}
		
		createCellInstruction(wb, rowDescription, (short) 1, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, "Description: ", false);
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 2),
				(short) (rowWriting + 2), 1, 2));
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 2),
				(short) (rowWriting + 2), 3, 5));
		createCell(wb, rowDescription, (short) 3, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, tasklist.getDescription(), false);

		Row rowReponsible = sheet.createRow((short) ((short) rowWriting + 3));
		createCellInstruction(wb, rowReponsible, (short) 1, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, "Reponsible User: ", false);
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 3),
				(short) (rowWriting + 3), 1, 2));
		createCell(wb, rowReponsible, (short) 3, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, tasklist.getOwnerFullName(), false);
		
		createCellInstruction(wb, rowReponsible, (short) 4, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, "Phase: ", false);
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 3),
				(short) (rowWriting + 3), 4, 5));
		createCell(wb, rowReponsible, (short) 6, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, tasklist.getMilestoneName(), false);
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 3),
				(short) (rowWriting + 3), 6, 9));

		Row rowProgress = sheet.createRow((short) ((short) rowWriting + 4));
		createCellInstruction(wb, rowProgress, (short) 1, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, "Progress: ", false);
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 4),
				(short) (rowWriting + 4), 1, 2));
		createCell(wb, rowProgress, (short) 3, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, Math.ceil(tasklist.getPercentageComplete()) + "%",
				false);

		createCellInstruction(wb, rowProgress, (short) 4, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, "Number of Open Tasks: ", false);
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 4),
				(short) (rowWriting + 4), 4, 5));
		createCell(wb, rowProgress, (short) 6, CellStyle.ALIGN_LEFT,
				CellStyle.VERTICAL_BOTTOM, tasklist.getNumOpenTasks() + "/" + tasklist.getNumAllTasks(), false);
		sheet.addMergedRegion(new CellRangeAddress((short) (rowWriting + 4),
				(short) (rowWriting + 4), 6, 9));

	}
	
	private Cell createCellInstruction(Workbook wb, Row row, short column, short halign,
			short valign, Object value, boolean displayBorder) {
		Cell cell = row.createCell(column);
		CellStyle cellStyle = createBasicCellStyle(wb, halign, valign, displayBorder);
		if (value instanceof Date) {
			value = AppContext.formatDate((Date) value);
		}
		cellStyle.setWrapText(true);
		cell.setCellValue(value != null ? value.toString() : "");
		
		Font f = wb.createFont();
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(f);
		cell.setCellStyle(cellStyle);
		return cell;
	}
}
