package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.mobile.ui.I18nValueComboBox;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugResolution;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class BugResolutionComboBox extends I18nValueComboBox {
	private static final long serialVersionUID = 1L;

	private BugResolutionComboBox(boolean nullIsAllowable, Enum<?>... values) {
		super(nullIsAllowable, values);
	}

	public static BugResolutionComboBox getInstanceForWontFixWindow() {
		return new BugResolutionComboBox(false, BugResolution.CannotReproduce,
				BugResolution.Duplicate, BugResolution.Incomplete,
				BugResolution.Won_Fix);
	}

	public static BugResolutionComboBox getInstanceForValidBugWindow() {
		return new BugResolutionComboBox(false, BugResolution.Newissue,
				BugResolution.ReOpen, BugResolution.WaitforVerification);
	}

	public static BugResolutionComboBox getInstanceForResolvedBugWindow() {
		return new BugResolutionComboBox(false, BugResolution.Fixed);
	}
}
