package com.mycollab.rest.server.resource

import com.mycollab.core.UserInvalidInputException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.servlet.http.HttpServletRequest

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
@ControllerAdvice(basePackages = ["com.mycollab.rest.server.resource"])
class GenericController {

    @ExceptionHandler(UserInvalidInputException::class)
    fun rulesForInvalidUserException(req: HttpServletRequest, e: Exception): ResponseEntity<*> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun rulesFoException(req: HttpServletRequest, e: Exception): ResponseEntity<*> {
        LOG.error("Error ${req.contextPath}", e)
        return ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(GenericController::class.java)
    }
}
