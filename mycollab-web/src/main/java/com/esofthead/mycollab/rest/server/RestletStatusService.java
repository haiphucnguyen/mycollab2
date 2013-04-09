package com.esofthead.mycollab.rest.server;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.rest.RestletErrorItem;

public class RestletStatusService extends StatusService {
	private static Logger log = LoggerFactory
			.getLogger(RestletStatusService.class);

	@Override
	public Representation getRepresentation(Status status, Request request,
			Response response) {
		Throwable throwable = status.getThrowable();
		if (throwable != null) {
			log.error("get representation: " + status.getDescription() + "--"
					+ status.getCode() + "--" + status.getReasonPhrase(),
					status.getThrowable());
			RestletErrorItem error = null;

			if (throwable instanceof UserInvalidInputException) {
				error = new RestletErrorItem(RestletErrorItem.BAD_REQUEST,
						throwable.getMessage());
			} else if (throwable instanceof MyCollabException) {
				error = new RestletErrorItem(RestletErrorItem.INTERNAL_ERROR,
						throwable.getMessage());
			} else {
				error = new RestletErrorItem(RestletErrorItem.INTERNAL_ERROR,
						throwable.getMessage());
			}
			log.debug("Return custom error item: "
					+ BeanUtility.printBeanObj(error));
			return new JsonRepresentation(error);
		}

		return super.getRepresentation(status, request, response);
	}

}
