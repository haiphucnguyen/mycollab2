/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.tracker.service;

import java.util.List;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;

/**
 * 
 * @author haiphucnguyen
 */
@RemotingDestination
public interface BugRelatedItemService extends IService {
	
	void saveAffectedVersionsOfBug(int bugid, List<Version> versions);

	void saveFixedVersionsOfBug(int bugid, List<Version> versions);

	void saveComponentsOfBug(int bugid, List<Component> components);

	void updateAfftedVersionsOfBug(int bugid, List<Version> versions);

	void updateFixedVersionsOfBug(int bugid, List<Version> versions);

	void updateComponentsOfBug(int bugid, List<Component> components);
}
