package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.AccountEvent.GotoRead;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.ActivityEvent.GotoCalendar;
import com.esofthead.mycollab.module.crm.events.ActivityEvent.GotoTodoList;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.CrmEvent;
import com.esofthead.mycollab.module.crm.events.CrmEvent.GotoHome;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.view.account.AccountAddPresenter;
import com.esofthead.mycollab.module.crm.view.account.AccountListPresenter;
import com.esofthead.mycollab.module.crm.view.account.AccountReadPresenter;
import com.esofthead.mycollab.module.crm.view.activity.ActivityRootPresenter;
import com.esofthead.mycollab.module.crm.view.activity.CallAddPresenter;
import com.esofthead.mycollab.module.crm.view.activity.CallReadPresenter;
import com.esofthead.mycollab.module.crm.view.activity.MeetingAddPresenter;
import com.esofthead.mycollab.module.crm.view.activity.MeetingReadPresenter;
import com.esofthead.mycollab.module.crm.view.activity.TodoAddPresenter;
import com.esofthead.mycollab.module.crm.view.activity.TodoAddViewImpl;
import com.esofthead.mycollab.module.crm.view.activity.TodoReadPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignAddPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignListPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignReadPresenter;
import com.esofthead.mycollab.module.crm.view.cases.CaseAddPresenter;
import com.esofthead.mycollab.module.crm.view.cases.CaseListPresenter;
import com.esofthead.mycollab.module.crm.view.cases.CaseReadPresenter;
import com.esofthead.mycollab.module.crm.view.contact.ContactAddPresenter;
import com.esofthead.mycollab.module.crm.view.contact.ContactListPresenter;
import com.esofthead.mycollab.module.crm.view.contact.ContactReadPresenter;
import com.esofthead.mycollab.module.crm.view.lead.LeadAddPresenter;
import com.esofthead.mycollab.module.crm.view.lead.LeadListPresenter;
import com.esofthead.mycollab.module.crm.view.lead.LeadReadPresenter;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityAddPresenter;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListPresenter;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityReadPresenter;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;

public class CrmController {

    private CrmContainer container;

    public CrmController(CrmContainer container) {
        this.container = container;

        bindCrmEvents();
        bindAccountEvents();
        bindActivityEvents();
        bindCampaignEvents();
        bindContactEvents();
        bindLeadEvents();
        bindOpportunityEvents();
        bindCasesEvents();
    }

