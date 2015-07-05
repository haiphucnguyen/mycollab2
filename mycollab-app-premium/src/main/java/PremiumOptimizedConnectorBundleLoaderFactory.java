import com.google.gwt.core.ext.typeinfo.JClassType;
import com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory;
import com.vaadin.shared.ui.Connect;

import java.util.HashSet;
import java.util.Set;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class PremiumOptimizedConnectorBundleLoaderFactory extends
        ConnectorBundleLoaderFactory {
    private Set<String> eagerConnectors = new HashSet<String>();

    {
        eagerConnectors.add(com.vaadin.client.ui.ui.UIConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.customlayout.CustomLayoutConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.passwordfield.PasswordFieldConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.textfield.TextFieldConnector.class.getName());
        eagerConnectors.add(org.vaadin.jonatan.contexthelp.widgetset.client.ui.ContextHelpConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.checkbox.CheckBoxConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.customcomponent.CustomComponentConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.csslayout.CssLayoutConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.orderedlayout.VerticalLayoutConnector.class.getName());
        eagerConnectors.add(com.vaadin.client.ui.button.ButtonConnector.class.getName());
    }

    @Override
    protected Connect.LoadStyle getLoadStyle(JClassType connectorType) {
        if (eagerConnectors.contains(connectorType.getQualifiedBinaryName())) {
            return Connect.LoadStyle.EAGER;
        } else {
            // Loads all other connectors immediately after the initial view has
            // been rendered
            return Connect.LoadStyle.LAZY;
        }
    }
}