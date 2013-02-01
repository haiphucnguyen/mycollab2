package com.esofthead.mycollab.module.mail.domain.mandrill;

public abstract class StatisticsResponse {
	public abstract StatisticObject getStatisticObject();
	public abstract StatisticObject getTodayStatisticObject();
	public abstract StatisticObject get7dayStatisticObject();
	public abstract StatisticObject get30dayStatisticObject();
	public abstract StatisticObject get60dayStatisticObject();
	public abstract StatisticObject get90dayStatisticObject();
}
