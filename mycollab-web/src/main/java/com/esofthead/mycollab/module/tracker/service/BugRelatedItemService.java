/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.tracker.service;

import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public interface BugRelatedItemService {
    void saveAffectedVersionsOfBug(int bugid, List<Version> versions);
    
    void saveFixedVersionsOfBug(int bugid, List<Version> versions);
    
    void saveComponentsOfBug(int bugid, List<Component> components);
    
    void updateAfftedVersionsOfBug(int bugid, List<Version> versions);
    
    void updateFixedVersionsOfBug(int bugid, List<Version> versions);
    
    void updateComponentsOfBug(int bugid, List<Component> components);
}
