/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.tracker.dao.BugRelatedItemMapper;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItem;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItemExample;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;

/**
 *
 * @author haiphucnguyen
 */
@Service
public class BugRelatedItemServiceImpl implements BugRelatedItemService{
    @Autowired
    private BugRelatedItemMapper bugRelatedItemMapper;

    @Override
    public void saveAffectedVersionsOfBug(int bugid, List<Version> versions) {
    	insertAffectedVersionsOfBug(bugid, versions);
    }
    
    private void insertAffectedVersionsOfBug(int bugid, List<Version> versions) {
    	for (Version version : versions) {
            BugRelatedItem relatedItem = new BugRelatedItem();
            relatedItem.setBugid(bugid);
            relatedItem.setTypeid(version.getId());
            relatedItem.setType(BugSearchCriteria.AFFVERSION);
            bugRelatedItemMapper.insert(relatedItem);
        }
    }

    @Override
    public void saveFixedVersionsOfBug(int bugid, List<Version> versions) {
    	insertFixedVersionsOfBug(bugid, versions);
    }
    
    private void insertFixedVersionsOfBug(int bugid, List<Version> versions) {
        for (Version version : versions) {
            BugRelatedItem relatedItem = new BugRelatedItem();
            relatedItem.setBugid(bugid);
            relatedItem.setTypeid(version.getId());
            relatedItem.setType(BugSearchCriteria.FIXVERSION);
            bugRelatedItemMapper.insert(relatedItem);
        }
    }

    @Override
    public void saveComponentsOfBug(int bugid, List<Component> components) {
    	insertComponentsOfBug(bugid, components);
    }
    
    public void insertComponentsOfBug(int bugid, List<Component> components) {
        for (Component component : components) {
            BugRelatedItem relatedItem = new BugRelatedItem();
            relatedItem.setBugid(bugid);
            relatedItem.setTypeid(component.getId());
            relatedItem.setType(BugSearchCriteria.COMPONENT);
            bugRelatedItemMapper.insert(relatedItem);
        }
    }
    
    private void deleteTrackerBugRelatedItem(int bugid) {
    	BugRelatedItemExample ex = new BugRelatedItemExample();
        ex.createCriteria().andBugidEqualTo(bugid).andTypeEqualTo(BugSearchCriteria.AFFVERSION).andTypeEqualTo(BugSearchCriteria.FIXVERSION).andTypeEqualTo(BugSearchCriteria.COMPONENT);
        
        bugRelatedItemMapper.deleteByExample(ex);
    }

    
    @Override
    public void updateAfftedVersionsOfBug(int bugid, List<Version> versions) {
    	deleteTrackerBugRelatedItem(bugid);
    	insertAffectedVersionsOfBug(bugid, versions);
    }

    @Override
    public void updateFixedVersionsOfBug(int bugid, List<Version> versions) {
    	deleteTrackerBugRelatedItem(bugid);
    	insertFixedVersionsOfBug(bugid, versions);
    }

    @Override
    public void updateComponentsOfBug(int bugid, List<Component> components) {
    	deleteTrackerBugRelatedItem(bugid);
    	insertComponentsOfBug(bugid, components);
    }
    
}
