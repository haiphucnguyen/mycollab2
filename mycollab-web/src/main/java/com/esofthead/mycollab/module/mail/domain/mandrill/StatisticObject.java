package com.esofthead.mycollab.module.mail.domain.mandrill;

public abstract class StatisticObject {
	public abstract int get_sent();
	public abstract int get_hard_bounces();
	public abstract int get_soft_bounces();
	public abstract int get_rejects();
	public abstract int get_complaints();
	public abstract int get_unsubs();
	public abstract int get_opens();
	public abstract int get_unique_opens();
	public abstract int get_clicks();
	public abstract int get_unique_clicks();
}
