package org.appfuse.web;

import org.springframework.web.servlet.mvc.SimpleFormController;


/**
 */
public abstract class FormController extends SimpleFormController
{


	/**
	 * Convenience method for getting a i18n key's value.  Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable
	 * is not set in unit tests b/c there's no DispatchServlet Request.
	 *
	 * @param msgKey the i18n key to lookup
	 * @return the message for the key
	 */
	public String getText(String msgKey) {
		return getMessageSourceAccessor().getMessage(msgKey);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single
	 * string argument.
	 *
	 * @param msgKey the i18n key to lookup
	 * @param arg arguments to substitute into key's value
	 * @return the message for the key
	 */
	public String getText(String msgKey, String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 *
	 * @param msgKey the i18n key to lookup
	 * @param args arguments to substitute into key's value
	 * @return the message for the key
	 */
	public String getText(String msgKey, Object[] args) {
		return getMessageSourceAccessor().getMessage(msgKey, args);
	}
}
