/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Drive extends TimedRobot {

  TalonSRX m_dr1 = new TalonSRX(1);
  TalonSRX m_dr2 = new TalonSRX(2);
  TalonSRX m_dl1 = new TalonSRX(3);
  TalonSRX m_dl2 = new TalonSRX(4);
  Joystick joy0 = new Joystick(0);
  @Override
  public void robotInit() {
   
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
 
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

 @Override
 public void teleopInit(){
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    


 }

  @Override
  public void teleopPeriodic() {
    double vAxis = -joy0.getRawAxis(1);
    double hAxis = joy0.getRawAxis(0);
    double speed = vAxis*0.1;
    double turn = hAxis*0.3;
    if(vAxis <= 0.05 && vAxis >= -0.05){
      speed = 0;
    }
    if(hAxis <= 0.05 &&  hAxis>= -0.05){
      turn = 0;
    }
    
    double left = speed+turn;
    double right = -(speed-turn);
    m_dl1.set(ControlMode.PercentOutput, left);
    m_dl2.set(ControlMode.PercentOutput, left);
    m_dr1.set(ControlMode.PercentOutput, right);
    m_dr2.set(ControlMode.PercentOutput, right);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
