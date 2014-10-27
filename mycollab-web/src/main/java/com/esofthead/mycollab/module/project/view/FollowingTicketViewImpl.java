/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.esofthead.mycollab.common.i18n.FollowerI18nEnum;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.FollowingTicket;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.FollowingTicketSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectFollowingTicketService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.reporting.ExportItemsStreamResource;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.reporting.RpParameterBuilder;
import com.esofthead.mycollab.reporting.SimpleGridExportItemsStreamResource;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.resources.LazyStreamSource;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.WebResourceIds;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class FollowingTicketViewImpl extends AbstractPageView implements
		FollowingTicketView {
	private static final long serialVersionUID = 1L;

	private SplitButton exportButtonControl;
	private final FollowingTicketTableDisplay ticketTable;
	private FollowingTicketSearchCriteria searchCriteria;

	private UserInvolvedProjectsListSelect projectField;
	private TextField summaryField;

	private List<SimpleProject> projects;

	public FollowingTicketViewImpl() {
		projects = ApplicationContextUtil.getSpringBean(ProjectService.class)
				.getProjectsUserInvolved(AppContext.getUsername(),
						AppContext.getAccountId());
		this.setWidth("100%");

		final VerticalLayout headerWrapper = new VerticalLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.setStyleName("projectfeed-hdr-wrapper");

		HorizontalLayout controlBtns = new HorizontalLayout();

		final HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.setSpacing(true);

		final Image timeIcon = new Image(null,
				MyCollabResource.newResource("icons/24/follow.png"));
		header.addComponent(timeIcon);

		final Label layoutHeader = new Label("My Following Tickets");
		layoutHeader.addStyleName("h2");
		header.addComponent(layoutHeader);
		header.setComponentAlignment(layoutHeader, Alignment.MIDDLE_LEFT);
		header.setExpandRatio(layoutHeader, 1.0f);

		final VerticalLayout contentWrapper = new VerticalLayout();

		contentWrapper.setWidth("100%");
		contentWrapper.addStyleName("content-wrapper");

		headerWrapper.addComponent(header);
		this.addComponent(headerWrapper);
		contentWrapper.addComponent(controlBtns);
		this.addComponent(contentWrapper);

		final Button backBtn = new Button(
				AppContext
						.getMessage(FollowerI18nEnum.BUTTON_BACK_TO_WORKBOARD));
		backBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				EventBusFactory.getInstance().post(
						new ShellEvent.GotoProjectModule(
								FollowingTicketViewImpl.this, null));

			}
		});

		backBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		backBtn.setIcon(MyCollabResource.newResource(WebResourceIds._16_back));

		controlBtns.setMargin(new MarginInfo(true, false, true, false));
		controlBtns.setWidth("100%");
		controlBtns.addComponent(backBtn);
		controlBtns.setExpandRatio(backBtn, 1.0f);

		Button exportBtn = new Button("Export", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				exportButtonControl.setPopupVisible(true);

			}
		});
		exportButtonControl = new SplitButton(exportBtn);
		exportButtonControl.setWidthUndefined();
		exportButtonControl.addStyleName(UIConstants.THEME_GRAY_LINK);
		exportButtonControl.setIcon(MyCollabResource
				.newResource("icons/16/export.png"));

		VerticalLayout popupButtonsControl = new VerticalLayout();
		exportButtonControl.setContent(popupButtonsControl);

		Button exportPdfBtn = new Button("Pdf");
		FileDownloader pdfDownloader = new FileDownloader(
				constructStreamResource(ReportExportType.PDF));
		pdfDownloader.extend(exportPdfBtn);
		exportPdfBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/pdf.png"));
		exportPdfBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportPdfBtn);

		Button exportExcelBtn = new Button("Excel");
		FileDownloader excelDownloader = new FileDownloader(
				constructStreamResource(ReportExportType.EXCEL));
		excelDownloader.extend(exportExcelBtn);
		exportExcelBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/excel.png"));
		exportExcelBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportExcelBtn);

		controlBtns.addComponent(exportButtonControl);

		VerticalLayout selectionLayoutWrapper = new VerticalLayout();
		selectionLayoutWrapper.setWidth("100%");
		selectionLayoutWrapper
				.addStyleName("time-tracking-summary-search-panel");
		contentWrapper.addComponent(selectionLayoutWrapper);

		final GridLayout selectionLayout = new GridLayout(3, 2);
		selectionLayout.setSpacing(true);
		selectionLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
		selectionLayout.setMargin(true);
		selectionLayoutWrapper.addComponent(selectionLayout);

		VerticalLayout summaryLbWrapper = new VerticalLayout();
		summaryLbWrapper.setWidth("100px");
		Label summaryLb = new Label("Summary:");
		summaryLb.setWidthUndefined();
		UiUtils.addComponent(summaryLbWrapper, summaryLb, Alignment.TOP_RIGHT);
		selectionLayout.addComponent(summaryLbWrapper, 0, 0);

		this.summaryField = new TextField();
		this.summaryField.setWidth("100%");
		selectionLayout.addComponent(this.summaryField, 1, 0);

		VerticalLayout projectLbWrapper = new VerticalLayout();
		projectLbWrapper.setWidth("100px");
		Label projectLb = new Label("Project:");
		projectLb.setWidthUndefined();
		UiUtils.addComponent(projectLbWrapper, projectLb, Alignment.TOP_RIGHT);
		selectionLayout.addComponent(projectLbWrapper, 0, 1);

		this.projectField = new UserInvolvedProjectsListSelect();
		initListSelectStyle(this.projectField);
		selectionLayout.addComponent(this.projectField, 1, 1);

		final Button queryBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_SUBMIT),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
					public void buttonClick(final ClickEvent event) {
						String summary = summaryField.getValue();
						if (!StringUtils.isEmpty(summary)) {
							searchCriteria.setSummary(new StringSearchField(
									summary));
						}
						final Collection<Integer> selectedProjects = (Collection<Integer>) projectField
								.getValue();
						if (CollectionUtils.isNotEmpty(selectedProjects)) {
							searchCriteria.setExtraTypeIds(new SetSearchField(
									SearchField.AND, selectedProjects));
						} else {
							List<Integer> keys = new ArrayList<Integer>();
							for (SimpleProject project : projects) {
								keys.add(project.getId());
							}
							searchCriteria.setExtraTypeIds(new SetSearchField(
									SearchField.AND, keys));
						}
						ticketTable.setSearchCriteria(searchCriteria);
					}
				});
		queryBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

		VerticalLayout queryBtnWrapper = new VerticalLayout();
		queryBtnWrapper.setWidth("100px");
		UiUtils.addComponent(queryBtnWrapper, queryBtn, Alignment.TOP_RIGHT);
		selectionLayout.addComponent(queryBtnWrapper, 2, 0);

		this.ticketTable = new FollowingTicketTableDisplay();
		this.ticketTable.addStyleName("full-border-table");
		this.ticketTable.setMargin(new MarginInfo(true, false, false, false));
		contentWrapper.addComponent(this.ticketTable);
	}

	private void initListSelectStyle(ListSelect listSelect) {
		listSelect.setWidth("300px");
		listSelect.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		listSelect.setNullSelectionAllowed(false);
		listSelect.setMultiSelect(true);
		listSelect.setRows(4);
	}

	private StreamResource constructStreamResource(
			final ReportExportType exportType) {

		LazyStreamSource streamSource = new LazyStreamSource() {
			private static final long serialVersionUID = 1L;

			@Override
			protected StreamSource buildStreamSource() {
				return new SimpleGridExportItemsStreamResource.AllItems<FollowingTicketSearchCriteria, FollowingTicket>(
						"Following Tickets Report",
						new RpParameterBuilder(ticketTable.getDisplayColumns()),
						exportType,
						ApplicationContextUtil
								.getSpringBean(ProjectFollowingTicketService.class),
						searchCriteria, FollowingTicket.class);
			}
		};

		StreamResource res = new StreamResource(streamSource,
				ExportItemsStreamResource.getDefaultExportFileName(exportType));
		return res;
	}

	@Override
	public void displayFollowingTicket(final List<Integer> prjKeys) {
		if (CollectionUtils.isNotEmpty(prjKeys)) {
			searchCriteria = new FollowingTicketSearchCriteria();
			searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(prjKeys
					.toArray(new Integer[0])));
			searchCriteria.setUser(new StringSearchField(AppContext
					.getUsername()));
		}
	}

	private class UserInvolvedProjectsListSelect extends ListSelect {
		private static final long serialVersionUID = 1L;

		public UserInvolvedProjectsListSelect() {
			for (SimpleProject project : projects) {
				this.addItem(project.getId());
				this.setItemCaption(project.getId(), project.getName());
			}
		}

	}
}
