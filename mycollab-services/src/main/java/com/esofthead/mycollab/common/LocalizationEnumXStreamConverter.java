package com.esofthead.mycollab.common;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class LocalizationEnumXStreamConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
        Enum enumVal = (Enum) o;
        String value = enumVal.name();
        writer.setValue(value);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext unmarshallingContext) {
        try {
            String value = reader.getValue();
            Class enumCls = unmarshallingContext.getRequiredType();
            return Enum.valueOf(enumCls, value);
        } catch (Exception e) {
            return GenericI18Enum.OPT_UNDEFINED;
        }
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.isEnum() || Enum.class.isAssignableFrom(aClass);
    }
}
