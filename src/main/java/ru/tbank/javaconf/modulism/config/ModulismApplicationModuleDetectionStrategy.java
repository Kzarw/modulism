package ru.tbank.javaconf.modulism.config;

import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.core.ApplicationModuleDetectionStrategy;
import org.springframework.modulith.core.JavaPackage;

import java.util.stream.Stream;

public class ModulismApplicationModuleDetectionStrategy implements ApplicationModuleDetectionStrategy {

  @Override
  public Stream<JavaPackage> getModuleBasePackages(JavaPackage basePackage) {
    return basePackage.getSubPackagesAnnotatedWith(ApplicationModule.class);
  }
}
