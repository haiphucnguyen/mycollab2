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
package com.mycollab.module.crm.ui.components;

import com.mycollab.common.domain.FavoriteItem;
import com.mycollab.common.service.FavoriteItemService;
import com.mycollab.module.crm.view.CrmVerticalTabsheet;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.ReadViewLayout;
import com.mycollab.vaadin.web.ui.VerticalTabsheet;
import com.mycollab.vaadin.web.ui.VerticalTabsheet.TabImpl;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 3.0
 */
public abstract class AbstractPreviewItemComp<B> extends AbstractVerticalPageView {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = LoggerFactory.getLogger(AbstractPreviewItemComp.class);

    protected B beanItem;
    private FontAwesome iconResource;
    private ELabel headerTitle;
    protected MHorizontalLayout header;
    protected VerticalLayout tabContent;
    protected VerticalTabsheet previewItemContainer;
    protected ReadViewLayout previewLayout;
    protected AdvancedPreviewBeanForm<B> previewForm;
    private MVerticalLayout sidebarContent;
    private boolean isDisplaySideBar = true;
    private MButton favoriteBtn;

    public AbstractPreviewItemComp(FontAwesome iconResource) {
        super();
        this.iconResource = iconResource;
        previewItemContainer = new CrmVerticalTabsheet(true);
        previewItemContainer.setSizeFull();
        previewItemContainer.setNavigatorWidth("100%");
        previewItemContainer.setNavigatorStyleName("sidebar-menu");

        headerTitle = ELabel.h2("");
        header = new MHorizontalLayout(headerTitle).withStyleName("hdr-view").withFullWidth().withMargin(new
                MarginInfo(true, false, true, false)).expand(headerTitle);
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        addComponent(previewItemContainer);

        CssLayout navigatorWrapper = previewItemContainer.getNavigatorWrapper();
        navigatorWrapper.setWidth("200px");

        previewItemContainer.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                Tab tab = ((VerticalTabsheet) event.getSource()).getSelectedTab();
                previewItemContainer.selectTab(((TabImpl) tab).getTabId());
            }
        });

        tabContent = previewItemContainer.getContentWrapper();
        tabContent.addComponent(header, 0);

        previewForm = initPreviewForm();

        ComponentContainer actionControls = createButtonControls();
        if (actionControls != null) {
            header.with(actionControls).withAlign(actionControls, Alignment.TOP_RIGHT);
        }

        initRelatedComponents();

        tabContent.addComponent(previewForm);
        tabContent.addComponent(createBottomPanel());
    }

    @Override
    public void attach() {
        super.attach();

        if (this.getParent() instanceof CustomLayout) {
            this.getParent().addStyleName("preview-comp");
        }
    }

    public void previewItem(final B item) {
        this.beanItem = item;
        if (favoriteBtn != null) {
            if (isFavorite()) {
                favoriteBtn.addStyleName("favorite-btn-selected");
            } else {
                favoriteBtn.addStyleName("favorite-btn");
            }
        }

        previewForm.setFormLayoutFactory(initFormLayoutFactory());
        previewForm.setBeanFormFieldFactory(initBeanFormFieldFactory());
        previewForm.setBean(item);

        headerTitle.setValue(iconResource.getHtml() + " " + initFormTitle());
        onPreviewItem();
    }

    public B getBeanItem() {
        return beanItem;
    }

    public AdvancedPreviewBeanForm<B> getPreviewForm() {
        return previewForm;
    }

    abstract protected void onPreviewItem();

    abstract protected String initFormTitle();

    abstract protected AdvancedPreviewBeanForm<B> initPreviewForm();

    abstract protected void initRelatedComponents();

    abstract protected IFormLayoutFactory initFormLayoutFactory();

    abstract protected AbstractBeanFieldGroupViewFieldFactory<B> initBeanFormFieldFactory();

    abstract protected ComponentContainer createButtonControls();

    abstract protected ComponentContainer createBottomPanel();

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
            favoriteItem.setSaccountid(MyCollabUI.getAccountId());
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

    abstract protected String getType();

}
