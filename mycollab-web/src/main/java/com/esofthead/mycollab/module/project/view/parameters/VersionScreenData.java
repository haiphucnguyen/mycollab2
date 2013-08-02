package com.esofthead.mycollab.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class VersionScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
	
	public static class Add extends ScreenData<Version> {

        public Add(Version version) {
            super(version);
        }
    }
    
    public static class Edit extends ScreenData<Version> {

        public Edit(Version version) {
            super(version);
        }
    }
    
    public static class Search extends ScreenData<VersionSearchCriteria> {

        public Search(VersionSearchCriteria criteria) {
            super(criteria);
        }
    }
}
