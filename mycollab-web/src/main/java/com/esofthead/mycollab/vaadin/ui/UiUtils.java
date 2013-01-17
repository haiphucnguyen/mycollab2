package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

public class UiUtils {
    
    public static void addComponent(AbstractOrderedLayout parent,
            Component child, Alignment alignment) {
        parent.addComponent(child);
        parent.setComponentAlignment(child, alignment);
    }
    
    public static ThemeResource getFileIconResource(String fileName) {
        String themeLink = "";
        
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            String suffix = fileName.substring(index + 1, fileName.length());
            
            if ("mp3".equalsIgnoreCase(suffix) || "wav".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/audio.png";
            } else if ("bmp".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/bmp.png";
            } else if ("xls".equalsIgnoreCase(suffix) || "xlsx".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/excel.png";
            } else if ("gz".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/gz.png";
            } else if ("html".equalsIgnoreCase(suffix) || "xhtml".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/html.png";
            } else if ("jpg".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/jpg.png";
            } else if ("png".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/png.png";
            } else if ("pdf".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/pdf.png";
            } else if ("ppt".equalsIgnoreCase(suffix) || "pptx".equalsIgnoreCase(suffix) || "pps".equalsIgnoreCase(suffix) || "pot".equalsIgnoreCase(suffix) || "pptm".equalsIgnoreCase(suffix) || "potx".equalsIgnoreCase(suffix) || "potm".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/ppt.png";
            } else if ("psd".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/psd.png";
            } else if ("rar".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/rar.png";
            } else if ("svg".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/svg.png";
            } else if ("tar".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/tar.png";
            } else if ("tiff".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/tiff.png";
            } else if ("txt".equalsIgnoreCase(suffix) || "rtf".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/txt.png";
            } else if ("doc".equalsIgnoreCase(suffix) || "docx".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/word.png";
            } else if ("zip".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/zip.png";
            } else if ("odt".equalsIgnoreCase(suffix) || "odg".equalsIgnoreCase(suffix) || "otp".equalsIgnoreCase(suffix)) {
                themeLink = "icons/22/filetypes/document.png";
            } else {
                themeLink = "icons/22/filetypes/blank.png";
            }
        } else {
            themeLink = "icons/22/filetypes/blank.png";
        }
        
        return new ThemeResource(themeLink);
    }
}
