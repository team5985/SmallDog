/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  Joystick joy0 = new Joystick(0);

  Victor m_dr1 = new Victor(0);
  Victor m_dr2 = new Victor(1);
  Victor m_dl1 = new Victor(2);
  Victor m_dl2 = new Victor(3);

  CANSparkMax shooter = new CANSparkMax(2, MotorType.kBrushless);
  Victor hopper = new Victor(4);
  Victor intake = new Victor(5);


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

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

    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //get joystick position
    double hAxis = -joy0.getX();
    double vAxis = joy0.getY();
    double throttle = ((-joy0.getThrottle() + 1) / 2 * -1);
    double speed = vAxis * Config.kDriveMultiplier * Config.kSafeMultiplier * throttle;
    double turn = hAxis * Config.kDriveMultiplier * Config.kSafeMultiplier * throttle;
    if(vAxis <= 0.05 && vAxis >= -0.05){
      speed = 0;
    }
    if(hAxis <= 0.05 &&  hAxis>= -0.05){
      turn = 0;
    }
    //calcualte speed/turn
    double left = (speed+turn);
    double right = -((speed-turn));
    m_dl1.set((left * -1));
    m_dl2.set((left * -1));
    m_dr1.set((right * -1));
    m_dr2.set((right * -1));

    //init mechanism speeds
  double hopperSpeed = 0;
  double intakeSpeed = 0;
   double shooterSpeed = 0;
  if(joy0.getRawButton(1)) 
  {
     shooterSpeed = 1;
     hopperSpeed = -1.0;
  } 
  else 
  {
    shooterSpeed = 0.0;
    hopperSpeed = 0.0;
  }
    
  if(joy0.getRawButton(2)) {
    intakeSpeed = -0.75;
  } else if(joy0.getRawButton(3)) {
    intakeSpeed = 0.75;
  }else {
    intakeSpeed = 0.0;
  }

  if(joy0.getRawButton(4)) {
    hopperSpeed = -1;
  } else if (joy0.getRawButton(5)) {
    hopperSpeed = 1;
  } else {
    hopperSpeed = 0;
  }

  


  //Set motors
  shooter.set(shooterSpeed);
  hopper.setSpeed(hopperSpeed);
  intake.setSpeed(intakeSpeed);
}



  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}


