/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.web.ui;

import com.mycollab.common.domain.FavoriteItem;
import com.mycollab.common.service.FavoriteItemService;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.PageView;
import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.3.3
 */
public abstract class AbstractPreviewItemComp<B> extends VerticalLayout implements PageView {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = LoggerFactory.getLogger(AbstractPreviewItemComp.class);

    protected B beanItem;
    private boolean isDisplaySideBar = true;

    protected AdvancedPreviewBeanForm<B> previewForm;
    protected ReadViewLayout previewLayout;
    protected MHorizontalLayout header;
    private MVerticalLayout sidebarContent;
    private MVerticalLayout bodyContent;
    private MButton favoriteBtn;

    public AbstractPreviewItemComp(String headerText, VaadinIcons iconResource) {
        this(headerText, iconResource, null, false);
    }

    public AbstractPreviewItemComp(MHorizontalLayout customHeader, ReadViewLayout layout) {
        this.header = customHeader;
        this.addComponent(header);
        isDisplaySideBar = false;
        this.previewLayout = layout;
        initContent();
    }

    public AbstractPreviewItemComp(String headerText, VaadinIcons iconResource, ReadViewLayout layout) {
        this(headerText, iconResource, layout, true);
    }

    public AbstractPreviewItemComp(String headerText, VaadinIcons iconResource, ReadViewLayout layout, boolean isDisplaySideBar) {
        ELabel headerLbl = ELabel.h2("").withUndefinedWidth();
        this.previewLayout = layout;
        this.isDisplaySideBar = isDisplaySideBar;

        header = new MHorizontalLayout().withFullWidth();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        if (iconResource != null) {
            String title = iconResource.getHtml() + " " + headerText;
            headerLbl.setValue(title);
        } else {
            headerLbl.setValue(headerText);
        }

        if (SiteConfiguration.isCommunityEdition()) {
            header.with(headerLbl);
        } else {
            favoriteBtn = new MButton("", clickEvent -> toggleFavorite()).withIcon(VaadinIcons.HEART);
            header.with(new MCssLayout(headerLbl, favoriteBtn));
        }

        this.addComponent(header);
        ComponentContainer extraComp;
        if ((extraComp = createExtraControls()) != null) {
            this.addComponent(extraComp);
        }
        initContent();
    }

    private void initContent() {
        previewForm = initPreviewForm();
        HorizontalLayout actionControls = createButtonControls();
        if (actionControls != null) {
            header.with(actionControls).expand(actionControls).withAlign(actionControls, Alignment.TOP_RIGHT);
        }

        MCssLayout contentWrapper = new MCssLayout().withFullSize();

        if (previewLayout == null)
            previewLayout = new DefaultReadViewLayout("");

        contentWrapper.addComponent(previewLayout);

        if (isDisplaySideBar) {
            RightSidebarLayout bodyContainer = new RightSidebarLayout();

            bodyContent = new MVerticalLayout(previewForm).withSpacing(false).withMargin(false).withFullSize().withId("bodyContent");
            bodyContainer.setContent(bodyContent);
            sidebarContent = new MVerticalLayout().withWidth("250px").withStyleName("readview-sidebar");
            bodyContainer.setSidebar(sidebarContent);
            previewLayout.addBody(bodyContainer);
        } else {
            bodyContent = new MVerticalLayout(previewForm).withSpacing(false).withMargin(false).withFullSize();
            previewLayout.addBody(bodyContent);
        }

        this.addComponent(contentWrapper);
    }

    abstract protected void initRelatedComponents();

    abstract protected String getType();

    private void toggleFavorite() {
        try {
            if (isFavorite()) {
                favoriteBtn.removeStyleName("favorite-btn-selected");
                favoriteBtn.addStyleName("favorite-btn");
            } else {
                favoriteBtn.addStyleName("favorite-btn-selected");
                favoriteBtn.removeStyleName("favorite-btn");
            }
            FavoriteItem favoriteItem = new FavoriteItem();
            favoriteItem.setExtratypeid(CurrentProjectVariables.getProjectId());
            favoriteItem.setType(getType());
            favoriteItem.setTypeid(PropertyUtils.getProperty(beanItem, "id").toString());
            favoriteItem.setSaccountid(AppUI.getAccountId());
            favoriteItem.setCreateduser(UserUIContext.getUsername());
            FavoriteItemService favoriteItemService = AppContextUtil.getSpringBean(FavoriteItemService.class);
            favoriteItemService.saveOrDelete(favoriteItem);
        } catch (Exception e) {
            LOG.error("Error while set favorite flag to bean", e);
        }
    }

    private boolean isFavorite() {
        try {
            FavoriteItemService favoriteItemService = AppContextUtil.getSpringBean(FavoriteItemService.class);
            return favoriteItemService.isUserFavorite(UserUIContext.getUsername(), getType(),
                    PropertyUtils.getProperty(beanItem, "id").toString());
        } catch (Exception e) {
            LOG.error("Error while check favorite", e);
            return false;
        }
    }

    public void previewItem(final B item) {
        this.beanItem = item;
        initLayout();

        if (previewLayout instanceof DefaultReadViewLayout) {
            ((DefaultReadViewLayout) previewLayout).setTitle(initFormTitle());
        }

        previewForm.setBean(item);

        if (favoriteBtn != null) {
            String favStyle = isFavorite() ? "favorite-btn-selected" : "favorite-btn";
            favoriteBtn.addStyleName(favStyle);
        }

        onPreviewItem();
    }

    private void initLayout() {
        if (sidebarContent != null) {
            sidebarContent.removeAllComponents();
        }

        initRelatedComponents();
        ComponentContainer bottomPanel = createBottomPanel();
        if (bottomPanel != null) {
            if (bodyContent.getComponentCount() >= 2) {
                bodyContent.replaceComponent(bodyContent.getComponent(bodyContent.getComponentCount() - 1), bottomPanel);
            } else {
                bodyContent.addComponent(bottomPanel);
            }
        }
    }

    public void addToSideBar(Component... components) {
        Arrays.stream(components).forEach(sidebarContent::addComponent);
    }

    public B getBeanItem() {
        return beanItem;
    }

    public AdvancedPreviewBeanForm<B> getPreviewForm() {
        return previewForm;
    }

    protected void addLayoutStyleName(String styleName) {
        previewLayout.addTitleStyleName(styleName);
    }

    protected void removeLayoutStyleName(String styleName) {
        previewLayout.removeTitleStyleName(styleName);
    }

    abstract protected void onPreviewItem();

    abstract protected String initFormTitle();

    abstract protected AdvancedPreviewBeanForm<B> initPreviewForm();

    protected ComponentContainer createExtraControls() {
        return null;
    }

    abstract protected HorizontalLayout createButtonControls();

    abstract protected ComponentContainer createBottomPanel();
}
