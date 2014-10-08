package com.esofthead.mycollab.installer;

import java.io.File;
import java.io.IOException;

import com.izforge.izpack.gui.IzPanelLayout;
import com.izforge.izpack.gui.LabelFactory;
import com.izforge.izpack.installer.InstallData;
import com.izforge.izpack.installer.InstallerFrame;
import com.izforge.izpack.installer.IzPanel;
import com.izforge.izpack.installer.ResourceManager;
import com.izforge.izpack.installer.ResourceNotFoundException;
import com.izforge.izpack.util.Log;
import com.izforge.izpack.util.VariableSubstitutor;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class FinishPanelMessage extends IzPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * The variables substitutor.
	 */
	private VariableSubstitutor vs;

	public FinishPanelMessage(InstallerFrame arg0, InstallData arg1) {
		super(arg0, arg1, new IzPanelLayout());
	}

	/**
	 * Indicates wether the panel has been validated or not.
	 *
	 * @return true if the panel has been validated.
	 */
	public boolean isValidated() {
		return true;
	}

	/**
	 * Called when the panel becomes active.
	 */
	public void panelActivate() {
		parent.lockNextButton();
		parent.lockPrevButton();
		parent.setQuitButtonText(parent.langpack.getString("FinishPanel.done"));
		parent.setQuitButtonIcon("done");
		if (idata.installSuccess) {

			// We set the information
			add(LabelFactory.create(parent.icons.getImageIcon("check")));
			add(IzPanelLayout.createVerticalStrut(5));
			add(LabelFactory.create(
					parent.langpack.getString("FinishPanel.success"),
					parent.icons.getImageIcon("preferences"), LEADING),
					NEXT_LINE);
			add(IzPanelLayout.createVerticalStrut(5));
			if (idata.uninstallOutJar != null) {
				// We prepare a message for the uninstaller feature
				String path = translatePath("$INSTALL_PATH") + File.separator
						+ "Uninstaller";

				add(LabelFactory.create(
						parent.langpack.getString("FinishPanel.uninst.info"),
						parent.icons.getImageIcon("preferences"), LEADING),
						NEXT_LINE);
				add(LabelFactory.create(path,
						parent.icons.getImageIcon("empty"), LEADING), NEXT_LINE);
			}
			add(LabelFactory.create("", LEADING), NEXT_LINE);

			String message = null;
			try {
				message = ResourceManager.getInstance().getTextResource(
						"FinishPanelMessage.message");
			} catch (ResourceNotFoundException ignore) {
			} catch (IOException ignore) {
			}
			if (message != null) {
				add(LabelFactory.create(" "), NEXT_LINE);
				add(LabelFactory.create(" "), NEXT_LINE);
				add(LabelFactory.create(message), NEXT_LINE);
			}
		} else {
			add(LabelFactory.create(
					parent.langpack.getString("FinishPanel.fail"),
					parent.icons.getImageIcon("stop"), LEADING));
		}
		getLayoutHelper().completeLayout(); // Call, or call not?
		Log.getInstance().informUser();
	}

	/**
	 * Translates a relative path to a local system path.
	 *
	 * @param destination
	 *            The path to translate.
	 * @return The translated path.
	 */
	private String translatePath(String destination) {
		// Parse for variables
		destination = vs.substitute(destination, null);

		// Convert the file separator characters
		return destination.replace('/', File.separatorChar);
	}
}
