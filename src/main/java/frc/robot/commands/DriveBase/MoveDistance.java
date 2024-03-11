// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveBase;

public class MoveDistance extends Command {
  double m_distance;
  double m_speed;
  double m_turn;
  DriveBase m_drivebase = DriveBase.getInstance();
  /** Creates a new MoveDistance. */
  public MoveDistance(double distance, double speed, double turn) {
    m_distance = distance;
    m_speed = speed;
    m_turn = turn;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivebase.zeroDriveMotors();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivebase.MoveDistance(m_distance, m_speed, m_turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivebase.driveMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
