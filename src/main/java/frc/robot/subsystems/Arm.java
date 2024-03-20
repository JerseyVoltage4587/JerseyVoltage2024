// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Arm extends SubsystemBase {
  
  static Arm m_Instance = null;
  private static TalonSRX armMotorLeft = new TalonSRX(Constants.armMotorLeft);
  private static TalonSRX armMotorRight = new TalonSRX(Constants.armMotorRight);

  /** Creates a new Arm. */
  public Arm() {
    armMotorLeft.configFactoryDefault();
    armMotorRight.configFactoryDefault();
    armMotorLeft.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public static Arm getInstance() {
    if (m_Instance == null) {
      synchronized (Arm.class) {
        if (m_Instance == null) {
          m_Instance = new Arm();
        }
      }
    }
    return m_Instance;
  }

  public void armUp() {
    armMotorLeft.set(TalonSRXControlMode.PercentOutput, -.6);
    armMotorRight.set(TalonSRXControlMode.PercentOutput, -.6);
  }

  public void armDown() {
    armMotorLeft.set(TalonSRXControlMode.PercentOutput, .6);
    armMotorRight.set(TalonSRXControlMode.PercentOutput, .6);
  }

  public void zeroArmMotors() {
    armMotorLeft.set(TalonSRXControlMode.PercentOutput, 0);
    armMotorRight.set(TalonSRXControlMode.PercentOutput, 0);
  }
}
