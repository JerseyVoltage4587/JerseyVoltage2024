// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmDown;
import frc.robot.commands.Arm.ArmUp;
import frc.robot.commands.DriveBase.MoveDistance;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Intake.zeroIntakeMotors;
import frc.robot.commands.Shooter.runShooter;
import frc.robot.commands.Shooter.zeroShooterMotors;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AmpExit extends SequentialCommandGroup {
  /** Creates a new AmpExit. */
  public AmpExit() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveDistance(5, 0.2, 0),
    new MoveDistance(0, 0.1, -2),
    new MoveDistance(-2, 0.2, 0),
    new ArmUp(),
    new WaitCommand(2),
    new runShooter(),
    new WaitCommand(1),
    new runIntake(),
    new WaitCommand(1),
    new ArmDown(),
    new WaitCommand(3),
    new MoveDistance(2, .2, 0),
    new MoveDistance(0, 0.1, 2),
    new MoveDistance(3, 0.2, 0),
    new zeroIntakeMotors(),
    new MoveDistance(-3, 0.2, 0),
    new MoveDistance(0, 0.1, -2),
    new MoveDistance(-2, 0.2, 0),
    new ArmUp(),
    new WaitCommand(3),
    new runShooter(),
    new WaitCommand(1),
    new runIntake(),
    new WaitCommand(1),
    new zeroIntakeMotors(),
    new zeroShooterMotors());
  }
}
