package com.esofthead.mycollab.module.ecm;

import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.NodeType;
import javax.jcr.nodetype.NodeTypeManager;
import javax.jcr.nodetype.NodeTypeTemplate;
import javax.jcr.nodetype.PropertyDefinitionTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.extensions.jcr.JcrSessionFactory;

public class MyCollabJcrSessionFactory extends JcrSessionFactory {

	private static Logger log = LoggerFactory
			.getLogger(MyCollabJcrSessionFactory.class);

	@Override
	protected void registerNodeTypes() throws Exception {
		final String[] jcrNamespaces = getSession().getWorkspace()
				.getNamespaceRegistry().getPrefixes();
		boolean createNamespace = true;
		for (int i = 0; i < jcrNamespaces.length; i++) {
			if (jcrNamespaces[i].equals("mycollab")) {
				createNamespace = false;
				log.debug("Jackrabbit OCM namespace exists.");
			}
		}
		if (createNamespace) {
			getSession()
					.getWorkspace()
					.getNamespaceRegistry()
					.registerNamespace("mycollab",
							"http://www.esofthead.com/mycollab");
			log.debug("Successfully created Mycollab content namespace.");
		}
		if (getSession().getRootNode() == null) {
			throw new ContentException("Jcr session setup not successful.");
		}

		NodeTypeManager manager = (NodeTypeManager) getSession().getWorkspace()
				.getNodeTypeManager();

		manager.registerNodeType(createMyCollabContentType(manager), true);
		manager.registerNodeType(createMyCollabFolderType(manager), true);
	}

	private NodeTypeTemplate createMyCollabContentType(NodeTypeManager manager)
			throws NoSuchNodeTypeException, RepositoryException {
		NodeType hierachyNode = manager.getNodeType(NodeType.NT_HIERARCHY_NODE);
		// Create content node type
		NodeTypeTemplate contentTypeTemplate = manager
				.createNodeTypeTemplate(hierachyNode);

		contentTypeTemplate.setAbstract(false);
		contentTypeTemplate.setMixin(false);
		contentTypeTemplate.setName("mycollab:content");
		contentTypeTemplate.setPrimaryItemName("content");
		contentTypeTemplate
				.setDeclaredSuperTypeNames(new String[] { NodeType.NT_HIERARCHY_NODE });
		contentTypeTemplate.setQueryable(true);
		contentTypeTemplate.setOrderableChildNodes(false);
		log.debug("PROERTY {} {}",
				contentTypeTemplate.getDeclaredPropertyDefinitions().length,
				contentTypeTemplate.getDeclaredChildNodeDefinitions().length);

		PropertyDefinitionTemplate createdUserPropertyTemplate = manager
				.createPropertyDefinitionTemplate();
		createdUserPropertyTemplate.setMultiple(false);
		createdUserPropertyTemplate.setName("mycollab:createdUser");
		createdUserPropertyTemplate.setMandatory(true);
		createdUserPropertyTemplate.setRequiredType(PropertyType.STRING);
		contentTypeTemplate.getPropertyDefinitionTemplates().add(
				createdUserPropertyTemplate);

		PropertyDefinitionTemplate lastModifiedUserPropertyTemplate = manager
				.createPropertyDefinitionTemplate();
		lastModifiedUserPropertyTemplate.setMultiple(false);
		lastModifiedUserPropertyTemplate.setName("mycollab:lastModifiedUser");
		lastModifiedUserPropertyTemplate.setMandatory(true);
		lastModifiedUserPropertyTemplate.setRequiredType(PropertyType.STRING);
		contentTypeTemplate.getPropertyDefinitionTemplates().add(
				lastModifiedUserPropertyTemplate);

		PropertyDefinitionTemplate sizePropertyTemplate = manager
				.createPropertyDefinitionTemplate();
		sizePropertyTemplate.setMultiple(false);
		sizePropertyTemplate.setName("mycollab:size");
		sizePropertyTemplate.setMandatory(true);
		sizePropertyTemplate.setRequiredType(PropertyType.DOUBLE);
		contentTypeTemplate.getPropertyDefinitionTemplates().add(
				sizePropertyTemplate);

		return contentTypeTemplate;
	}

	private NodeTypeTemplate createMyCollabFolderType(NodeTypeManager manager)
			throws NoSuchNodeTypeException, RepositoryException {
		// Create content node type
		NodeTypeTemplate contentTypeTemplate = manager.createNodeTypeTemplate();

		contentTypeTemplate.setAbstract(false);
		contentTypeTemplate.setMixin(false);
		contentTypeTemplate.setName("mycollab:folder");
		contentTypeTemplate.setPrimaryItemName("folder");
		contentTypeTemplate
				.setDeclaredSuperTypeNames(new String[] { NodeType.NT_FOLDER });
		contentTypeTemplate.setQueryable(true);
		contentTypeTemplate.setOrderableChildNodes(false);
		// log.debug("PROERTY {} {}",
		// contentTypeTemplate.getDeclaredPropertyDefinitions().length,
		// contentTypeTemplate.getDeclaredChildNodeDefinitions().length);

		PropertyDefinitionTemplate createdPropertyTemplate = manager
				.createPropertyDefinitionTemplate();
		createdPropertyTemplate.setMultiple(false);
		createdPropertyTemplate.setName("mycollab:createdUser");
		createdPropertyTemplate.setMandatory(true);
		createdPropertyTemplate.setRequiredType(PropertyType.STRING);
		contentTypeTemplate.getPropertyDefinitionTemplates().add(
				createdPropertyTemplate);
		return contentTypeTemplate;
	}
}
