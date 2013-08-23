package com.esofthead.mycollab.module.project.service;

import org.springframework.flex.remoting.RemotingDestination;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.project.domain.Permission;

@RemotingDestination
public interface PermissionService extends ICrudService<Integer, Permission> {
}
