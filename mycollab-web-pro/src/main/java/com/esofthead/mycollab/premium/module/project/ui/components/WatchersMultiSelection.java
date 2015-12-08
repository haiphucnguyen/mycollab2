package com.esofthead.mycollab.premium.module.project.ui.components;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
public class WatchersMultiSelection extends MVerticalLayout {
    private List<SimpleProjectMember> projectMembers;
    private List<SimpleProjectMember> unsavedMembers = new ArrayList<>();
    private List<SimpleUser> followers;
    private String type;
    private Integer typeId;
    private MonitorItemService monitorItemService;

    public WatchersMultiSelection(String type, Integer typeId) {
        this.type = type;
        this.typeId = typeId;
        monitorItemService = ApplicationContextUtil.getSpringBean(MonitorItemService.class);
        followers = monitorItemService.getWatchers(type, typeId);
        new Restrain(this).setMaxHeight("600px");
        this.addStyleName("scrollable-container");

        ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        criteria.setStatus(new StringSearchField(ProjectMemberStatusConstants.ACTIVE));

        ProjectMemberService projectMemberService = ApplicationContextUtil.getSpringBean(ProjectMemberService.class);
        projectMembers = projectMemberService.findPagableListByCriteria(new SearchRequest<>(
                criteria, 0, Integer.MAX_VALUE));
        for (SimpleProjectMember member : projectMembers) {
            this.addComponent(new FollowerRow(member));
        }

        this.setWidth("100%");
    }

    public List<MonitorItem> getUnsavedItems() {
        List<MonitorItem> items = new ArrayList<>(unsavedMembers.size());
        for (SimpleProjectMember member : unsavedMembers) {
            MonitorItem item = new MonitorItem();
            item.setExtratypeid(CurrentProjectVariables.getProjectId());
            item.setMonitorDate(new GregorianCalendar().getTime());
            item.setSaccountid(AppContext.getAccountId());
            item.setType(type);
            item.setTypeid(typeId);
            item.setUser(member.getUsername());
            items.add(item);
        }
        return items;
    }

    private class FollowerRow extends MHorizontalLayout {
        private CheckBox isSelectedBox;
        private boolean isWatching = false;

        private FollowerRow(final SimpleProjectMember member) {
            isSelectedBox = new CheckBox();
            Image avatarResource = UserAvatarControlFactory.createUserAvatarEmbeddedComponent(member.getMemberAvatarId(), 16);
            Label icon = new Label(StringUtils.trim(member.getDisplayName(), 20, true));
            icon.setDescription(member.getDisplayName());
            this.with(isSelectedBox, avatarResource, icon);
            for (SimpleUser follower : followers) {
                if (follower.getUsername().equals(member.getUsername())) {
                    isSelectedBox.setValue(true);
                    isWatching = true;
                }
            }
            isSelectedBox.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (isSelectedBox.getValue()) {
                        if (!isWatching) {
                            unsavedMembers.add(member);
                        }
                    } else {
                        if (isWatching) {
                            unfollowItem(member.getUsername());
                            isWatching = false;
                        } else {
                            unsavedMembers.remove(member);
                        }
                    }
                }
            });
        }

        private void unfollowItem(String username) {
            MonitorSearchCriteria criteria = new MonitorSearchCriteria();
            criteria.setTypeId(new NumberSearchField(typeId));
            criteria.setType(new StringSearchField(type));
            criteria.setUser(new StringSearchField(username));
            monitorItemService.removeByCriteria(criteria, AppContext.getAccountId());
        }
    }
}
