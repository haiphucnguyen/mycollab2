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
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.server.Page;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */
public class ThemeManager {

    public static void loadUserTheme(int sAccountId) {
        AccountThemeService themeService = ApplicationContextUtil.getSpringBean(AccountThemeService.class);

        AccountTheme accountTheme = themeService.findTheme(sAccountId);

        if (accountTheme == null) {
            accountTheme = themeService.findDefaultTheme(AppContext.getAccountId());
            if (accountTheme == null) {
                throw new UserInvalidInputException("Can not load theme for this account. You may get bad experience " +
                        "while using MyCollab. Please contact your site admoinistrator to solve this issue or fill a " +
                        "support request to MyCollab team" +
                        " ");
            }
        }

		/* Top Menu */

        if (accountTheme.getTopmenubg() != null) {
            Page.getCurrent().getStyles().add(".topNavigation { background-color: #" + accountTheme.getTopmenubg() + "; }");
        }

        if (accountTheme.getTopmenubgselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".topNavigation .serviceMenuContainer .service-menu.v-buttongroup .v-button.selected { background-color: #"
                            + accountTheme.getTopmenubgselected() + "; }");

            Page.getCurrent().getStyles()
                    .add(".topNavigation .serviceMenuContainer .service-menu.v-buttongroup .v-button:hover { background-color: #"
                            + accountTheme.getTopmenubgselected() + "; }");

        }

        if (accountTheme.getTopmenutext() != null) {
            Page.getCurrent().getStyles()
                    .add(".topNavigation .v-button { color: #" + accountTheme.getTopmenutext() + "; }");
        }

        if (accountTheme.getTopmenutextselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".topNavigation .serviceMenuContainer .service-menu.v-buttongroup .v-button.selected { color: #"
                            + accountTheme.getTopmenutextselected() + "; }");

