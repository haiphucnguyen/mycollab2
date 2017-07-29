package com.mycollab.test;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public class TestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TestException(Throwable e) {
        super(e);
    }
}
