package org.vaadin.peter.buttongroup;

import java.util.LinkedList;
import java.util.Map;

import org.vaadin.peter.buttongroup.client.ui.VButtonGroup;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ClientWidget;

/**
 * ButtonGroup is used to group similar buttons together. When buttons are
 * grouped using ButtonGroup their layout will change so that the buttons are
 * close to each other without having rounded corners in the middle buttons.
 * 
 * ButtonGroup supports all size of button groups from one button group to
 * multiple buttons.
 * 
 * @author Peter Lehto / Vaadin Oy
 */
@ClientWidget(VButtonGroup.class)
public class ButtonGroup extends AbstractComponent {

	private static final long serialVersionUID = 8638442408634664677L;

	private final LinkedList<Button> buttons;

	private static String leftButtonCss = "left";
	private static String middleButtonCss = "middle";
	private static String rightButtonCss = "right";

	/**
	 * Creates empty ButtonGroup
	 */
	public ButtonGroup() {
		buttons = new LinkedList<Button>();
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);

		updateButtonCssClasses();

		target.startTag("buttons");

		for (Button button : buttons) {
			button.paint(target);
		}

		target.endTag("buttons");
	}

	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {
		super.changeVariables(source, variables);
	}

	/**
	 * Adds given button to this button group. Group will be filled from left to
	 * right.
	 * 
	 * @param button
	 * @return given button
	 * @throws IllegalArgumentException
	 *             if given button already has a parent component
	 */
	public Button addButton(Button button) {
		return addButton(button, buttons.size());
	}

	/**
	 * Replaces given oldButton with given newButton. newButton will be
	 * positioned to the same place from where old one was taken away.
	 * 
	 * @param oldButton
	 * @param newButton
	 * @throws IllegalArgumentException
	 *             if given old button does not exist in this group
	 * @throws IllegalArgumentException
	 *             if given newButton already has a parent component
	 */
	public void replaceButton(Button oldButton, Button newButton) {
		if (!buttons.contains(oldButton)) {
			throw new IllegalArgumentException(
					"Given old button does not exist in this group");
		}

		int oldPosition = buttons.indexOf(oldButton);

		if (oldPosition == -1) {
			return;
		}

		buttons.remove(oldButton);
		oldButton.setParent(null);

		addButton(newButton, oldPosition);
	}

	/**
	 * Removes given button from this group.
	 * 
	 * @param button
	 */
	public void removeButton(Button button) {
		if (!buttons.contains(button)) {
			return;
		}

		buttons.remove(button);
		button.setParent(null);

		requestRepaint();
	}

	/**
	 * Removes all buttons from this group
	 */
	public void removeAllButtons() {
		for (Button button : buttons) {
			button.setParent(null);
		}

		buttons.clear();

		requestRepaint();
	}

	private Button addButton(Button button, int position) {
		if (button.getParent() != null) {
			throw new IllegalArgumentException(
					"Given button already has a parent component");
		}

		button.setParent(this);
		buttons.add(position, button);

		requestRepaint();

		return button;
	}

	private void updateButtonCssClasses() {
		removeOldButtonCss();
		addNewButtonCss();
	}

	private void removeOldButtonCss() {
		for (Button button : buttons) {
			button.removeStyleName(leftButtonCss);
			button.removeStyleName(middleButtonCss);
			button.removeStyleName(rightButtonCss);
		}
	}
	
	public void removeButtonsCss(String styleName) {
		for (Button button : buttons) {
			button.removeStyleName(styleName);
		}
	}


	private void addNewButtonCss() {
		if (getNumberOfVisibleButtons() <= 1) {
			return;
		}

		if (getNumberOfVisibleButtons() == 2) {
			Button left = getLeftmostVisibleButton();
			Button right = getRightmostVisibleButton();

			left.addStyleName(leftButtonCss);
			right.addStyleName(rightButtonCss);
			return;
		}

		if (getNumberOfVisibleButtons() > 2) {
			Button left = getLeftmostVisibleButton();
			Button right = getRightmostVisibleButton();

			left.addStyleName(leftButtonCss);
			right.addStyleName(rightButtonCss);

			for (Button button : buttons) {
				if (!button.equals(left) && !button.equals(right)) {
					button.addStyleName(middleButtonCss);
				}
			}
		}
	}

	private Button getLeftmostVisibleButton() {
		for (Button button : buttons) {
			if (button.isVisible()) {
				return button;
			}
		}

		return null;
	}

	private Button getRightmostVisibleButton() {
		for (int i = buttons.size() - 1; i > 0; i--) {
			if (buttons.get(i).isVisible()) {
				return buttons.get(i);
			}
		}

		return null;
	}

	private int getNumberOfVisibleButtons() {
		int visibleButtons = 0;

		for (Button button : buttons) {
			if (button.isVisible()) {
				visibleButtons += 1;
			}
		}

		return visibleButtons;
	}
}
