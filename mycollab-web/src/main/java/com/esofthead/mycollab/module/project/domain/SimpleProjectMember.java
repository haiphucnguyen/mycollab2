/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

/**
 *
 * @author haiphucnguyen
 */
public class SimpleProjectMember extends ProjectMember {
    private String memberFullName;

    public String getMemberFullName() {
        return memberFullName;
    }

    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }
}
