package org.appfuse.web;

import org.appfuse.service.RobotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RobotController
{
    @Autowired
    RobotManager robotManager;

    @RequestMapping("/robots.*")
    public String execute(ModelMap model) {
        model.addAttribute("robotList", robotManager.getRobots());
        return "robotList";
    }
}
