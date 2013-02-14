/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.schedule.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.schedule.MyCollabScheduleService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class MyCollabScheduleServiceImpl implements MyCollabScheduleService {

	@Override
	@Scheduled(fixedDelay = 10000)
	public void doSomething() {

	}
}
