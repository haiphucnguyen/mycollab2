package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.common.interceptor.service.Watchable;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.file.service.AttachmentService;
import com.esofthead.mycollab.module.mail.SendingRelayEmailNotificationTemplate;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.tracker.dao.BugMapper;
import com.esofthead.mycollab.module.tracker.dao.BugMapperExt;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapperExt;
import com.esofthead.mycollab.module.tracker.dao.MetaDataMapper;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.MetaData;
import com.esofthead.mycollab.module.tracker.domain.MetaDataExample;
import com.esofthead.mycollab.module.tracker.domain.MetaField;
import com.esofthead.mycollab.module.tracker.domain.MetaOptionField;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.ScheduleConfig;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "summary", type = ProjectContants.BUG, extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.BUG)
@Watchable(type = MonitorTypeConstants.PRJ_BUG, userFieldName = "assignuser")
public class BugServiceImpl extends
		DefaultService<Integer, Bug, BugSearchCriteria> implements BugService {
	@Autowired
	protected BugMapper bugMapper;
	@Autowired
	protected BugMapperExt bugMapperExt;
	@Autowired
	protected ComponentMapperExt componentMapperExt;
	@Autowired
	protected MetaDataMapper metaDataMapper;
	@Autowired
	protected AuditLogService auditLogService;
	@Autowired
	protected AttachmentService attachmentService;
	@Autowired
	protected MonitorItemService monitorItemService;
	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Override
	public ICrudGenericDAO<Integer, Bug> getCrudMapper() {
		return bugMapper;
	}

	@Override
	public ISearchableDAO<BugSearchCriteria> getSearchMapper() {
		return bugMapperExt;
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
			throw new MyCollabException(
					"Error while constructing bug workflow.");
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

	@Override
	public SimpleBug findBugById(int bugId) {
		SimpleBug bug = bugMapperExt.getBugById(bugId);
		return bug;
	}

	@Override
	public List<GroupItem> getBugStatusTrendSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getBugStatusTrendSummary(criteria);
	}

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	@MonitoredWithSpring
	@Override
	public void runNotification() {
		new SendingRelayEmailNotificationTemplate(this)
				.run(MonitorTypeConstants.PRJ_BUG);
	}

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		int taskId = emailNotification.getTypeid();
		SimpleBug bug = this.findBugById(taskId);

		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put("bugUrl", "#");
		hyperLinks.put("projectUrl", "#");
		hyperLinks.put("loggedUserUrl", "#");
		hyperLinks.put("assignUserUrl", "#");
		hyperLinks.put("milestoneUrl", "#");

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$bug.projectname]: New bug created",
				"templates/email/project/bugCreatedNotifier.mt");
		templateGenerator.putVariable("bug", bug);
		templateGenerator.putVariable("hyperLinks", hyperLinks);
		return templateGenerator;
	}
}
