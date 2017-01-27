package org.appfuse.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.model.Robot;
import org.appfuse.model.User;
import org.appfuse.service.RobotManager;
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
import java.util.*;


@Controller
@RequestMapping("/robotform.*")
public class RobotFormController extends FormController {
    private final Log log = LogFactory.getLog(RobotFormController.class);
    @Autowired
    RobotManager robotManager;

    @Autowired
    UserManager userManager;

    @Autowired(required = false)
	 @Qualifier("beanValidator")
	 Validator validator;

    public RobotFormController() {
        setCommandName("robot");
        setCommandClass(Robot.class);
        setFormView("robotForm");
        setSuccessView("redirect:robots.html");
        if (validator != null)
        {
            setValidator(validator);
        }
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
        log.debug("entering 'onSubmit' method..."+command);

        Robot robot = (Robot) command;

        if (request.getParameter("delete") != null) {
            deleteRobot(request, robot);
        } else {
            updateRobot(request, robot);
        }
        return new ModelAndView(getSuccessView());
    }

    private void updateRobot(HttpServletRequest request, Robot robot)
    {
        if(robot.getOwnerId() != null && robot.getOwnerId().trim().length() > 0)
		  {
				robot.setOwner(userManager.getUser(robot.getOwnerId()));
		  }
		  else
		  {
				robot.setOwner(null);
		  }
        robotManager.saveRobot(robot);
        request.getSession().setAttribute("message", getText("robot.saved"));
    }

    private void deleteRobot(HttpServletRequest request, Robot robot)
    {
        robotManager.removeRobot(robot.getId().toString());
        request.getSession().setAttribute("message", getText("robot.deleted"));
    }

    protected Robot formBackingObject(HttpServletRequest request)
            throws ServletException {
        String robotId = request.getParameter("id");

        if (robotId != null && robotId.trim().length()>0) {
            Robot robot = robotManager.getRobot(robotId);
            if(robot.getOwner() != null)
            {
                robot.setOwnerId(robot.getOwner().getId().toString());
            }
            return robot;
        } else {
            return new Robot();
        }
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {

        Map referenceData = new HashMap();
        Map<Long, User> users = new LinkedHashMap<Long, User>();
        List<User> userList = userManager.getUsers();
        for(User user:userList)
        {
            users.put(user.getId(), user);
        }
        referenceData.put("users", users);
        return referenceData;
    }

}
