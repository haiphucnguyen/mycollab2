package com.esofthead.mycollab.module.ecm.domain;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class ContentActivityLogAction {

	public static final String FOLDER_TYPE = "folder";

	public static final String CONTENT_TYPE = "content";

	public String toString() {
		XStream xstream = new XStream(new StaxDriver());
		return xstream.toXML(this);
	}

	public static ContentActivityLogAction fromString(String actionDesc) {
		XStream xstream = new XStream(new StaxDriver());
		Object obj = xstream.fromXML(actionDesc);
		if (obj instanceof ContentActivityLogAction) {
			return (ContentActivityLogAction) obj;
		} else {
			return null;
		}
	}

	public static class Move extends ContentActivityLogAction {
		private String oldPath;
		private String newPath;
		private String moveType;

		public String getOldPath() {
			return oldPath;
		}

		public void setOldPath(String oldPath) {
			this.oldPath = oldPath;
		}

		public String getNewPath() {
			return newPath;
		}

		public void setNewPath(String newPath) {
			this.newPath = newPath;
		}

		public String getMoveType() {
			return moveType;
		}

		public void setMoveType(String moveType) {
			this.moveType = moveType;
		}
	}

	public static class Create extends ContentActivityLogAction {
		private String path;
		private String createType;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getCreateType() {
			return createType;
		}

		public void setCreateType(String createType) {
			this.createType = createType;
		}
	}

	public static class Delete extends ContentActivityLogAction {
		private String path;
		private String deleteType;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getDeleteType() {
			return deleteType;
		}

		public void setDeleteType(String deleteType) {
			this.deleteType = deleteType;
		}
	}

	public static class Rename extends ContentActivityLogAction {
		private String oldPath;
		private String newPath;
		private String resourceType;

		public String getOldPath() {
			return oldPath;
		}

		public void setOldPath(String oldPath) {
			this.oldPath = oldPath;
		}

		public String getNewPath() {
			return newPath;
		}

		public void setNewPath(String newPath) {
			this.newPath = newPath;
		}

		public String getResourceType() {
			return resourceType;
		}

		public void setResourceType(String resourceType) {
			this.resourceType = resourceType;
		}

	}
}