            Page.getCurrent().getStyles()
                    .add(".topNavigation .serviceMenuContainer .service-menu.v-buttongroup .v-button:hover { color: #"
                            + accountTheme.getTopmenutextselected() + "; }");
        }

		/* Vertical Tabsheet */

        if (accountTheme.getVtabsheetbg() != null) {
            Page.getCurrent().getStyles().add(".verticaltabsheet-fix { background-color: #"
                    + accountTheme.getVtabsheetbg() + "; }");

            Page.getCurrent().getStyles().add(".v-progressbar.medium .v-progressbar-indicator { background-color: #"
                    + accountTheme.getVtabsheetbg() + "; }");
        }

        if (accountTheme.getVtabsheetbgselected() != null) {
            Page.getCurrent().getStyles().add(".vertical-tabsheet .v-button-tab.tab-selected > .v-button-wrap { background-color: #"
                    + accountTheme.getVtabsheetbgselected() + "; }");

            Page.getCurrent().getStyles().add(".sidebar-menu .v-button:hover {background-color: #" + accountTheme
                    .getVtabsheetbgselected() + ";}");
        }

        if (accountTheme.getVtabsheettext() != null) {
            Page.getCurrent().getStyles().add(".vertical-tabsheet .v-button-tab > .v-button-wrap { color: #"
                    + accountTheme.getVtabsheettext() + "; }");
        }

        if (accountTheme.getVtabsheettextselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".vertical-tabsheet .v-button-tab.tab-selected > .v-button-wrap { color: #"
                            + accountTheme.getVtabsheettextselected() + "; }");

            //Color while hover on sidebar menu
            Page.getCurrent().getStyles().add(".vertical-tabsheet .v-button-tab .v-button-wrap:hover {color: #" + accountTheme
                    .getVtabsheettextselected() + "!important;}");
        }

		/* Tabsheet */

        if (accountTheme.getTabsheetbg() != null) {
            Page.getCurrent().getStyles()
                    .add(".h-sidebar-menu .v-button.v-button-link.isSelected { background-color: #"
                            + accountTheme.getTabsheetbg() + "; }");

            Page.getCurrent().getStyles()
                    .add(".h-sidebar-menu .v-button.v-button-link:hover { background-color: #"
                            + accountTheme.getTabsheetbg() + "; }");

            Page.getCurrent().getStyles()
                    .add(".tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell > .v-tabsheet-tabitem { background-color: #"
                            + accountTheme.getTabsheetbg() + "; }");
        }

        if (accountTheme.getTabsheetbgselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".h-sidebar-menu, .projectfeed-hdr-wrapper { background-color: #"
                            + accountTheme.getTabsheetbgselected() + "; }");

            //Set style of popup content
            Page.getCurrent().getStyles().add(".optionPopupContent .action-wrap .v-button-action { color: #" + accountTheme
                    .getTabsheetbgselected() + "; }");

            Page.getCurrent().getStyles().add(".helpPanel .v-sliderpanel-wrapper.layout-vertical .v-sliderpanel-tab, " +
                    ".helpPanel .v-sliderpanel-wrapper.layout-vertical .v-sliderpanel-content" +
                    " { background-color: #" + accountTheme.getTabsheetbgselected() + "; }");


            //Set link style
            Page.getCurrent().getStyles().add(".v-app a { color: #" + accountTheme.getTabsheetbgselected() + "; }");

            // Set button link
            Page.getCurrent().getStyles().add(".v-button.v-button-link { color: #" + accountTheme
                    .getTabsheetbgselected() + "; }");

            Page.getCurrent().getStyles()
                    .add(".tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell.v-tabsheet-tabitemcell-selected > .v-tabsheet-tabitem { " +
                            "background-color: #" + accountTheme.getTabsheetbgselected() + "; }");

            Page.getCurrent().getStyles()
                    .add(".tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > " +
                            ".v-tabsheet-tabitemcell > .v-tabsheet-tabitem:hover {background-color: #" +
                            accountTheme.getTabsheetbgselected() + ";}");

            //Set milestone header background
            Page.getCurrent().getStyles().add(".milestone-view-header {background-color: #" + accountTheme.getTabsheetbgselected() + ";}");
        }

        if (accountTheme.getTabsheettext() != null) {
            Page.getCurrent().getStyles()
                    .add(".h-sidebar-menu .v-button.v-button-link.isSelected { color: #" + accountTheme.getTabsheettext() + "; }");

            Page.getCurrent().getStyles()
                    .add(".h-sidebar-menu .v-button.v-button-link:hover .v-button-caption, .h-sidebar-menu .v-button" +
                            ".v-button-link.isSelected .v-button-caption:hover { color: #"
                            + accountTheme.getTabsheettext() + "; }");

            Page.getCurrent().getStyles()
                    .add(".tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell > .v-tabsheet-tabitem { color: #"
                            + accountTheme.getTabsheettext() + "; }");
        }

        if (accountTheme.getTabsheettextselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".h-sidebar-menu .v-button.v-button-link:active, .h-sidebar-menu .v-button.v-button-link { color: #"
                            + accountTheme.getTabsheettextselected() + "; }");

            Page.getCurrent().getStyles()
                    .add(".tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell > .v-tabsheet-tabitem.v-tabsheet-tabitem-selected { color: #"
                            + accountTheme.getTabsheettextselected() + "; }");

            Page.getCurrent().getStyles()
                    .add(".tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > " +
                            ".v-tabsheet-tabitemcell > .v-tabsheet-tabitem:hover {color: #" +
                            accountTheme.getTabsheettextselected() + ";}");

            //Set milestone header text color
            Page.getCurrent().getStyles().add(".milestone-view-header {color: #" + accountTheme.getTabsheettextselected() + ";}");
        }

		/* Action Buttons */

        if (accountTheme.getActionbtn() != null) {
            Page.getCurrent().getStyles()
                    .add(".v-button.v-button-greenbtn, .v-button-greenbtn:focus { background-color: #" + accountTheme.getActionbtn()
                            + "; border-color: #" + getBorderColor(accountTheme.getActionbtn()) + "; }");

            Page.getCurrent().getStyles().add(".crm-toolbar .quickadd-btn .v-button-caption:before { " +
                    "background-color: #" + accountTheme.getActionbtn() + ";}");

            Page.getCurrent().getStyles().add(".v-button.add-project-btn {background-color: #" + accountTheme
                    .getActionbtn() + ";}");

            Page.getCurrent().getStyles().add(".upload-field .v-upload-immediate .v-button {background-color: #" +
                    accountTheme.getActionbtn() + ";}");

            Page.getCurrent().getStyles().add(".optionPopupContent .action-wrap:hover {" +
                    "background-color: #" + accountTheme.getActionbtn() + "};");

            Page.getCurrent().getStyles()
                    .add(".v-buttongroup.toggle-btn-group .v-button.active { background-color: #" + accountTheme.getActionbtn()
                            + "; border-color: #" + getBorderColor(accountTheme.getActionbtn()) + "; }");

            //Button paging
            Page.getCurrent().getStyles().add("div.v-button-link.buttonPaging.current, div.v-button-link" +
                    ".buttonPaging:hover { background-color:#" + accountTheme.getActionbtn() + "; }");

            //Selection background of selected item
            Page.getCurrent().getStyles().add(".v-filterselect-suggestpopup .gwt-MenuItem-selected { background-color:#" +
                    accountTheme.getActionbtn() + "; }");

            //Date label of activity stream
            Page.getCurrent().getStyles().add(".feed-block-wrap .date-lbl { background-color:#" + accountTheme
                    .getActionbtn() + ";}");

            Page.getCurrent().getStyles().add(".feed-block-wrap .date-lbl::after{ border-left-color:#" + accountTheme
                    .getActionbtn() + ";}");
        }

        if (accountTheme.getActionbtntext() != null) {
            Page.getCurrent().getStyles().add(".v-button.v-button-greenbtn, .v-button-greenbtn:focus { color: #"
                    + accountTheme.getActionbtntext() + "; }");

            Page.getCurrent().getStyles().add(".optionPopupContent .action-wrap .v-button-action .v-button-wrap:hover" +
                    " {" + "color: #" + accountTheme.getActionbtntext() + "};");

            //Button paging
            Page.getCurrent().getStyles().add("div.v-button-link.buttonPaging.current, div.v-button-link" +
                    ".buttonPaging:hover { color:#" + accountTheme.getActionbtntext() + "; }");

            //Selection text color of selected item
            Page.getCurrent().getStyles().add(".v-filterselect-suggestpopup .gwt-MenuItem-selected { color:#" +
                    accountTheme.getActionbtntext() + "; }");

            //Date label of activity stream
            Page.getCurrent().getStyles().add(".feed-block-wrap .date-lbl { color:#" + accountTheme
                    .getActionbtntext() + ";}");
        }

		/* Option Buttons */

        if (accountTheme.getOptionbtn() != null) {
            Page.getCurrent().getStyles().add(".v-button.v-button-graybtn, .v-button-graybtn:focus { background-color: #"
                    + accountTheme.getOptionbtn() + "; border-color: #" + getBorderColor(accountTheme.getOptionbtn())
                    + "; }");

            //Set toogle button group background
            Page.getCurrent().getStyles().add(".v-buttongroup.toggle-btn-group .v-button { background-color: #" +
                    accountTheme.getOptionbtn() + "; border-color: #" + getBorderColor(accountTheme.getOptionbtn()) + ";}");
        }

        if (accountTheme.getOptionbtntext() != null) {
            Page.getCurrent().getStyles()
                    .add(".v-button.v-button-graybtn, .v-button-graybtn:focus { color: #" + accountTheme.getOptionbtntext() + "; }");

            Page.getCurrent().getStyles()
                    .add(".v-buttongroup.toggle-btn-group .v-button { color: #"
                            + accountTheme.getOptionbtntext() + "; }");
        }

		/* Danger Buttons */

        if (accountTheme.getDangerbtn() != null) {
            Page.getCurrent().getStyles()
                    .add(".v-button.v-button-redbtn, .v-button-redbtn:focus { background-color: #"
                            + accountTheme.getDangerbtn() + "; border-color: #"
                            + getBorderColor(accountTheme.getDangerbtn()) + "; }");
        }

        if (accountTheme.getDangerbtntext() != null) {
            Page.getCurrent().getStyles()
                    .add(".v-button.v-button-redbtn, .v-button-redbtn:focus { color: #"
                            + accountTheme.getDangerbtntext() + "; }");
        }

    }

    public static void loadDemoTheme(AccountTheme accountTheme) {

		/* Top Menu */

        if (accountTheme.getTopmenubg() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .topNavigation { background-color: #"
                            + accountTheme.getTopmenubg() + "; }");
        }

        if (accountTheme.getTopmenubgselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .topNavigation .service-menu.v-buttongroup .v-button.selected { background-color: #"
                            + accountTheme.getTopmenubgselected() + "; }");
        }

        if (accountTheme.getTopmenutext() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .topNavigation .v-button-caption { color: #"
                            + accountTheme.getTopmenutext() + "; }");
        }

        if (accountTheme.getTopmenutextselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .topNavigation .service-menu.v-buttongroup .v-button.selected .v-button-caption { color: #"
                            + accountTheme.getTopmenutextselected() + "; }");
        }

		/* Vertical Tabsheet */

        if (accountTheme.getVtabsheetbg() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .verticaltabsheet-fix { background-color: #"
                            + accountTheme.getVtabsheetbg() + "; }");
        }

        if (accountTheme.getVtabsheetbgselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .vertical-tabsheet .v-button-tab.tab-selected > .v-button-wrap { background-color: #"
                            + accountTheme.getVtabsheetbgselected() + "; }");
        }

        if (accountTheme.getVtabsheettext() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .vertical-tabsheet .v-button-tab > .v-button-wrap > .v-button-caption { color: #"
                            + accountTheme.getVtabsheettext() + "; }");
        }

        if (accountTheme.getVtabsheettextselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .vertical-tabsheet .v-button-tab.tab-selected > .v-button-wrap > .v-button-caption { color: #"
                            + accountTheme.getVtabsheettextselected() + "; }");
        }

		/* Tabsheet */

        if (accountTheme.getTabsheetbg() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell > .v-tabsheet-tabitem { background-color: #"
                            + accountTheme.getTabsheetbg() + "; }");
        }

        if (accountTheme.getTabsheetbgselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell.v-tabsheet-tabitemcell-selected > .v-tabsheet-tabitem { background-color: #"
                            + accountTheme.getTabsheetbgselected() + "; }");
        }

        if (accountTheme.getTabsheettext() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell > .v-tabsheet-tabitem .v-caption .v-captiontext { color: #"
                            + accountTheme.getTabsheettext() + "; }");
        }

        if (accountTheme.getTabsheettextselected() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .tab-style3 > .v-tabsheet-tabcontainer > .v-tabsheet-tabs > tbody > tr > .v-tabsheet-tabitemcell > .v-tabsheet-tabitem.v-tabsheet-tabitem-selected .v-caption .v-captiontext { color: #"
                            + accountTheme.getTabsheettextselected() + "; }");
        }

		/* Action Buttons */

        if (accountTheme.getActionbtn() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .v-button.v-button-greenbtn, .example-block .v-button-greenbtn:focus { background-color: #"
                            + accountTheme.getActionbtn()
                            + "; border-color: #"
                            + getBorderColor(accountTheme.getActionbtn())
                            + "; }");
        }

        if (accountTheme.getActionbtntext() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .v-button.v-button-greenbtn, .example-block .v-button-greenbtn:focus { color: #"
                            + accountTheme.getActionbtntext() + "; }");
        }

		/* Option Buttons */

        if (accountTheme.getOptionbtn() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .v-button.v-button-graybtn, .example-block .v-button-graybtn:focus { background-color: #"
                            + accountTheme.getOptionbtn()
                            + "; border-color: #"
                            + getBorderColor(accountTheme.getOptionbtn())
                            + "; }");
        }

        if (accountTheme.getOptionbtntext() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .v-button.v-button-graybtn, .example-block .v-button-graybtn:focus { color: #"
                            + accountTheme.getOptionbtntext() + "; }");
        }

		/* Danger Buttons */

        if (accountTheme.getDangerbtn() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .v-button.v-button-redbtn, .example-block .v-button-redbtn:focus { background-color: #"
                            + accountTheme.getDangerbtn()
                            + "; border-color: #"
                            + getBorderColor(accountTheme.getDangerbtn())
                            + "; }");
        }

        if (accountTheme.getDangerbtntext() != null) {
            Page.getCurrent().getStyles()
                    .add(".example-block .v-button.v-button-redbtn, .example-block .v-button-redbtn:focus { color: #"
                            + accountTheme.getDangerbtntext() + "; }");
        }

    }

    private static String getBorderColor(String bgColor) {
        String rgb = "";
        Long c;
        for (int i = 0; i < 3; i++) {
            c = Long.valueOf(bgColor.substring(i * 2, i * 2 + 2), 16);
            c = Math.round(Math.min(Math.max(0, c - c * 0.2), 255));
            String strVal = Long.toHexString(c);
            rgb += ("00" + strVal).substring(strVal.length());
        }

        return rgb;
    }

}
