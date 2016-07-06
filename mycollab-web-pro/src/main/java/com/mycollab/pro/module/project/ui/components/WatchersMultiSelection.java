package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectMemberStatusConstants;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.db.arguments.*;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.mycollab.vaadin.web.ui.UIConstants;
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
    private List<SimpleProjectMember> unsavedMembers = new ArrayList<>();
    private List<SimpleUser> followers;
    private String type;
    private Integer typeId;
    private MonitorItemService monitorItemService;

    public WatchersMultiSelection(String type, Integer typeId) {
        this.type = type;
        this.typeId = typeId;
        monitorItemService = AppContextUtil.getSpringBean(MonitorItemService.class);
        followers = monitorItemService.getWatchers(type, typeId);
        new Restrain(this).setMaxHeight("600px");
        this.addStyleName(UIConstants.SCROLLABLE_CONTAINER);

        ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        criteria.setStatuses(new SetSearchField<>(ProjectMemberStatusConstants.ACTIVE));
        criteria.addOrderField(new SearchCriteria.OrderField("memberFullName", SearchCriteria.ASC));

        ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
        List<SimpleProjectMember> projectMembers = projectMemberService.findPagableListByCriteria(new BasicSearchRequest<>(
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
            criteria.setType(StringSearchField.and(type));
            criteria.setUser(StringSearchField.and(username));
            monitorItemService.removeByCriteria(criteria, AppContext.getAccountId());
        }
    }
}
