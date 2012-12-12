package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.tracker.RelatedItemConstants;
import com.esofthead.mycollab.module.tracker.dao.BugMapper;
import com.esofthead.mycollab.module.tracker.dao.BugMapperExt;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapperExt;
import com.esofthead.mycollab.module.tracker.dao.HistoryMapper;
import com.esofthead.mycollab.module.tracker.dao.MetaDataMapper;
import com.esofthead.mycollab.module.tracker.dao.RelatedItemMapper;
import com.esofthead.mycollab.module.tracker.dao.VersionMapperExt;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.DefectComment;
import com.esofthead.mycollab.module.tracker.domain.History;
import com.esofthead.mycollab.module.tracker.domain.HistoryExample;
import com.esofthead.mycollab.module.tracker.domain.MetaData;
import com.esofthead.mycollab.module.tracker.domain.MetaDataExample;
import com.esofthead.mycollab.module.tracker.domain.MetaField;
import com.esofthead.mycollab.module.tracker.domain.MetaOptionField;
import com.esofthead.mycollab.module.tracker.domain.RelatedItem;
import com.esofthead.mycollab.module.tracker.domain.RelatedItemExample;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

@Service
public class BugServiceImpl extends DefaultService<Integer, Bug, BugSearchCriteria> implements
		BugService {
	
	@Autowired
	private BugMapper bugMapper;

	@Autowired
	private BugMapperExt bugMapperExt;

	@Autowired
	private ComponentMapperExt componentMapperExt;

	@Autowired
	private HistoryMapper historyMapper;

	@Autowired
	private MetaDataMapper metaDataMapper;

	@Autowired
	private RelatedItemMapper relatedItemMapper;

	@Autowired
	private VersionMapperExt versionMapperExt;

	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private MonitorItemService monitorItemService;

	@Autowired
	private ChangeLogService changeLogService;

	@Override
	public ICrudGenericDAO<Integer, Bug> getCrudMapper() {
		return bugMapper;
	}

	@Override
	public ISearchableDAO<BugSearchCriteria> getSearchMapper() {
		return bugMapperExt;
	}

	@Override
	protected int internalRemoveWithSession(Integer primaryKey, String username) {
		Bug bug = findByPrimaryKey(primaryKey);
		changeLogService.saveChangeLog(bug.getProjectid(), username,
				ChangeLogSource.DEFECT, primaryKey, ChangeLogAction.DELETE,
				bug.getSummary());

		RelatedItemExample ex = new RelatedItemExample();
		ex.createCriteria().andRefitemkeyEqualTo("bug-" + primaryKey);
		relatedItemMapper.deleteByExample(ex);

		// remove bug's attachments
		String attachmentid = "defect-" + primaryKey;
		attachmentService.removeById(attachmentid);

		// notify watchers
		String bugid = "defect-" + primaryKey;

		monitorItemService.deleteWatchingItems(bugid);

		return super.remove(primaryKey);
	}

	@Override
	public int updateBug(String username, Bug bug, DefectComment comment) {
		changeLogService.saveChangeLog(bug.getProjectid(), username,
				ChangeLogSource.DEFECT, bug.getId(), ChangeLogAction.UPDATE,
				bug.getSummary());
		bug.setUpdateddate(new GregorianCalendar().getTime());
		super.updateWithSession(bug, null);
		return 0;
	}

	@Override
	public int updateBugExt(String username, final SimpleBug record,
			final DefectComment comment) {
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.DEFECT, record.getId(), ChangeLogAction.UPDATE,
				record.getSummary());

		SimpleBug oldValue = this.getBugById(record.getId());

		String refid = "bug-" + record.getId();
		auditLogService.saveAuditLog(username, refid, (Object) oldValue,
				(Object) record);

		RelatedItemExample ex = new RelatedItemExample();
		String refkey = "bug-" + record.getId();
		ex.createCriteria().andRefitemkeyEqualTo("bug-" + record.getId());
		relatedItemMapper.deleteByExample(ex);

		saveBugRelatedItems(record, refkey);

		record.setUpdateddate(new GregorianCalendar().getTime());

		if (record.getStatus() == "Resolved") {
			record.setResolveddate(new GregorianCalendar().getTime());
		}

		return super.updateWithSession(record, null);
	}

	@Override
	public List<History> getHistoriesOfBug(int bugid) {
		HistoryExample ex = new HistoryExample();
		ex.createCriteria().andRelatedbugEqualTo(bugid);
		return historyMapper.selectByExample(ex);
	}

	@Override
	public List<MetaField> getProjectTrackerMetaData(int projectid) {
		MetaDataExample ex = new MetaDataExample();
		ex.createCriteria().andProjectidEqualTo(projectid);
		List<MetaData> metadata = metaDataMapper.selectByExampleWithBLOBs(ex);
		if (metadata == null || metadata.size() == 0) {
			return null;
		} else {
			try {
				return constructProjectMetaData(new InputSource(
						new StringReader(metadata.get(0).getXmlstring())));
			} catch (Exception e) {
				return null;
			}
		}
	}

	private List<MetaField> constructProjectMetaData(InputSource docSource) {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			builderFactory.setIgnoringComments(true);
			builderFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder documentBuilder = builderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(docSource);

			return getFieldsFromDoc(document);
		} catch (Exception e) {
			throw new EngroupException("Error while constructing bug workflow.");
		}
	}

	private List<MetaField> getFieldsFromDoc(Document document) {
		List<MetaField> fields = new ArrayList<MetaField>();
		NodeList elements = document.getElementsByTagName("fields");
		if (elements.getLength() > 0) {
			NodeList fieldNodes = ((Element) elements.item(0))
					.getElementsByTagName("field");

			for (int i = 0; i < fieldNodes.getLength(); i++) {
				Element fieldnode = (Element) fieldNodes.item(i);

				NodeList optionNodes = fieldnode.getElementsByTagName("option");
				if (optionNodes == null || optionNodes.getLength() == 0) {
					MetaField field = new MetaField();
					field.setLabel(fieldnode.getAttribute("label"));
					field.setName(fieldnode.getAttribute("name"));
					fields.add(field);
				} else {
					MetaOptionField field = new MetaOptionField();
					field.setLabel(fieldnode.getAttribute("label"));
					field.setName(fieldnode.getAttribute("name"));
					for (int j = 0; j < optionNodes.getLength(); j++) {
						Element option = (Element) optionNodes.item(j);
						String value = option.getAttribute("value");
						String label = option.getTextContent();
						field.putOption(label, value);
					}
					fields.add(field);
				}

			}
		}
		return fields;
	}

	private void saveBugRelatedItems(SimpleBug bug, String refkey) {
		if (bug.getAffectedVersions() != null) {
			for (Version version : bug.getAffectedVersions()) {
				RelatedItem relatedItem = new RelatedItem();
				relatedItem.setRefitemkey(refkey);
				relatedItem.setRelateitemid(version.getId());
				relatedItem.setType(RelatedItemConstants.AFFECTED_VERSION);

				relatedItemMapper.insert(relatedItem);
			}
		}

		if (bug.getFixedVersions() != null) {
			for (Version version : bug.getFixedVersions()) {
				RelatedItem relatedItem = new RelatedItem();
				relatedItem.setRefitemkey(refkey);
				relatedItem.setRelateitemid(version.getId());
				relatedItem.setType(RelatedItemConstants.FIXED_VERSION);
				relatedItemMapper.insert(relatedItem);
			}
		}

		if (bug.getComponents() != null) {
			for (Component component : bug.getComponents()) {
				RelatedItem relatedItem = new RelatedItem();
				relatedItem.setRefitemkey(refkey);
				relatedItem.setRelateitemid(component.getId());
				relatedItem.setType(RelatedItemConstants.COMPONENT);
				relatedItemMapper.insert(relatedItem);
			}
		}

	}

	@Override
	public int saveBugExt(SimpleBug bug, String username) {
		bug.setPosteddate(new GregorianCalendar().getTime());

		// Force these fields are null to prevent client application eventually
		// add them
		bug.setUpdateddate(new GregorianCalendar().getTime());
		bug.setResolveddate(null);

		bugMapperExt.insertAndReturnKey(bug);
		int bugid = bug.getId();
		String refkey = "bug-" + bugid;
		saveBugRelatedItems(bug, refkey);

		changeLogService.saveChangeLog(bug.getProjectid(), username,
				ChangeLogSource.DEFECT, bugid, ChangeLogAction.CREATE,
				bug.getSummary());

		// Send mail to all relate ones
		// TODO: switch to schedule send email with Quartz
		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { try { User user =
		 * securityDelegate.findUserByUsername(bug .getAssignuser()); Email
		 * email = new Email(); email.setFrom("noreply@esofthead.com");
		 * email.setTo(new InternetAddress[] { new InternetAddress(
		 * user.getEmail()) });
		 * email.setSubject(String.format("[Bug %d] New %s", bugid,
		 * bug.getSummary()));
		 * 
		 * Map parameters = new HashMap(); parameters.put("bugweblink",
		 * "<TODO: insert bug web link here>"); parameters.put("summary",
		 * bug.getSummary()); parameters.put("project", bug.getProjectname());
		 * parameters.put("severity", bug.getSeverity());
		 * parameters.put("priority", convertPriority(bug.getPriority()));
		 * parameters.put("components", bug.getComponents());
		 * parameters.put("versions", bug.getAffectedVersions());
		 * parameters.put("assignedTo", bug.getAssignuser());
		 * parameters.put("reportedBy", bug.getLogby());
		 * parameters.put("description", bug.getDetail());
		 * parameters.put("reason", "You are the assignee for the bug.");
		 * 
		 * mailSender.sendEmailWithTemplate(email,
		 * "defect/create_new_defect.template", parameters); } catch (Exception
		 * e) { log.error("Error while sending email", e); }
		 * 
		 * } }).start();
		 */
		return bugid;
	}

	private String convertPriority(int priority) {
		String result = "";
		if (priority == 1) {
			result = "Blocker";
		} else if (priority == 2) {
			result = "Critical";
		} else if (priority == 3) {
			result = "Major";
		} else if (priority == 4) {
			result = "Minor";
		} else if (priority == 5) {
			result = "Cosmetic";
		}

		return result;
	}

	@Override
	public SimpleBug getBugById(int bugid) {
		SimpleBug bug = bugMapperExt.getBugById(bugid);

		// get related versions
		String refKey = "bug-" + bug.getId();
		bug.setAffectedVersions(versionMapperExt
				.getAffectedVersionsByRelatedRefKey(refKey));
		bug.setFixedVersions(versionMapperExt
				.getFixedVersionByRelatedRefKey(refKey));

		// get related components
		bug.setComponents(componentMapperExt.getComponentByRefKey(refKey));
		return bug;
	}

	@Override
	public List<GroupItem> getStatusSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getStatusSummary(criteria);
	}

	@Override
	public List<GroupItem> getPrioritySummary(BugSearchCriteria criteria) {
		return bugMapperExt.getPrioritySummary(criteria);
	}

	@Override
	public List<GroupItem> getAssignedDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getAssignedDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getReporterDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getReporterDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getResolutionDefectsSummary(
			BugSearchCriteria criteria) {
		return bugMapperExt.getResolutionDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getComponentDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getComponentDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getVersionDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getVersionDefectsSummary(criteria);
	}
}
