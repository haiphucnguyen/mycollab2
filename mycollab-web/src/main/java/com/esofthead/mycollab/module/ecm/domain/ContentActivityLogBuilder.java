package com.esofthead.mycollab.module.ecm.domain;

import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Create;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Delete;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Move;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Rename;

public class ContentActivityLogBuilder {
	public static ContentActivityLogAction makeCreateFolder(String path) {
		return makeCreate(path, ContentActivityLogAction.FOLDER_TYPE);
	}

	public static ContentActivityLogAction makeCreateContent(String path) {
		return makeCreate(path, ContentActivityLogAction.CONTENT_TYPE);
	}

	private static ContentActivityLogAction makeCreate(String path,
			String createType) {
		Create createAction = new Create();
		createAction.setPath(path);
		createAction.setCreateType(createType);
		return createAction;
	}

	public static ContentActivityLogAction makeMoveFolder(String oldPath,
			String newPath) {
		return makeMove(oldPath, newPath, ContentActivityLogAction.FOLDER_TYPE);
	}

	public static ContentActivityLogAction makeMoveContent(String oldPath,
			String newPath) {
		return makeMove(oldPath, newPath, ContentActivityLogAction.CONTENT_TYPE);
	}

	public static ContentActivityLogAction makeRenameFolder(String oldPath,
			String newPath) {
		return makeRenameFolder(oldPath, newPath,
				ContentActivityLogAction.FOLDER_TYPE);
	}

	public static ContentActivityLogAction makeRenameContent(String oldPath,
			String newPath) {
		return makeRenameFolder(oldPath, newPath,
				ContentActivityLogAction.CONTENT_TYPE);
	}

	private static ContentActivityLogAction makeRenameFolder(String oldPath,
			String newPath, String type) {
		Rename renameAction = new Rename();
		renameAction.setOldPath(oldPath);
		renameAction.setNewPath(newPath);
		renameAction.setResourceType(type);
		return renameAction;
	}

	private static ContentActivityLogAction makeMove(String oldPath,
			String newPath, String moveType) {
		Move moveAction = new Move();
		moveAction.setOldPath(oldPath);
		moveAction.setNewPath(newPath);
		moveAction.setMoveType(moveType);
		return moveAction;
	}

	public static ContentActivityLogAction makeDeleteFolder(String path) {
		return makeDelete(path, ContentActivityLogAction.FOLDER_TYPE);
	}

	public static ContentActivityLogAction makeDeleteContent(String path) {
		return makeDelete(path, ContentActivityLogAction.CONTENT_TYPE);
	}

	private static ContentActivityLogAction makeDelete(String path,
			String deleteType) {
		Delete deleteAction = new Delete();
		deleteAction.setDeleteType(deleteType);
		deleteAction.setPath(path);
		return deleteAction;
	}
}
