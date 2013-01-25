/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.tracker.service.ibatis;

import com.esofthead.mycollab.module.tracker.dao.BugRelatedItemMapper;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItem;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItemExample;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (Version version : versions) {
            BugRelatedItem relatedItem = new BugRelatedItem();
            relatedItem.setBugid(bugid);
            relatedItem.setTypeid(version.getId());
            relatedItem.setType("AffVersion");
            bugRelatedItemMapper.insert(relatedItem);
        }
    }

    @Override
    public void saveFixedVersionsOfBug(int bugid, List<Version> versions) {
        for (Version version : versions) {
            BugRelatedItem relatedItem = new BugRelatedItem();
            relatedItem.setBugid(bugid);
            relatedItem.setTypeid(version.getId());
            relatedItem.setType("FixVersion");
            bugRelatedItemMapper.insert(relatedItem);
        }
    }

    @Override
    public void saveComponentsOfBug(int bugid, List<Component> components) {
        for (Component component : components) {
            BugRelatedItem relatedItem = new BugRelatedItem();
            relatedItem.setBugid(bugid);
            relatedItem.setTypeid(component.getId());
            relatedItem.setType("Component");
            bugRelatedItemMapper.insert(relatedItem);
        }
    }

    @Override
    public void updateAfftedVersionsOfBug(int bugid, List<Version> versions) {
        BugRelatedItemExample ex = new BugRelatedItemExample();
        
        List<BugRelatedItem> existingVerions = bugRelatedItemMapper.selectByExample(ex);
    }

    @Override
    public void updateFixedVersionsOfBug(int bugid, List<Version> versions) {
        
    }

    @Override
    public void updateComponentsOfBug(int bugid, List<Component> components) {
        
    }
    
}
