package com.esofthead.mycollab.module.user.service;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;

import com.esofthead.mycollab.core.persistence.service.IService;

@RemotingDestination(channels = { "mycollab-amf", "mycollab-secure-amf" })
public interface AvatarService extends IService {
    @RemotingInclude
    public String getUserAvatarUrl(String username);
}
