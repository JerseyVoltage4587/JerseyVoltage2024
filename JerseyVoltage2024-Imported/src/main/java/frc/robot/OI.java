package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.DriveBase;

public class OI extends Command{
    
    DriveBase m_drive;
    static OI Instance = null;
    public Joystick j, k;
    public Trigger jButtonY, jButtonX, jButtonA, jButtonB, jLeftBumper, jRightBumper, jLeftTrigger, jRightTrigger,
    jMinusButton, jPlusButton, jLeftStickButton, jRightStickButton;

    public Trigger kButtonY, kButtonX, kButtonA, kButtonB, kLeftBumper, kRightBumper, kLeftTrigger, kRightTrigger,
    kMinusButton, kPlusButton, kLeftStickButton, kRightStickButton;

    public OI() {
        j = new Joystick(0);
        k = new Joystick(1);

        jButtonY = new JoystickButton(j, 1);
        jButtonB = new JoystickButton(j, 2);
        jButtonA = new JoystickButton(j, 3);
        jButtonX = new JoystickButton(j, 4);
        jLeftBumper = new JoystickButton(j, 5);
        jRightBumper = new JoystickButton(j, 6);
        jLeftTrigger = new JoystickButton(j, 7);
        jRightTrigger = new JoystickButton(j, 8);
        jMinusButton = new JoystickButton(j, 9);
        jPlusButton = new JoystickButton(j, 10);
        jLeftStickButton = new JoystickButton(j, 11);
        jRightStickButton = new JoystickButton(j, 12);

        kButtonY = new JoystickButton(k, 1);
        kButtonB = new JoystickButton(k, 2);
        kButtonA = new JoystickButton(k, 3);
        kButtonX = new JoystickButton(k, 4);
        kLeftBumper = new JoystickButton(k, 5);
        kRightBumper = new JoystickButton(k, 6);
        kLeftTrigger = new JoystickButton(k, 7);
        kRightTrigger = new JoystickButton(k, 8);
        kMinusButton = new JoystickButton(k, 9);
        kPlusButton = new JoystickButton(k, 10);
        kLeftStickButton = new JoystickButton(k, 11);
        kRightStickButton = new JoystickButton(k, 12);

    }

    public static OI getInstance() {
        if(Instance == null) {
            synchronized (OI.class) {
                Instance = new OI();
            }
        }
        return Instance;
    }
}
