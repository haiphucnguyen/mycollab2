package com.mycollab.module.project.ui.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.file.PathUtils;
import com.mycollab.module.file.service.EntityUploaderService;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.ImagePreviewCropWindow;
import com.mycollab.vaadin.web.ui.UploadImageField;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.awt.image.BufferedImage;

/**
 * @author MyCollab Ltd
 * @since 5.4.8
 */
public class ProjectLogoUploadWindow extends MWindow implements ImagePreviewCropWindow.ImageSelectionCommand {
    public ProjectLogoUploadWindow(String shortName, Integer projectId, String projectAvatar) {
        super(UserUIContext.getMessage(GenericI18Enum.OPT_UPLOAD_IMAGE));
        withModal(true).withResizable(false).withWidth("200px").withCenter();
        Component projectIcon = ProjectAssetsUtil.projectLogoComp(shortName, projectId, projectAvatar, 100);
        projectIcon.setWidthUndefined();
        final UploadImageField avatarUploadField = new UploadImageField(this);
        withContent(new MVerticalLayout(projectIcon, avatarUploadField)
                .withDefaultComponentAlignment(Alignment.TOP_CENTER));
    }

    @Override
    public void process(BufferedImage image) {
        SimpleProject project = CurrentProjectVariables.getProject();
        EntityUploaderService entityUploaderService = AppContextUtil.getSpringBean(EntityUploaderService.class);
        String newLogoId = entityUploaderService.upload(image, PathUtils.INSTANCE.getProjectLogoPath(AppUI.getAccountId(),
                project.getId()), project.getAvatarid(), UserUIContext.getUsername(), AppUI.getAccountId(),
                new Integer[]{16, 32, 48, 64, 100});
        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        project.setAvatarid(newLogoId);
        projectService.updateSelectiveWithSession(project, UserUIContext.getUsername());
        Page.getCurrent().getJavaScript().execute("window.location.reload();");
    }
}
