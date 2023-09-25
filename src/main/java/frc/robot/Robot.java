// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /*The lines below initialize a motor attached to a spark motor controller.
    The parameter passed into the constructor is the port that the motor controller is attached to.
   */ 
  
  private Spark frontLeft = new Spark(0);
  private Spark backLeft = new Spark(1);
  private Spark frontRight = new Spark(2);
  private Spark backRight = new Spark(3);
  // This creates an object of a xbox controller since we will be using a controller to move the robot.
  // The 0 is the usb port number that the xbox is plugged into on the computer. 
  private XboxController controller = new XboxController(0);
  // This is a motor controller group and it allows you to group controllers so that they can both do the same thing.
  // Here, I'm creating a group for the left motors and the right motors 

  MotorControllerGroup rMotorControllerGroup = new MotorControllerGroup(frontLeft, backLeft);
  MotorControllerGroup lMotorControllerGroup = new MotorControllerGroup(frontRight, backRight);

  // This is an object that basically allows us to move the wheels of the robot. 
  // Differential Drive is a type of steering that only allows you to control front and back movement, so the only way to turn is to reduce the speed
  // Of one side and increase the speed on the other.
  DifferentialDrive differentialDrive  = new DifferentialDrive(rMotorControllerGroup, lMotorControllerGroup);
  public double m_autoStart;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
      
  }


  //  This method is run as long as the robot is turned on. 
  @Override
  public void robotPeriodic() {}

  // Run during the autonomous phase of the competition ONE time, during start of autonomous. 
  // Should be used to instantiate any objects that will be used autonomous
  @Override
  public void autonomousInit() {
    m_autoStart = Timer.getFPGATimestamp();
  }

  // Runs every 50ms. Any debug statements, dashboard information, and anything that needs to be checked continuous should be put here.
  @Override
  public void autonomousPeriodic() {
    if(m_autoStart>=10){
      autonomousExit();
    }
  }

  // Runs once the first time teleoperated mode is activated. Object declarations should be done here as well.
  @Override
  public void teleopInit() {}

  // Runs every 50ms during teleop mode.
  @Override
  public void teleopPeriodic() {
    differentialDrive.tankDrive(controller.getLeftX(), controller.getLeftY(), false);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
