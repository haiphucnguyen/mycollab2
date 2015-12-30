package com.esofthead.mycollab.mobile;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.vaadin.sass.SassCompiler;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class ThemeMobileCompiler {
    public static void main(String args[]) throws Exception {
        SassCompiler.main(new String[]{"src/main/resources/VAADIN/themes/" + MyCollabVersion.THEME_MOBILE_VERSION + "/styles.scss",
                "src/main/resources/VAADIN/themes/" + MyCollabVersion.THEME_MOBILE_VERSION + "/styles.css"});
        Path source = FileSystems.getDefault().getPath("src/main/resources/VAADIN/themes/" + MyCollabVersion.THEME_MOBILE_VERSION + "/", "styles.css");
        Path dest = FileSystems.getDefault().getPath("target/classes/VAADIN/themes/" + MyCollabVersion.THEME_MOBILE_VERSION + "/", "styles.css");
        Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);

    }
}
