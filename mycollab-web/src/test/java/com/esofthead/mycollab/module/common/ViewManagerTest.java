package com.esofthead.mycollab.module.common;

import org.junit.Assert;
import org.junit.Test;

import com.esofthead.mycollab.module.common.view.InvalidSomeView;
import com.esofthead.mycollab.module.common.view.SomeView;
import com.esofthead.mycollab.module.common.view.SomeViewImpl;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;

public class ViewManagerTest {

	@Test
	public void testGetViewClassSuccess() {
//		SomeView view = ViewManager.getView(SomeView.class);
//		Assert.assertEquals(SomeViewImpl.class, view.getClass());
	}

	@Test(expected = RuntimeException.class)
	public void testNotFindView() {
		ViewManager.getView(InvalidSomeView.class);
	}
}
