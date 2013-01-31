package com.esofthead.mycollab.module.mail.service;

public abstract class StatisticsResponse {
	public abstract StatisticObject getStatisticObject();
	public abstract StatisticObject getTodayStatisticObject();
	public abstract StatisticObject get7dayStatisticObject();
	public abstract StatisticObject get30dayStatisticObject();
	public abstract StatisticObject get60dayStatisticObject();
	public abstract StatisticObject get90dayStatisticObject();
}

@SuppressWarnings("unused")
class StatisticsResponseImpl extends StatisticsResponse {

	@Override
	public StatisticObject getStatisticObject() {
		StatisticObjectImpl result = new StatisticObjectImpl();
		result.set_clicks(clicks);
		result.set_complaints(complaints);
		result.set_hard_bounces(hard_bounces);
		result.set_opens(opens);
		result.set_rejects(rejects);
		result.set_sent(sent);
		result.set_soft_bounces(soft_bounces);
		result.set_unsubs(unsubs);
		return result;
	}
	
	private String address;
	private String created_at;
	private int sent;
	private int hard_bounces;
	private int soft_bounces;
	private int rejects;
	private int complaints;
	private int unsubs;
	private int opens;
	private int clicks;
	private StatisticsResponseImpl1 stats;
	
	@Override
	public StatisticObject getTodayStatisticObject() {
		return stats.today;
	}
	@Override
	public StatisticObject get7dayStatisticObject() {
		return stats.last_7_days;
	}
	@Override
	public StatisticObject get30dayStatisticObject() {
		return stats.last_30_days;
	}
	@Override
	public StatisticObject get60dayStatisticObject() {
		return stats.last_60_days;
	}
	@Override
	public StatisticObject get90dayStatisticObject() {
		return stats.last_90_days;
	}
	
}

class StatisticsResponseImpl1 {
	StatisticObjectImpl today;
	StatisticObjectImpl last_7_days;
	StatisticObjectImpl last_30_days;
	StatisticObjectImpl last_60_days;
	StatisticObjectImpl last_90_days;
}

class StatisticObjectImpl extends StatisticObject {

	private int sent;
	private int hard_bounces;
	private int soft_bounces;
	private int rejects;
	private int complaints;
	private int unsubs;
	private int opens;
	private int unique_opens = 0;
	private int clicks;
	private int unique_clicks = 0;
	
	public void set_sent(int value) {
		sent = value;
	}
	public void set_hard_bounces(int value) {
		hard_bounces = value;
	}
	public void set_soft_bounces(int value) {
		soft_bounces = value;
	}
	public void set_rejects(int value) {
		rejects = value;
	}
	public void set_complaints(int value) {
		complaints = value;
	}
	public void set_unsubs(int value) {
		unsubs = value;
	}
	public void set_opens(int value) {
		opens = value;
	}
	public void set_unique_opens(int value) {
		unique_opens = value;
	}
	public void set_clicks(int value) {
		clicks = value;
	}
	public void set_unique_clicks(int value) {
		unique_clicks = value;
	}
	
	
	@Override
	public int get_sent() {
		return sent;
	}

	@Override
	public int get_hard_bounces() {
		return hard_bounces;
	}

	@Override
	public int get_soft_bounces() {
		return soft_bounces;
	}

	@Override
	public int get_rejects() {
		return rejects;
	}

	@Override
	public int get_complaints() {
		return complaints;
	}

	@Override
	public int get_unsubs() {
		return unsubs;
	}

	@Override
	public int get_opens() {
		return opens;
	}

	@Override
	public int get_unique_opens() {
		return unique_opens;
	}

	@Override
	public int get_clicks() {
		return clicks;
	}

	@Override
	public int get_unique_clicks() {
		return unique_clicks;
	}
	
}
