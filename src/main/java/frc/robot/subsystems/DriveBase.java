// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.hal.CANAPITypes.CANDeviceType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveBase extends SubsystemBase {
  /** Creates a new DriveBase. */

  public DifferentialDrive m_drive = null;
  static DriveBase m_Instance = null;
  private static CANSparkMax driveLeftFrontSparkMAX = new CANSparkMax(Constants.driveLeftFrontSparkMAX, MotorType.kBrushless);
  private static CANSparkMax driveRightBackSparkMAX = new CANSparkMax(Constants.driveRightBackSparkMAX, MotorType.kBrushless);
  private static CANSparkMax driveRightFrontSparkMAX = new CANSparkMax(Constants.driveRightFrontSparkMAX, MotorType.kBrushless);
  private static CANSparkMax driveLeftBackSparkMAX = new CANSparkMax(Constants.driveLeftBackSparkMAX, MotorType.kBrushless);
  private static MotorControllerGroup rightMotors = new MotorControllerGroup(driveRightFrontSparkMAX, driveRightBackSparkMAX);
  private static MotorControllerGroup leftMotors = new MotorControllerGroup(driveLeftFrontSparkMAX, driveLeftBackSparkMAX);
  private static SparkAbsoluteEncoder driveLeftFrontEncoder = driveLeftFrontSparkMAX.getAbsoluteEncoder();
  private static SparkAbsoluteEncoder driveLeftBackEncoder = driveLeftBackSparkMAX.getAbsoluteEncoder();
  private static SparkAbsoluteEncoder driveRightFrontEncoder = driveRightFrontSparkMAX.getAbsoluteEncoder();
  private static SparkAbsoluteEncoder driveRightBackEncoder = driveRightBackSparkMAX.getAbsoluteEncoder();

  public DriveBase() {
    m_drive = new DifferentialDrive(leftMotors, rightMotors);
    m_drive.setExpiration(0.1);
    m_drive.setSafetyEnabled(false);
    driveLeftBackSparkMAX.restoreFactoryDefaults();
    driveLeftFrontSparkMAX.restoreFactoryDefaults();
    driveRightBackSparkMAX.restoreFactoryDefaults();
    driveRightFrontSparkMAX.restoreFactoryDefaults();
    driveLeftBackSparkMAX.setInverted(true);
    driveLeftFrontSparkMAX.setInverted(true);

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
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
    driveLeftBackSparkMAX.set(Drive - Turn);
    driveRightBackSparkMAX.set(Drive + Turn);
    driveLeftFrontSparkMAX.set(Drive - Turn);
    driveRightFrontSparkMAX.set(Drive + Turn);

    // driveLeftBackSparkMAX.set(.5); // very bad
    // driveRightBackSparkMAX.set(.5); // bad
    // driveLeftFrontSparkMAX.set(.5); // Iffy
    // driveRightFrontSparkMAX.set(1); // iffy
    // left side tandem: bad
    // right side tandem: bad

  }

  public double getLeftDistanceInches() {
    double leftBack = (driveLeftBackEncoder.getPosition() / 4096) * (6 * Math.PI);
    double leftFront = (driveLeftFrontEncoder.getPosition() / 4096) * (6 * Math.PI);
    return (leftFront + leftBack) / 2;
  }

  public double getRightDistanceInches() {
    double rightBack = (driveRightBackEncoder.getPosition() / 4096) * (6 * Math.PI);
    double rightFront = (driveRightFrontEncoder.getPosition() / 4096) * (6 * Math.PI);
    return (rightBack + rightFront) / 2;
  }

  double getAverageDistanceInches() {
    return (getLeftDistanceInches() + getRightDistanceInches()) / 2;
  }

  public void MoveDistance(double distance, double speed, double turn) {

    double averageInches = getAverageDistanceInches();

    if(Math.abs(averageInches) > Math.abs(distance)) {
      driveMotors(0, 0);
    } else {
      driveMotors(speed, turn);
    }
  }

  public void zeroDriveMotors() {
    driveLeftBackEncoder.setZeroOffset(driveLeftBackEncoder.getPosition());
    driveLeftFrontEncoder.setZeroOffset(driveLeftFrontEncoder.getPosition());
    driveRightBackEncoder.setZeroOffset(driveRightBackEncoder.getPosition());
    driveRightFrontEncoder.setZeroOffset(driveRightFrontEncoder.getPosition());
  }
}
