// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Auto.AmpExit;
import frc.robot.commands.Auto.ExitOnly;
import frc.robot.commands.Auto.Nothing;
import frc.robot.commands.Auto.SpeakerExit;
import frc.robot.commands.DriveBase.DriveJoystick;
import frc.robot.subsystems.DriveBase;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  public DifferentialDrive m_drive = null;
  CANSparkMax driveLeftFrontSparkMAX = null;
  CANSparkMax driveRightBackSparkMAX = null;
  CANSparkMax driveRightFrontSparkMAX = null;
  CANSparkMax driveLeftBackSparkMAX = null;
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  OI m_oi;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    m_chooser.setDefaultOption("Leave Only", new ExitOnly());
    m_chooser.addOption("Score Amp 2x, Leave", new AmpExit());
    m_chooser.addOption("Score Speaker 2x, Leave", new SpeakerExit());
    m_chooser.addOption("Do Nothing", new Nothing());
    SmartDashboard.putData("Autos", m_chooser);

/*    driveLeftFrontSparkMAX = new CANSparkMax(Constants.driveLeftFrontSparkMAX, MotorType.kBrushless);
    driveRightBackSparkMAX = new CANSparkMax(Constants.driveRightBackSparkMAX, MotorType.kBrushless);
    driveRightFrontSparkMAX = new CANSparkMax(Constants.driveRightFrontSparkMAX, MotorType.kBrushless);
    driveLeftBackSparkMAX = new CANSparkMax(Constants.driveLeftBackSparkMAX, MotorType.kBrushless);*/
    // MotorControllerGroup rightMotors = new MotorControllerGroup(driveRightFrontSparkMAX, driveRightBackSparkMAX);
    // MotorControllerGroup leftMotors = new MotorControllerGroup(driveLeftFrontSparkMAX, driveLeftBackSparkMAX);

    // rightMotors.setInverted(true);

    // m_drive = new DifferentialDrive(leftMotors, rightMotors);

    m_oi = OI.getInstance();
    DriveBase.getInstance().setDefaultCommand(new DriveJoystick());

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    m_autonomousCommand = m_chooser.getSelected();
    CommandScheduler.getInstance().schedule(m_autonomousCommand);

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    // DriveBase.getInstance().toggleMode(OI.getInstance().j.getRawButton(7));

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
