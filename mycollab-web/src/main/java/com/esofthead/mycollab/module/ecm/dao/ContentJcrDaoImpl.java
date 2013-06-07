package com.esofthead.mycollab.module.ecm.dao;

import java.io.IOException;
import java.util.ArrayList;
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

import com.esofthead.mycollab.core.MyCollabException;
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
	public void saveContent(final Content content) {
		log.debug("Save content {} {}", content, jcrTemplate);
		jcrTemplate.execute(new JcrCallback() {

			@Override
			public Object doInJcr(Session session) throws IOException,
					RepositoryException {
				Node rootNode = session.getRootNode();
				Node node = getNode(rootNode, content.getPath());
				// forward to current path
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
				} else {
					try {
						String path = content.getPath();
						String[] pathStr = path.split("/");
						Node parentNode = rootNode;
						// create folder note
						for (int i = 0; i < pathStr.length - 1; i++) {
							// move to lastest node of the path
							Node childNode = getNode(parentNode, pathStr[i]);
							if (childNode != null) {
								if (!isNodeFolder(childNode)) {
									// node must is folder
									String errorString = "Invalid path. User want to create a content has path %s but there is a folder has path %s";
									throw new ContentException(String.format(
											errorString, content.getPath(),
											childNode.getPath()));
								}
							} else {
								// add node
								childNode = JcrUtils.getOrAddFolder(parentNode,
										pathStr[i]);
							}
							parentNode = childNode;
						}
						Node addNode = parentNode.addNode(
								pathStr[pathStr.length - 1],
								"{http://www.esofthead.com/mycollab}content");
						addNode.addMixin(NodeType.MIX_LAST_MODIFIED);
						addNode.addMixin(NodeType.MIX_TITLE);

						addNode.setProperty("jcr:title", content.getTitle());
						addNode.setProperty("jcr:description",
								content.getDescription());
						session.save();
					} catch (Exception e) {
						log.debug("error in convertToNode Method"
								+ e.getMessage());
					}
				}
				return null;
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void createFolder(final Folder folder) {
		log.debug("Save content {} {}", folder, jcrTemplate);
		jcrTemplate.execute(new JcrCallback() {

			@Override
			public Object doInJcr(Session session) throws IOException,
					RepositoryException {
				try {
					String path = folder.getPath();
					Node rootNode = session.getRootNode();
					String[] pathStr = path.split("/");
					Node parentNode = rootNode;
					// create folder note
					for (int i = 0; i < pathStr.length; i++) {
						if ("".equals(pathStr[i])) {
							continue;
						}
						// move to lastest node of the path
						Node childNode = getNode(parentNode, pathStr[i]);
						if (childNode != null) {
							log.debug("Found node with path {} in sub node ",
									pathStr[i], parentNode.getPath());
							if (!isNodeFolder(childNode)) {
								// node must be the folder
								String errorString = "Invalid path. User want to create folder has path %s but there is a content has path %s";
								throw new ContentException(String.format(
										errorString, folder.getPath(),
										childNode.getPath()));
							} else {
								log.debug("Found folder node {}",
										childNode.getPath());
							}
						} else { // add node
							log.debug("Create new folder {} of sub node ",
									pathStr[i], parentNode.getPath());
							childNode = JcrUtils.getOrAddFolder(parentNode,
									pathStr[i]);
							session.save();
						}
						
						parentNode = childNode;
					}

					log.debug("Node path {} is existed {}", path,
							(getNode(rootNode, path) != null));
				} catch (Exception e) {
					String errorString = "Error while create folder with path %s";
					throw new MyCollabException(String.format(errorString,
							folder.getPath()), e);
				}
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
	public Resource getResource(final String path) {
		return jcrTemplate.execute(new JcrCallback<Resource>() {
			@Override
			public Resource doInJcr(Session session) throws IOException,
					RepositoryException {
				Node rootNode = session.getRootNode();
				Node node = getNode(rootNode, path);

				if (node != null) {
					if (isNodeMyCollabContent(node)) {
						Content content = new Content();
						content.setCreated(node.getProperty("jcr:created")
								.getDate());
						content.setCreatedBy(node.getProperty("jcr:createdBy")
								.getString());
						content.setTitle(node.getProperty("jcr:title")
								.getString());
						content.setDescription(node.getProperty(
								"jcr:description").getString());
						content.setPath(node.getPath());
						content.setLastModified(node.getProperty(
								"jcr:lastModified").getDate());
						return content;
					} else if (isNodeFolder(node)) {
						return convertNodeToFolder(node);
					} else {
						throw new MyCollabException(
								"Resource does not have type be nt:folder or mycollab:content. Its path is "
										+ node.getPath());
					}
				}
				return null;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void removeResource(final String path) {
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
					if (isNodeFolder(node)) {
						List<Resource> resources = new ArrayList<Resource>();
						NodeIterator childNodes = node.getNodes();
						while (childNodes.hasNext()) {
							Node childNode = childNodes.nextNode();
							if (isNodeFolder(childNode)) {
								Folder subFolder = convertNodeToFolder(node);
								resources.add(subFolder);
							} else if (isNodeMyCollabContent(childNode)) {

							} else {
								String errorString = "Node %s has type not mycollab:content or nt:folder";
								log.error(String.format(errorString,
										childNode.getPath()));
							}
						}

						return resources;
					} else {
						throw new ContentException(
								"Do not support any node type except nt:folder. The current node has type "
										+ node.getPrimaryNodeType().getName());
					}
				}

				log.debug("There is no resource in path {}", path);
				return null;
			}
		});
	}

	@Override
	public List<Folder> getSubFolders(final String path) {
		return jcrTemplate.execute(new JcrCallback<List<Folder>>() {

			@Override
			public List<Folder> doInJcr(Session session) throws IOException,
					RepositoryException {
				Node rootNode = session.getRootNode();
				Node node = getNode(rootNode, path);
				if (node != null) {
					if (node.isNodeType("nt:folder")) {
						List<Folder> folders = new ArrayList<Folder>();
						NodeIterator childNodes = node.getNodes();
						while (childNodes.hasNext()) {
							Node childNode = (Node) childNodes.next();
							if (isNodeFolder(childNode)) {
								Folder subFolder = convertNodeToFolder(childNode);
								folders.add(subFolder);
							}

						}
						return folders;
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

	private static Folder convertNodeToFolder(Node node) {
		try {
			Folder folder = new Folder();
			folder.setCreated(node.getProperty("jcr:created").getDate());
			folder.setCreatedBy(node.getProperty("jcr:createdBy").getString());
			folder.setPath(node.getPath());
			return folder;
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}
}
