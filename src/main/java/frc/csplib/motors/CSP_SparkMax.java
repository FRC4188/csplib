package frc.csplib.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

public class CSP_SparkMax extends CANSparkMax implements CSP_Motor {
  private RelativeEncoder encoder;
  private SparkMaxPIDController pid;

  public CSP_SparkMax(int id) {
    super(id, MotorType.kBrushless);
    encoder = getEncoder();
    pid = getPIDController();
    init();
  }

  public void init() {
    super.restoreFactoryDefaults();
    super.clearFaults();
    setEncoder(0);
  }

  public void set(double percent) {
    super.set(percent);
  }

  public void setVoltage(double voltage) {
    super.setVoltage(voltage);
  }

  public void setPosition(double position) {
    pid.setReference(position, ControlType.kPosition);
  }

  public void setPosition(double position, double ff) {
    pid.setReference(position, ControlType.kSmartMotion, 0, ff);
  }

  public void setVelocity(double velocity) {
    pid.setReference(velocity, ControlType.kVelocity);
  }

  public void setRampRate(double rate) {
    super.setOpenLoopRampRate(rate);
    super.setClosedLoopRampRate(rate);
  }

  public void setInverted(boolean inverted) {
    super.setInverted(inverted);
  }

  public void setBrake(boolean brake) {
    super.setIdleMode(brake ? IdleMode.kBrake : IdleMode.kCoast);
  }

  public void setPIDF(double kP, double kI, double kD, double kF) {
    pid.setP(kP);
    pid.setI(kI);
    pid.setD(kD);
    pid.setFF(kF);
  }

  public void setEncoder(double position) {
    encoder.setPosition(position);
  }

  public void setScalar(double scalar) {
    encoder.setPositionConversionFactor(scalar);
    encoder.setVelocityConversionFactor(scalar);
  }

  public double getVelocity() {
    return encoder.getVelocity();
  }

  public double getPosition() {
    return encoder.getPosition();
  }

  public double getTemperature() {
    return super.getMotorTemperature();
  }

  public double getCurrent() {
    return super.getOutputCurrent();
  }

  public int getID() {
    return super.getDeviceId();
  }

  public void setMotionPlaning(double maxVel, double maxAccel) {
    pid.setSmartMotionMaxVelocity(maxVel, 0);
    pid.setSmartMotionMaxAccel(maxVel, 0);
  }

  public void setContinousInputWrap(double min, double max) {
    pid.setPositionPIDWrappingEnabled(true);
    pid.setPositionPIDWrappingMaxInput(max);
    pid.setPositionPIDWrappingMinInput(min);
  }

  public void setError(double allowedErr) {
    pid.setSmartMotionAllowedClosedLoopError(allowedErr, 0);
  }
}
