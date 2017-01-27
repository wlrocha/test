package org.appfuse.service;

import org.appfuse.model.Robot;

import java.util.List;


public interface RobotManager
{
    public List getRobots();
    public Robot getRobot(String robotId);
    public void saveRobot(Robot robot);
    public void removeRobot(String robotId);
}
