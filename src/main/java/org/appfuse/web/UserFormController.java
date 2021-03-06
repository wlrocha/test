package org.appfuse.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/userform.*")
public class UserFormController extends FormController {
    private final Log log = LogFactory.getLog(UserFormController.class);
    @Autowired
    UserManager userManager;
    
    @Autowired(required = false)
	@Qualifier("beanValidator")
	Validator validator;

    public UserFormController() {
        setCommandName("user");
        setCommandClass(User.class);
        setFormView("userForm");
        setSuccessView("redirect:users.html");
        if (validator != null)
            setValidator(validator);
    }

    public ModelAndView processFormSubmission(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object command,
                                              BindException errors)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return new ModelAndView(getSuccessView());
        }

        return super.processFormSubmission(request, response, command, errors);
    }

    /**
     * Set up a custom property editor for converting Longs
     */
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
        // convert java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format"));
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, 
                new CustomDateEditor(dateFormat, true));
        
        // convert java.lang.Long
        binder.registerCustomEditor(Long.class, null,
                new CustomNumberEditor(Long.class, null, true));
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
            throws Exception {
        log.debug("entering 'onSubmit' method...");

        User user = (User) command;

        if (request.getParameter("delete") != null) {
            userManager.removeUser(user.getId().toString());
            request.getSession().setAttribute("message", 
                    getText("user.deleted", user.getFullName()));
        } else {
            userManager.saveUser(user);
            request.getSession().setAttribute("message",
                    getText("user.saved", user.getFullName()));
        }

        return new ModelAndView(getSuccessView());
    }

    protected Object formBackingObject(HttpServletRequest request)
            throws ServletException {
        String userId = request.getParameter("id");

        if ((userId != null) && !userId.equals("")) {
            return userManager.getUser(userId);
        } else {
            return new User();
        }
    }
}
