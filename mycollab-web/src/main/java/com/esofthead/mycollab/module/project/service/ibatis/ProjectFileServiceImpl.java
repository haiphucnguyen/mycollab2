package com.esofthead.mycollab.module.project.service.ibatis;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.project.service.ProjectFileService;

@Service
public class ProjectFileServiceImpl implements ProjectFileService {
	public static void main(String[] args) throws FileNotFoundException,
			XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();

		XMLEventReader eventReader = factory
				.createXMLEventReader(new FileReader("test.xml"));
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
				StartElement startElement = event.asStartElement();
				System.out.println(startElement.getName().getLocalPart());
			}
		}
	}
}
