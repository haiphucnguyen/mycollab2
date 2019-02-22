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
package com.mycollab.module.project.view.file;

import com.mycollab.common.i18n.FileI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.ecm.domain.Folder;
import com.mycollab.module.ecm.domain.Resource;
import com.mycollab.module.ecm.service.ResourceService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Tree;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
// TODO
abstract class AbstractResourceMovingWindow extends MWindow {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = LoggerFactory.getLogger(AbstractResourceMovingWindow.class);

    private final ResourceService resourceService;

    private Tree folderTree;
    private Folder baseFolder;
    private Collection<Resource> movedResources;

    AbstractResourceMovingWindow(Folder baseFolder, Resource resource) {
        this(baseFolder, Collections.singletonList(resource));
    }

    AbstractResourceMovingWindow(Folder baseFolder, Collection<Resource> lstRes) {
        super(UserUIContext.getMessage(FileI18nEnum.ACTION_MOVE_ASSETS));
        withModal(true).withResizable(false).withWidth("600px").withCenter();
        this.baseFolder = baseFolder;
        this.movedResources = lstRes;
        this.resourceService = AppContextUtil.getSpringBean(ResourceService.class);
        constructBody();
    }

    private void constructBody() {
        MVerticalLayout contentLayout = new MVerticalLayout();
        this.setContent(contentLayout);

        folderTree = new Tree();
        folderTree.setSizeFull();

        folderTree.addExpandListener(expandEvent -> {
            final Folder expandFolder = (Folder) expandEvent.getExpandedItem();
            // load externalResource if currentExpandFolder is rootFolder

            final List<Folder> subFolders = resourceService.getSubFolders(expandFolder.getPath());
//            folderTree.setItemIcon(expandFolder, FontAwesome.FOLDER_OPEN);

//            if (subFolders != null) {
//                for (final Folder subFolder : subFolders) {
//                    String subFolderName = subFolder.getName();
//                    if (!subFolderName.startsWith(".")) {
//                        expandFolder.addChild(subFolder);
//                        folderTree.addItem(subFolder);
//                        folderTree.setItemIcon(subFolder, FontAwesome.FOLDER);
//                        folderTree.setItemCaption(subFolder, subFolderName);
//                        folderTree.setParent(subFolder, expandFolder);
//                    }
//                }
//            }
        });

//        folderTree.addCollapseListener(new Tree.CollapseListener() {
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public void nodeCollapse(final CollapseEvent event) {
//                final Folder collapseFolder = (Folder) event.getItemId();
//                if (collapseFolder instanceof ExternalFolder) {
//                    folderTree.setItemIcon(collapseFolder, FontAwesome.DROPBOX);
//                } else {
//                    folderTree.setItemIcon(collapseFolder, FontAwesome.FOLDER);
//                }
//                collapseFolder.getChilds().forEach(this::recursiveRemoveSubItem);
//            }
//
//            private void recursiveRemoveSubItem(Folder collapseFolder) {
//                List<Folder> childFolders = collapseFolder.getChilds();
//                if (childFolders.size() > 0) {
//                    childFolders.forEach(this::recursiveRemoveSubItem);
//                    folderTree.removeItem(collapseFolder);
//                } else {
//                    folderTree.removeItem(collapseFolder);
//                }
//            }
//        });

//        folderTree.addItemClickListener(itemClickEvent -> baseFolder = (Folder) itemClickEvent.getItemId());

        CssLayout treeWrapper = new CssLayout(folderTree);
        treeWrapper.setSizeFull();
        contentLayout.addComponent(treeWrapper);
        displayFiles();

        MButton moveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_MOVE), clickEvent -> {
            if (!CollectionUtils.isEmpty(movedResources)) {
                boolean checkingFail = false;
                for (Resource res : movedResources) {
                    try {
                        resourceService.moveResource(res, baseFolder, UserUIContext.getUsername(), AppUI.getAccountId());
                    } catch (Exception e) {
                        checkingFail = true;
                        LOG.error("Error", e);
                    }
                }
                close();
                displayAfterMoveSuccess(baseFolder, checkingFail);
            }
        }).withIcon(FontAwesome.ARROWS).withStyleName(WebThemes.BUTTON_ACTION);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);

        MHorizontalLayout controlGroupBtnLayout = new MHorizontalLayout(cancelBtn, moveBtn);
        contentLayout.with(controlGroupBtnLayout).withAlign(controlGroupBtnLayout, Alignment.MIDDLE_RIGHT);
    }

    public abstract void displayAfterMoveSuccess(Folder folder, boolean checking);

    private void displayFiles() {
//        folderTree.addItem(baseFolder);
//        folderTree.setItemCaption(baseFolder, UserUIContext.getMessage(FileI18nEnum.OPT_MY_DOCUMENTS));
//        folderTree.expandItem(baseFolder);
    }
}
