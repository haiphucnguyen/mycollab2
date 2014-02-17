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

package com.esofthead.mycollab.module.project.view.bug;

import java.util.Arrays;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.CheckBoxDecor;
import com.esofthead.mycollab.vaadin.ui.DefaultMassItemActionHandlersContainer;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ComponentListViewImpl extends AbstractPageView implements
		ComponentListView {
	private static Logger log = LoggerFactory
			.getLogger(ComponentListViewImpl.class);

	private static final long serialVersionUID = 1L;
	private final ComponentSearchPanel componentSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private DefaultPagedBeanTable<ComponentService, ComponentSearchCriteria, SimpleComponent> tableItem;
	private final VerticalLayout componentListLayout;
	private DefaultMassItemActionHandlersContainer tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public ComponentListViewImpl() {

		this.setMargin(new MarginInfo(true, false, false, false));

		this.componentSearchPanel = new ComponentSearchPanel();
		this.addComponent(this.componentSearchPanel);

		this.componentListLayout = new VerticalLayout();
		this.addComponent(this.componentListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new DefaultPagedBeanTable<ComponentService, ComponentSearchCriteria, SimpleComponent>(
				ApplicationContextUtil.getSpringBean(ComponentService.class),
				SimpleComponent.class, new TableViewField("", "selected",
						UIConstants.TABLE_CONTROL_WIDTH), Arrays.asList(
						new TableViewField("Name", "componentname",
								UIConstants.TABLE_EX_LABEL_WIDTH),
						new TableViewField("Lead Name", "userLeadFullName",
								UIConstants.TABLE_X_LABEL_WIDTH),
						new TableViewField("Description", "description",
								UIConstants.TABLE_EX_LABEL_WIDTH)));

		this.tableItem.addGeneratedColumn("selected",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public Object generateCell(final Table source,
							final Object itemId, final Object columnId) {
						final CheckBoxDecor cb = new CheckBoxDecor("", false);
						cb.setImmediate(true);
						cb.addValueChangeListener(new Property.ValueChangeListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void valueChange(ValueChangeEvent event) {
								final SimpleComponent component = ComponentListViewImpl.this.tableItem
										.getBeanByIndex(itemId);
								ComponentListViewImpl.this.tableItem
										.fireSelectItemEvent(component);

							}
						});

						final SimpleComponent component = ComponentListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						component.setExtraData(cb);
						return cb;
					}
				});

		this.tableItem.addGeneratedColumn("componentname",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleComponent bugComponent = ComponentListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						final ButtonLink b = new ButtonLink(bugComponent
								.getComponentname(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(
											final Button.ClickEvent event) {
										EventBus.getInstance().fireEvent(
												new BugComponentEvent.GotoRead(
														this, bugComponent
																.getId()));
									}
								});
						if (bugComponent.getStatus() != null
								&& bugComponent.getStatus().equals("Close")) {
							b.addStyleName(UIConstants.LINK_COMPLETED);
						}
						b.setDescription(generateToolTip(bugComponent));
						return b;

					}
				});

		this.tableItem.addGeneratedColumn("userLeadFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleComponent bugComponent = ComponentListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						return new ProjectUserLink(bugComponent.getUserlead(),
								bugComponent.getUserLeadAvatarId(),
								bugComponent.getUserLeadFullName());

					}
				});

		this.tableItem.setWidth("100%");

		this.componentListLayout.addComponent(this
				.constructTableActionControls());
		this.componentListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<ComponentSearchCriteria> getSearchHandlers() {
		return this.componentSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		layout.addComponent(this.selectOptionButton);

		this.tableActionControls = new DefaultMassItemActionHandlersContainer();
		if (CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.COMPONENTS)) {
			tableActionControls.addActionItem(
					MassItemActionHandler.DELETE_ACTION,
					MyCollabResource.newResource("icons/16/action/delete.png"),
					"delete");
		}

		tableActionControls.addActionItem(MassItemActionHandler.MAIL_ACTION,
				MyCollabResource.newResource("icons/16/action/mail.png"),
				"mail");

		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_PDF_ACTION,
				MyCollabResource.newResource("icons/16/action/pdf.png"),
				"export", "export.pdf");
		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_EXCEL_ACTION,
				MyCollabResource.newResource("icons/16/action/excel.png"),
				"export", "export.xlsx");
		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_CSV_ACTION,
				MyCollabResource.newResource("icons/16/action/csv.png"),
				"export", "export.csv");

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layoutWrapper;
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public HasMassItemActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleComponent> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<ComponentSearchCriteria, SimpleComponent> getPagedBeanTable() {
		return this.tableItem;
	}

	private String generateToolTip(SimpleComponent component) {
		try {
			Div div = new Div();
			H3 componentName = new H3();
			componentName.appendText(Jsoup.parse(component.getComponentname())
					.html());
			div.appendChild(componentName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :350px; color: #5a5a5a; font-size:11px;");
			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 90px; vertical-align: top; text-align: right;")
							.appendText("Component Name:"))
					.appendChild(
							new Td().appendText(StringUtils
									.getStringFieldValue(component
											.getComponentname())));

			Tr trRow2 = new Tr();
			Td trRow2_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringRemoveHtmlTag(component
									.getDescription()));
			trRow2_value.setAttribute("colspan", "3");
			trRow2.appendChild(
					new Td().setStyle(
							"width: 90px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow2_value);
			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 90px; vertical-align: top; text-align: right;")
							.appendText("Lead:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(component.getUserlead() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	component
																			.getUserlead())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					component
																							.getUserLeadAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(component
																			.getUserLeadFullName()))));

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error("Error while generate tooltip for Component", e);
			return "";
		}
	}
}
