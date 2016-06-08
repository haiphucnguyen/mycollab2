package com.getlocalization.api.files;

/**
 * Thrown when server is handling way too much data simultaneously. You should
 * try to do your request again after a moment.
 */
public class GLServerBusyException extends Exception {

}
