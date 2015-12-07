package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.core.UserInvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
@ControllerAdvice(basePackages = {"com.esofthead.mycollab.rest.server.resource"})
public class GenericController {
    private static Logger LOG = LoggerFactory.getLogger(GenericController.class);

    @ExceptionHandler(UserInvalidInputException.class)
    public ResponseEntity rulesForInvalidUserException(HttpServletRequest req, Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity rulesFoException(HttpServletRequest req, Exception e) {
        LOG.error("Error", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
