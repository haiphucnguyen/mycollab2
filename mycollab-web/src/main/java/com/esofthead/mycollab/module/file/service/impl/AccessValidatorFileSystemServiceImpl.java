package com.esofthead.mycollab.module.file.service.impl;

import java.util.Collection;

import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.ContentSearchResult;
import com.esofthead.mycollab.module.file.domain.FileItem;
import com.esofthead.mycollab.module.file.domain.Folder;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;
import com.esofthead.mycollab.module.file.domain.SimpleFile;
import com.esofthead.mycollab.module.file.domain.criteria.ContentSearchCriteria;
import com.esofthead.mycollab.module.file.service.AccessValidatorFileSystemService;
import com.esofthead.mycollab.module.file.validator.AccessValidator;

public class AccessValidatorFileSystemServiceImpl implements
		AccessValidatorFileSystemService {

	@Override
	public void save(Content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Content> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Content findByPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Content> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithAccessValidator(AccessValidator accessValidator,
			Content content, boolean isTrack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWithAccessValidator(AccessValidator accessValidator,
			Content content, boolean isTrack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWithAccessValidator(AccessValidator accessValidator,
			Content content, boolean isTrack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<ContentSearchResult> findByCriteria(String userSessionId,
			ContentSearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileItem getFileByVersion(AccessValidator accessValidator,
			Content content, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Content> listContents(AccessValidator accessValidator,
			Folder folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateFileMetaData(AccessValidator accessValidator,
			SimpleFile file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePermissions(AccessValidator accessValidator, Folder folder,
			Collection<IdentityPermission> permissions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Content> getSubContents(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
