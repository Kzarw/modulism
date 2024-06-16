package ru.tbank.javaconf.modulism.config;

import org.springframework.modulith.core.ApplicationModuleDetectionStrategy;
import org.springframework.modulith.core.JavaPackage;

import java.util.stream.Stream;

public class ModulismApplicationModuleDetectionStrategy implements ApplicationModuleDetectionStrategy {

  private static final ApplicationModuleDetectionStrategy DELEGATE = ApplicationModuleDetectionStrategy.directSubPackage();

  @Override
  public Stream<JavaPackage> getModuleBasePackages(JavaPackage basePackage) {
    var modulesPackage = basePackage
      .getDirectSubPackages()
      .stream()
      .filter(it -> "module".equals(it.getLocalName()))
      .findFirst()
      .orElseThrow();
    return DELEGATE.getModuleBasePackages(modulesPackage);
  }
}
