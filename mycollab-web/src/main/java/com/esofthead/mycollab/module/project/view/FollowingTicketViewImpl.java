package com.esofthead.mycollab.module.project.view;

import java.util.List;

import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.module.project.domain.FollowingTicket;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.vaadin.ui.Label;

@ViewComponent
public class FollowingTicketViewImpl extends AbstractView implements
		FollowingTicketView {
	private static final long serialVersionUID = 1L;

	public FollowingTicketViewImpl() {
		this.addComponent(new Label("Display tickets"));
	}

	@Override
	public void displayFollowingTicket() {
		// TODO Auto-generated method stub

	}

	private class FollowingTicketTable extends
			AbstractPagedBeanTable<MonitorSearchCriteria, FollowingTicket> {

		private static final long serialVersionUID = 1L;

		public FollowingTicketTable() {
			super(FollowingTicket.class, new String[] {}, new String[] {});
		}

		@Override
		protected int queryTotalCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		protected List<FollowingTicket> queryCurrentData() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