    @SuppressWarnings("serial")
    private void bindCrmEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<CrmEvent.GotoHome>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CrmEvent.GotoHome.class;
                    }

                    @Override
                    public void handle(GotoHome event) {
                        CrmHomePresenter presenter = PresenterResolver
                                .getPresenter(CrmHomePresenter.class);
                        presenter.go(container, null);
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindAccountEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<AccountEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return AccountEvent.GotoList.class;
                    }

                    @Override
                    public void handle(AccountEvent.GotoList event) {
                        AccountListPresenter presenter = PresenterResolver
                                .getPresenter(AccountListPresenter.class);

                        AccountSearchCriteria criteria = new AccountSearchCriteria();
                        criteria.setSaccountid(new NumberSearchField(
                                SearchField.AND, AppContext.getAccountId()));
                        presenter.go(container,
                                new ScreenData.Search<AccountSearchCriteria>(
                                criteria));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<AccountEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return AccountEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(AccountEvent.GotoAdd event) {
                        AccountAddPresenter presenter = PresenterResolver
                                .getPresenter(AccountAddPresenter.class);
                        presenter.go(container, new ScreenData.Add<Account>(
                                new Account()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<AccountEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return AccountEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(AccountEvent.GotoEdit event) {
                        AccountAddPresenter presenter = PresenterResolver
                                .getPresenter(AccountAddPresenter.class);

                        Account account = (Account) event.getData();
                        presenter.go(container, new ScreenData.Edit<Account>(
                                account));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<AccountEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return AccountEvent.GotoRead.class;
                    }

                    @SuppressWarnings({"rawtypes", "unchecked"})
                    @Override
                    public void handle(GotoRead event) {
                        AccountReadPresenter presenter = PresenterResolver
                                .getPresenter(AccountReadPresenter.class);
                        presenter.go(container,
                                new ScreenData.Preview(event.getData()));
                    }
                });
    }

    private void bindActivityEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.GotoCalendar>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.GotoCalendar.class;
                    }

                    @Override
                    public void handle(GotoCalendar event) {
                        ActivityRootPresenter presenter = PresenterResolver
                                .getPresenter(ActivityRootPresenter.class);
                        presenter.go(container, null);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.GotoTodoList>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.GotoTodoList.class;
                    }

                    @Override
                    public void handle(GotoTodoList event) {
                        ActivityRootPresenter presenter = PresenterResolver
                                .getPresenter(ActivityRootPresenter.class);
                        presenter.go(container, new ScreenData<String>("todo"));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.TaskAdd>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.TaskAdd.class;
                    }

                    @Override
                    public void handle(ActivityEvent.TaskAdd event) {
                        TodoAddViewImpl view = ViewManager
                                .getView(TodoAddViewImpl.class);
                        new TodoAddPresenter(view).go(container,
                                new ScreenData.Add<Task>(new Task()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.TaskEdit>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.TaskEdit.class;
                    }

                    @Override
                    public void handle(ActivityEvent.TaskEdit event) {
                        TodoAddViewImpl view = ViewManager
                                .getView(TodoAddViewImpl.class);
                        new TodoAddPresenter(view).go(
                                container,
                                new ScreenData.Edit<Task>((Task) event
                                .getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.TaskRead>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.TaskRead.class;
                    }

                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public void handle(ActivityEvent.TaskRead event) {
                        TodoReadPresenter presenter = PresenterResolver
                                .getPresenter(TodoReadPresenter.class);
                        presenter.go(container,
                                new ScreenData.Preview(event.getData()));

                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.MeetingAdd>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.MeetingAdd.class;
                    }

                    @Override
                    public void handle(ActivityEvent.MeetingAdd event) {
                        MeetingAddPresenter presenter = PresenterResolver
                                .getPresenter(MeetingAddPresenter.class);
                        presenter.go(container, new ScreenData.Add<Meeting>(
                                new Meeting()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.MeetingEdit>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.MeetingEdit.class;
                    }

                    @Override
                    public void handle(ActivityEvent.MeetingEdit event) {
                        MeetingAddPresenter presenter = PresenterResolver
                                .getPresenter(MeetingAddPresenter.class);
                        presenter.go(container, new ScreenData.Edit<Meeting>(
                                (Meeting) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.MeetingRead>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.MeetingRead.class;
                    }

                    @Override
                    public void handle(ActivityEvent.MeetingRead event) {
                        MeetingReadPresenter presenter = PresenterResolver
                                .getPresenter(MeetingReadPresenter.class);
                        Object data = event.getData();
                        if (data instanceof Integer) {
                            MeetingService meetingService = AppContext
                                    .getSpringBean(MeetingService.class);
                            SimpleMeeting meeting = meetingService
                                    .findMeetingById((Integer) data);
                            if (meeting != null) {
                                presenter.go(container,
                                        new ScreenData.Add<Meeting>(meeting));
                            }
                        }

                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.CallAdd>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.CallAdd.class;
                    }

                    @Override
                    public void handle(ActivityEvent.CallAdd event) {
                        CallAddPresenter presenter = PresenterResolver
                                .getPresenter(CallAddPresenter.class);
                        presenter.go(container,
                                new ScreenData<Call>(new Call()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.CallEdit>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.CallEdit.class;
                    }

                    @Override
                    public void handle(ActivityEvent.CallEdit event) {
                        CallAddPresenter presenter = PresenterResolver
                                .getPresenter(CallAddPresenter.class);
                        presenter.go(container, new ScreenData.Edit<Call>(
                                (Call) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ActivityEvent.CallRead>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ActivityEvent.CallRead.class;
                    }

                    @Override
                    public void handle(ActivityEvent.CallRead event) {
                        CallReadPresenter presenter = PresenterResolver
                                .getPresenter(CallReadPresenter.class);
                        Object data = event.getData();
                        if (data instanceof Integer) {
                            CallService callService = AppContext
                                    .getSpringBean(CallService.class);
                            SimpleCall call = callService
                                    .findCallById((Integer) data);
                            if (call != null) {
                                presenter.go(container,
                                        new ScreenData.Add<Call>(call));
                            }
                        }

                    }
                });

    }

    @SuppressWarnings("serial")
    private void bindCampaignEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<CampaignEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CampaignEvent.GotoList.class;
                    }

                    @Override
                    public void handle(CampaignEvent.GotoList event) {
                        CampaignListPresenter presenter = PresenterResolver
                                .getPresenter(CampaignListPresenter.class);
                        CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
                        searchCriteria.setSaccountid(new NumberSearchField(
                                SearchField.AND, AppContext.getAccountId()));

                        presenter.go(container,
                                new ScreenData.Search<CampaignSearchCriteria>(
                                searchCriteria));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<CampaignEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CampaignEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(CampaignEvent.GotoAdd event) {
                        CampaignAddPresenter presenter = PresenterResolver
                                .getPresenter(CampaignAddPresenter.class);
                        presenter.go(container, new ScreenData.Add<Campaign>(
                                new Campaign()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<CampaignEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CampaignEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(CampaignEvent.GotoEdit event) {
                        CampaignAddPresenter presenter = PresenterResolver
                                .getPresenter(CampaignAddPresenter.class);
                        presenter.go(container, new ScreenData.Edit<Campaign>(
                                (Campaign) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<CampaignEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CampaignEvent.GotoRead.class;
                    }

                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public void handle(CampaignEvent.GotoRead event) {
                        CampaignReadPresenter presenter = PresenterResolver
                                .getPresenter(CampaignReadPresenter.class);
                        presenter.go(container,
                                new ScreenData.Preview(event.getData()));
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindContactEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<ContactEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ContactEvent.GotoList.class;
                    }

                    @Override
                    public void handle(ContactEvent.GotoList event) {
                        ContactListPresenter presenter = PresenterResolver
                                .getPresenter(ContactListPresenter.class);

                        ContactSearchCriteria searchCriteria = new ContactSearchCriteria();
                        searchCriteria.setSaccountid(new NumberSearchField(
                                SearchField.AND, AppContext.getAccountId()));
                        presenter.go(container,
                                new ScreenData.Search<ContactSearchCriteria>(
                                searchCriteria));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ContactEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ContactEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(ContactEvent.GotoAdd event) {
                        ContactAddPresenter presenter = PresenterResolver
                                .getPresenter(ContactAddPresenter.class);
                        presenter.go(container, new ScreenData.Add<SimpleContact>(
                                new SimpleContact()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ContactEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ContactEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(ContactEvent.GotoEdit event) {
                        ContactAddPresenter presenter = PresenterResolver
                                .getPresenter(ContactAddPresenter.class);
                        presenter.go(container, new ScreenData.Edit<Contact>(
                                (Contact) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ContactEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ContactEvent.GotoRead.class;
                    }

                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public void handle(ContactEvent.GotoRead event) {
                        ContactReadPresenter presenter = PresenterResolver
                                .getPresenter(ContactReadPresenter.class);
                        presenter.go(container,
                                new ScreenData.Preview(event.getData()));
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindLeadEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<LeadEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return LeadEvent.GotoList.class;
                    }

                    @Override
                    public void handle(LeadEvent.GotoList event) {
                        LeadListPresenter presenter = PresenterResolver
                                .getPresenter(LeadListPresenter.class);
                        LeadSearchCriteria searchCriteria = new LeadSearchCriteria();
                        searchCriteria.setSaccountid(new NumberSearchField(
                                SearchField.AND, AppContext.getAccountId()));
                        presenter.go(container,
                                new ScreenData.Search<LeadSearchCriteria>(
                                searchCriteria));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<LeadEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return LeadEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(LeadEvent.GotoAdd event) {
                        LeadAddPresenter presenter = PresenterResolver
                                .getPresenter(LeadAddPresenter.class);
                        presenter.go(container, new ScreenData.Add<Lead>(
                                new Lead()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<LeadEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return LeadEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(LeadEvent.GotoEdit event) {
                        LeadAddPresenter presenter = PresenterResolver
                                .getPresenter(LeadAddPresenter.class);
                        presenter.go(container, new ScreenData.Edit<Lead>(
                                (Lead) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<LeadEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return LeadEvent.GotoRead.class;
                    }

                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public void handle(LeadEvent.GotoRead event) {
                        LeadReadPresenter presenter = PresenterResolver
                                .getPresenter(LeadReadPresenter.class);
                        presenter.go(container,
                                new ScreenData.Preview(event.getData()));
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindOpportunityEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<OpportunityEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return OpportunityEvent.GotoList.class;
                    }

                    @Override
                    public void handle(OpportunityEvent.GotoList event) {
                        OpportunityListPresenter presenter = PresenterResolver
                                .getPresenter(OpportunityListPresenter.class);
                        OpportunitySearchCriteria searchCriteria = new OpportunitySearchCriteria();
                        searchCriteria.setSaccountid(new NumberSearchField(
                                SearchField.AND, AppContext.getAccountId()));
                        presenter
                                .go(container,
                                new ScreenData.Search<OpportunitySearchCriteria>(
                                searchCriteria));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<OpportunityEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return OpportunityEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(OpportunityEvent.GotoAdd event) {
                        OpportunityAddPresenter presenter = PresenterResolver
                                .getPresenter(OpportunityAddPresenter.class);
                        presenter.go(container,
                                new ScreenData.Add<Opportunity>(
                                new Opportunity()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<OpportunityEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return OpportunityEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(OpportunityEvent.GotoEdit event) {
                        OpportunityAddPresenter presenter = PresenterResolver
                                .getPresenter(OpportunityAddPresenter.class);
                        presenter.go(container,
                                new ScreenData.Edit<Opportunity>(
                                (Opportunity) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<OpportunityEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return OpportunityEvent.GotoRead.class;
                    }

                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public void handle(OpportunityEvent.GotoRead event) {
                        OpportunityReadPresenter presenter = PresenterResolver
                                .getPresenter(OpportunityReadPresenter.class);
                        presenter.go(container,
                                new ScreenData.Preview(event.getData()));
                    }
                });
    }

    @SuppressWarnings("serial")
    private void bindCasesEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<CaseEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CaseEvent.GotoList.class;
                    }

                    @Override
                    public void handle(CaseEvent.GotoList event) {
                        CaseListPresenter presenter = PresenterResolver
                                .getPresenter(CaseListPresenter.class);

                        CaseSearchCriteria searchCriteria = new CaseSearchCriteria();
                        searchCriteria.setSaccountid(new NumberSearchField(
                                SearchField.AND, AppContext.getAccountId()));
                        presenter.go(container,
                                new ScreenData.Search<CaseSearchCriteria>(
                                searchCriteria));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<CaseEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CaseEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(CaseEvent.GotoAdd event) {
                        CaseAddPresenter presenter = PresenterResolver
                                .getPresenter(CaseAddPresenter.class);
                        presenter.go(container, new ScreenData.Add<Case>(
                                new Case()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<CaseEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CaseEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(CaseEvent.GotoEdit event) {
                        CaseAddPresenter presenter = PresenterResolver
                                .getPresenter(CaseAddPresenter.class);
                        presenter.go(container, new ScreenData.Edit<Case>(
                                (Case) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<CaseEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CaseEvent.GotoRead.class;
                    }

                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public void handle(CaseEvent.GotoRead event) {
                        CaseReadPresenter presenter = PresenterResolver
                                .getPresenter(CaseReadPresenter.class);
                        presenter.go(container,
                                new ScreenData.Preview(event.getData()));
                    }
                });
    }
}
