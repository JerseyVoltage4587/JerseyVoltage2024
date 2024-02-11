// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveBase extends SubsystemBase {
  /** Creates a new DriveBase. */

  public DifferentialDrive m_drive = null;
  static DriveBase m_Instance = null;
  public static CANSparkMax driveLeftFrontSparkMAX = new CANSparkMax(Constants.driveLeftFrontSparkMAX, MotorType.kBrushless);
  public static CANSparkMax driveRightBackSparkMAX = new CANSparkMax(Constants.driveRightBackSparkMAX, MotorType.kBrushless);
  public static CANSparkMax driveRightFrontSparkMAX = new CANSparkMax(Constants.driveRightFrontSparkMAX, MotorType.kBrushless);
  public static CANSparkMax driveLeftBackSparkMAX = new CANSparkMax(Constants.driveLeftBackSparkMAX, MotorType.kBrushless);
  public static MotorControllerGroup rightMotors = new MotorControllerGroup(driveRightFrontSparkMAX, driveRightBackSparkMAX);
  public static MotorControllerGroup leftMotors = new MotorControllerGroup(driveLeftFrontSparkMAX, driveLeftBackSparkMAX);

  public DriveBase() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_drive = new DifferentialDrive(leftMotors, rightMotors);
    
  }

  public static DriveBase getInstance() {
    if (m_Instance == null) {
      synchronized (DriveBase.class) {
        if (m_Instance == null) {
          m_Instance = new DriveBase();
        }
      }
    }
    return m_Instance;
  }

  public void driveMotors(double Drive, double Turn) {
    // driveLeftBackSparkMAX.set(Drive - Turn);
    // driveRightBackSparkMAX.set(Drive + Turn);
    // driveLeftFrontSparkMAX.set(Drive - Turn);
    // driveRightFrontSparkMAX.set(Drive + Turn);

    // driveLeftBackSparkMAX.set(.5); // very bad
    // driveRightBackSparkMAX.set(.5); // bad
    // driveLeftFrontSparkMAX.set(.5); // Iffy
    // driveRightFrontSparkMAX.set(1); // iffy
    // left side tandem: bad
    // right side tandem: bad

    driveLeftBackSparkMAX.setInverted(true);
    driveLeftFrontSparkMAX.setInverted(true);
  }
}
