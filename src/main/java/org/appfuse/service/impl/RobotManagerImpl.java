package org.appfuse.service.impl;

import org.appfuse.dao.RobotDao;
import org.appfuse.model.Robot;
import org.appfuse.service.RobotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "robotManager")
public class RobotManagerImpl implements RobotManager {

    private RobotDao dao;

    @Autowired
    public RobotManagerImpl(RobotDao robotDao)
    {
        this.dao = robotDao;
    }

    public void setRobotDao(RobotDao dao) {
        this.dao = dao;
    }

    public List getRobots() {
        return dao.getRobots();
    }

    public Robot getRobot(String robotId) {
        return dao.getRobot(Long.valueOf(robotId));
    }

    public void saveRobot(Robot robot) {
        dao.saveRobot(robot);
    }

    public void removeRobot(String robotId) {
        dao.removeRobot(Long.valueOf(robotId));
    }
}
