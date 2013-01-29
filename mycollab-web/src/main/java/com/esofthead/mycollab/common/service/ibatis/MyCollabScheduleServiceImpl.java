/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.service.MyCollabScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author haiphucnguyen
 */
@Service
public class MyCollabScheduleServiceImpl implements MyCollabScheduleService{
    
    @Override
    @Scheduled(fixedDelay=10)
    public void doSomething() {
        System.out.print("AAA");
    }
}
