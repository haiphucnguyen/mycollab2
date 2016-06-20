package com.esofthead.mycollab.spring;

import com.esofthead.mycollab.schedule.email.MailContext;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@Service
public class FreeMarkerTemplateConfiguration extends FreeMarkerConfigurationFactoryBean {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        Configuration configuration = getObject();
        configuration.setIncompatibleImprovements(Configuration.VERSION_2_3_24);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setObjectWrapper(new ExtObjectWrapper(Configuration.VERSION_2_3_24));
        configuration.setTemplateLoader(new ClassTemplateLoader(getClass().getClassLoader(), ""));
    }

    public static class ExtObjectWrapper extends DefaultObjectWrapper {
        public ExtObjectWrapper(Version incompatibleImprovements) {
            super(incompatibleImprovements);
        }

        @Override
        protected TemplateModel handleUnknownType(final Object obj) throws TemplateModelException {
            if (obj instanceof MailContext) {
                return new MailContextAdapter((MailContext) obj, this);
            }
            return super.handleUnknownType(obj);
        }
    }

    public static class MailContextAdapter extends WrappingTemplateModel implements AdapterTemplateModel,
            TemplateHashModel {
        private MailContext mailContext;

        MailContextAdapter(MailContext mailContext, ObjectWrapper ow) {
            super(ow);
            this.mailContext = mailContext;
        }

        @Override
        public Object getAdaptedObject(Class hint) {
            return mailContext;
        }

        @Override
        public ObjectWrapper getObjectWrapper() {
            return super.getObjectWrapper();
        }

        @Override
        public TemplateModel get(String key) throws TemplateModelException {
            return new TemplateMethodModelEx() {
                @Override
                public Object exec(List arguments) throws TemplateModelException {
                    return null;
                }
            };
        }

        @Override
        public boolean isEmpty() throws TemplateModelException {
            return false;
        }
    }
}
