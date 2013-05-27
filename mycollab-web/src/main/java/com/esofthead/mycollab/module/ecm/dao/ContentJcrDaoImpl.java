package com.esofthead.mycollab.module.ecm.dao;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;

import org.apache.jackrabbit.commons.JcrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.jcr.JcrCallback;
import org.springframework.extensions.jcr.JcrTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.ecm.ContentException;
import com.esofthead.mycollab.module.ecm.domain.Content;

@Repository
@Transactional(readOnly = true)
public class ContentJcrDaoImpl implements ContentJcrDao {

	private static Logger log = LoggerFactory
			.getLogger(ContentJcrDaoImpl.class);

	@Autowired
	private JcrTemplate jcrTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void saveContent(final Content content, final String path) {
		log.debug("Save content {} {}", content, jcrTemplate);
		jcrTemplate.execute(new JcrCallback() {

			@Override
			public Object doInJcr(Session session) throws IOException,
					RepositoryException {
				Node rootNode = session.getRootNode();
				Node node = getNode(rootNode, path);
				if (node != null) {
					if (!node.isNodeType("nt:folder")) {
						throw new ContentException("Invalid path");
					}
				}

				String[] pathStr = path.split("/");

				Node parentNode = rootNode;
				// create folder note
				for (int i = 0; i < pathStr.length - 1; i++) {
					Node childNode = getNode(parentNode, pathStr[i]);
					if (childNode != null) {
						if (!childNode.isNodeType("nt:folder")) {
							throw new ContentException("Invalid path");
						}
					} else {
						childNode = JcrUtils.getOrAddFolder(parentNode,
								pathStr[i]);
						parentNode = childNode;
					}
				}
				log.debug("SAVE {}-{}", pathStr[pathStr.length - 1],
						parentNode.getPath());

				Node addNode = parentNode.addNode(pathStr[pathStr.length - 1],
						"{http://www.esofthead.com/mycollab}content");
				addNode.addMixin(NodeType.MIX_LAST_MODIFIED);
				addNode.addMixin(NodeType.MIX_TITLE);

				addNode.setProperty("jcr:title", content.getTitle());
				addNode.setProperty("jcr:description", content.getDescription());
				addNode.setProperty("path", content.getPath());
				session.save();

				return null;
			}
		});
	}

	private static Node getNode(Node node, String path) {
		try {
			return node.getNode(path);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Content getContent(final String path) {
		return jcrTemplate.execute(new JcrCallback<Content>() {

			@Override
			public Content doInJcr(Session session) throws IOException,
					RepositoryException {
				Node rootNode = session.getRootNode();
				Node node = getNode(rootNode, path);
				log.debug("NODE {}", node);

				if (node != null) {
					Content content = new Content();
					content.setCreated(node.getProperty("jcr:created")
							.getDate());
					content.setCreatedBy(node.getProperty("jcr:createdBy")
							.getString());
					content.setTitle(node.getProperty("jcr:title").getString());
					content.setDescription(node.getProperty("jcr:description")
							.getString());
					content.setPath(node.getProperty("path").getString());
					log.debug("DATA {}", BeanUtility.printBeanObj(content));
					return content;
				}
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void removeContent(final String path) {
		jcrTemplate.execute(new JcrCallback() {

			@Override
			public Content doInJcr(Session session) throws IOException,
					RepositoryException {
				Node rootNode = session.getRootNode();
				Node node = getNode(rootNode, path);
				if (node != null) {
					node.remove();
					session.save();
				}
				return null;
			}
		});

	}

}
