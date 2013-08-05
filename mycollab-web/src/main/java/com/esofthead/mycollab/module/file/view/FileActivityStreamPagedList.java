package com.esofthead.mycollab.module.file.view;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Create;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Delete;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Move;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Rename;
import com.esofthead.mycollab.module.ecm.domain.SimpleContentActivityLog;
import com.esofthead.mycollab.module.ecm.domain.criteria.ContentActivityLogSearchCriteria;
import com.esofthead.mycollab.module.ecm.service.ContentActivityLogService;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class FileActivityStreamPagedList
		extends
		AbstractBeanPagedList<ContentActivityLogSearchCriteria, SimpleContentActivityLog> {
	private static final long serialVersionUID = 1L;

	private final ContentActivityLogService contentActivityLogService;

	public FileActivityStreamPagedList() {
		super(null, 20);
		this.contentActivityLogService = AppContext
				.getSpringBean(ContentActivityLogService.class);

	}

	@Override
	public void doSearch() {
		this.totalCount = this.contentActivityLogService
				.getTotalCount(this.searchRequest.getSearchCriteria());
		this.totalPage = (this.totalCount - 1)
				/ this.searchRequest.getNumberOfItems() + 1;
		if (this.searchRequest.getCurrentPage() > this.totalPage) {
			this.searchRequest.setCurrentPage(this.totalPage);
		}

		if (totalPage > 1) {
			if (this.controlBarWrapper != null) {
				this.removeComponent(this.controlBarWrapper);
			}
			this.addComponent(this.createPageControls());
		} else {
			if (getComponentCount() == 2) {
				removeComponent(getComponent(1));
			}
		}

		final List<SimpleContentActivityLog> currentListData = this.contentActivityLogService
				.findPagableListByCriteria(this.searchRequest);
		this.listContainer.removeAllComponents();

		Date currentDate = new GregorianCalendar(2100, 1, 1).getTime();

		try {
			for (final SimpleContentActivityLog activityStream : currentListData) {
				final Date itemCreatedDate = activityStream.getCreatedtime();

				if (!DateUtils.isSameDay(currentDate, itemCreatedDate)) {
					final CssLayout dateWrapper = new CssLayout();
					dateWrapper.setWidth("100%");
					dateWrapper.addStyleName("date-wrapper");
					dateWrapper.addComponent(new Label(AppContext
							.formatDate(itemCreatedDate)));
					this.listContainer.addComponent(dateWrapper);
					currentDate = itemCreatedDate;
				}
				StringBuffer content = new StringBuffer("");

				ContentActivityLogAction contentActivityAction = ContentActivityLogAction
						.fromString(activityStream.getActiondesc());
				String userName = activityStream.getCreateduser();
				if (userName.indexOf("@") != -1)
					userName = userName.substring(0, userName.indexOf("@"));
				content.append(userName);

				if (contentActivityAction instanceof Move) {
					String oldName = ((Move) contentActivityAction)
							.getOldPath();
					String newName = ((Move) contentActivityAction)
							.getNewPath();
					oldName = oldName.substring(oldName.lastIndexOf("/") + 1);
					newName = newName.substring(newName.lastIndexOf("/") + 1);
					content.append(" moved ")
							.append(((Move) contentActivityAction)
									.getMoveType()).append(oldName);
					content.append(" to ");
					content.append(((Move) contentActivityAction).getNewPath());
				} else if (contentActivityAction instanceof Delete) {
					String oldName = ((Delete) contentActivityAction).getPath();
					oldName = oldName.substring(oldName.lastIndexOf("/") + 1);

					content.append(" deleted ").append(
							((Delete) contentActivityAction).getDeleteType());
					content.append(" ").append(oldName + " ");
				} else if (contentActivityAction instanceof Create) {
					String createName = ((Create) contentActivityAction)
							.getPath();
					createName = createName.substring(createName
							.lastIndexOf("/") + 1);
					content.append(" created ").append(
							((Create) contentActivityAction).getCreateType());
					content.append(" ").append(createName + " ");
				} else if (contentActivityAction instanceof Rename) {
					String newName = ((Rename) contentActivityAction)
							.getNewName();
					String oldName = ((Rename) contentActivityAction)
							.getOldName();
					content.append(" renamed ").append(
							((Rename) contentActivityAction).getResourceType());
					content.append(" " + oldName + " ");
					content.append("to " + newName);
				}
				final Label actionLbl = new Label(content.toString(),
						Label.CONTENT_XHTML);
				final CssLayout streamWrapper = new CssLayout();
				streamWrapper.setWidth("100%");
				streamWrapper.addStyleName("stream-wrapper");
				streamWrapper.addComponent(actionLbl);
				this.listContainer.addComponent(streamWrapper);
			}
		} catch (final Exception e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	protected int queryTotalCount() {
		return this.totalCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<SimpleContentActivityLog> queryCurrentData() {
		return this.contentActivityLogService
				.findPagableListByCriteria(this.searchRequest);
	}

}
