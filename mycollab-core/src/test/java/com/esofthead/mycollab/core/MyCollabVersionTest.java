package com.esofthead.mycollab.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
public class MyCollabVersionTest {
    @Test
    public void testHigherVersion() {
        Assert.assertFalse(MyCollabVersion.isEditionNewer("5.0.5", "5.0.6"));
        Assert.assertFalse(MyCollabVersion.isEditionNewer("5.0.5", "5.0.5"));
        Assert.assertTrue(MyCollabVersion.isEditionNewer("5.0.5", "5.0.4"));
        Assert.assertTrue(MyCollabVersion.isEditionNewer("6.0.0", "5.0.4"));
    }
}
