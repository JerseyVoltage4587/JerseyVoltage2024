// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmDown;
import frc.robot.commands.DriveBase.MoveDistance;
import frc.robot.commands.DriveBase.zeroDriveMotors;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Intake.zeroIntakeMotors;
import frc.robot.commands.Shooter.runShooter;
import frc.robot.commands.Shooter.zeroShooterMotors;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SpeakerExit extends SequentialCommandGroup {
  /** Creates a new SpeakerExit. */
  public SpeakerExit() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ArmDown(),
    new WaitCommand(2),
    new runShooter(), 
    new WaitCommand(1.5), 
    new runIntake(), 
    new WaitCommand(1.5), 
    new zeroShooterMotors(),
    new MoveDistance(5, 0.2, 0),
    new zeroIntakeMotors(),
    new MoveDistance(-5, 0.2, 0),
    new runShooter(),
    new runIntake(),
    new zeroIntakeMotors(),
    new zeroShooterMotors());
  }
}
