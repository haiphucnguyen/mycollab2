package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class DynaSectionBuilder {

    private DynaSection section;

    public DynaSectionBuilder() {
        section = new DynaSection();
    }

    public DynaSectionBuilder header(String header) {
        section.setHeader(header);
        return this;
    }

    public DynaSectionBuilder layoutType(LayoutType layoutType) {
        section.setLayoutType(layoutType);
        return this;
    }

    public DynaSectionBuilder orderIndex(int orderIndex) {
        section.setOrderIndex(orderIndex);
        return this;
    }

    public DynaSectionBuilder deleteSection(boolean isDeleteSection) {
        section.setDeletedSection(isDeleteSection);
        return this;
    }

    public DynaSectionBuilder fields(AbstractDynaFieldBuilder... fields) {
        for (AbstractDynaFieldBuilder field : fields) {
            section.addField(field.build());
        }
        return this;
    }

    public DynaSection build() {
        return section;
    }
}
