package org.appfuse.dao;

import org.appfuse.model.Robot;

import java.util.List;


public interface RobotDao extends Dao {
    List getRobots();
    Robot getRobot(Long robotId);
    void saveRobot(Robot robot);
    void removeRobot(Long robotId);
}
