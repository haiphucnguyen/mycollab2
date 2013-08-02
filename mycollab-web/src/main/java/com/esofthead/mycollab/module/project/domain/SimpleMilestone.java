/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

/**
 * 
 * @author haiphucnguyen
 */
public class SimpleMilestone extends Milestone {
	private static final long serialVersionUID = 1L;

	public static final String STATUS_INPROGRESS = "In Progress";
	public static final String STATUS_FUTURE = "Future";
	public static final String STATUS_CLOSE = "Closed";

	private String ownerAvatarId;
	private String ownerFullName;

	private int numOpenTasks;
	private int numTasks;

	private int numOpenBugs;
	private int numBugs;

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public int getNumOpenTasks() {
		return numOpenTasks;
	}

	public void setNumOpenTasks(int numOpenTasks) {
		this.numOpenTasks = numOpenTasks;
	}

	public int getNumTasks() {
		return numTasks;
	}

	public void setNumTasks(int numTasks) {
		this.numTasks = numTasks;
	}

	public int getNumOpenBugs() {
		return numOpenBugs;
	}

	public void setNumOpenBugs(int numOpenBugs) {
		this.numOpenBugs = numOpenBugs;
	}

	public int getNumBugs() {
		return numBugs;
	}

	public void setNumBugs(int numBugs) {
		this.numBugs = numBugs;
	}

	public String getOwnerAvatarId() {
		return ownerAvatarId;
	}

	public void setOwnerAvatarId(String ownerAvatarId) {
		this.ownerAvatarId = ownerAvatarId;
	}
}
