package com.esofthead.mycollab.module.ecm.dao;

import java.io.IOException;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
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

import com.esofthead.mycollab.module.ecm.ContentException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;

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
					if (isNodeFolder(node)) {
						String errorStr = String
								.format("Resource is existed. Search node is not a folder. It has path %s and type is %s",
										node.getPath(), node
												.getPrimaryNodeType().getName());
						throw new ContentException(errorStr);
					} else if (isNodeMyCollabContent(node)) {
						log.debug("Found existing resource. Override");

					} else {
						String errorStr = String
								.format("Resource is existed. But its node type is not mycollab:content. It has path %s and type is %s",
										node.getPath(), node
												.getPrimaryNodeType().getName());
						throw new ContentException(errorStr);
					}
				}

				String[] pathStr = path.split("/");

				Node parentNode = rootNode;
				// create folder note
				for (int i = 0; i < pathStr.length - 1; i++) {
					Node childNode = getNode(parentNode, pathStr[i]);
					if (childNode != null) {
						if (!isNodeFolder(childNode)) {
							throw new ContentException("Invalid path");
						}
					} else {
						childNode = JcrUtils.getOrAddFolder(parentNode,
								pathStr[i]);
						parentNode = childNode;
					}
				}

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

	private static boolean isNodeFolder(Node node) {
		try {
			return node.isNodeType("nt:folder");
		} catch (RepositoryException e) {
			return false;
		}
	}

	private static boolean isNodeMyCollabContent(Node node) {
		try {
			return node.isNodeType("mycollab:content");
		} catch (RepositoryException e) {
			return false;
		}
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
					content.setLastModified(node
							.getProperty("jcr:lastModified").getDate());
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

	@Override
	public List<Resource> getResources(final String path) {
		return jcrTemplate.execute(new JcrCallback<List<Resource>>() {

			@Override
			public List<Resource> doInJcr(Session session) throws IOException,
					RepositoryException {
				Node rootNode = session.getRootNode();
				Node node = getNode(rootNode, path);
				if (node != null) {
					if (node.isNodeType("nt:folder")) {
						Folder folder = new Folder();
						folder.setCreated(node.getProperty("jcr:created")
								.getDate());
						folder.setCreatedBy(node.getProperty("jcr:createdBy")
								.getString());
						NodeIterator childNodes = node.getNodes();

					} else {
						throw new ContentException(
								"Do not support any node type except nt:folder. The current node has type "
										+ node.getPrimaryNodeType().getName());
					}
				}

				return null;
			}
		});
	}

	static interface NodeMapper<T extends Resource> {
		T convertFromNode(Node node);

		void convertToNode(T item);
	}

	static class FolderNodeMapper implements NodeMapper<Folder> {

		@Override
		public Folder convertFromNode(Node node) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void convertToNode(Folder folder) {
			// TODO Auto-generated method stub

		}

	}

	static class ContentNodeMapper implements NodeMapper<Content> {

		@Override
		public Content convertFromNode(Node node) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void convertToNode(Content content) {
			// TODO Auto-generated method stub

		}

	}

}
