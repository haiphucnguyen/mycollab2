package com.esofthead.mycollab.module.ecm.domain;

import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Create;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Move;

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

	private static ContentActivityLogAction makeMove(String oldPath,
			String newPath, String moveType) {
		Move moveAction = new Move();
		moveAction.setOldPath(oldPath);
		moveAction.setNewPath(newPath);
		moveAction.setMoveType(moveType);
		return moveAction;
	}
}
