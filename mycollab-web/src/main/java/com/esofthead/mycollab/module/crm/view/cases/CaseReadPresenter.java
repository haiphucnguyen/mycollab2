package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class CaseReadPresenter extends CrmGenericPresenter<CaseReadView> {

    private static final long serialVersionUID = 1L;

    public CaseReadPresenter() {
        super(CaseReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Case>() {
                    @Override
                    public void onEdit(Case data) {
                        EventBus.getInstance().fireEvent(
                                new CaseEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(Case data) {
                        CaseService caseService = AppContext
                                .getSpringBean(CaseService.class);
                        caseService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new CaseEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(Case data) {
                        Case cloneData = (Case) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new CaseEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new CaseEvent.GotoList(this, null));
                    }
                    
                	@Override
					public void gotoNext(Case data) {
						CaseService caseService = AppContext
								.getSpringBean(CaseService.class);
						CaseSearchCriteria criteria = new CaseSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATHER));
						Integer nextId = caseService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new CaseEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the last record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(Case data) {
						CaseService caseService = AppContext
								.getSpringBean(CaseService.class);
						CaseSearchCriteria criteria = new CaseSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = caseService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new CaseEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the first record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
                });

        view.getRelatedActivityHandlers().addRelatedListHandler(
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        if (itemId.equals("task")) {
                            Task task = new Task();
                            task.setType(CrmTypeConstants.CASE);
                            task.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.TaskEdit(CaseReadPresenter.this, task));
                        } else if (itemId.equals("meeting")) {
                            Meeting meeting = new Meeting();
                            meeting.setType(CrmTypeConstants.CASE);
                            meeting.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.MeetingEdit(CaseReadPresenter.this, meeting));
                        } else if (itemId.equals("call")) {
                            Call call = new Call();
                            call.setType(CrmTypeConstants.CASE);
                            call.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.CallEdit(CaseReadPresenter.this, call));
                        }
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (data.getParams() instanceof Integer) {
            CaseService caseService = AppContext
                    .getSpringBean(CaseService.class);
            SimpleCase cases = caseService.findCaseById((Integer) data
                    .getParams());
            if (cases != null) {
                super.onGo(container, data);
                view.previewItem(cases);
            } else {
                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                return;
            }
        }
    }
}
