package ru.tbank.javaconf.modulism.root.config;

import org.springframework.lang.NonNull;
import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.core.ApplicationModuleDetectionStrategy;
import org.springframework.modulith.core.JavaPackage;

import java.util.stream.Stream;


public class ModulismApplicationModuleDetectionStrategy implements ApplicationModuleDetectionStrategy {

  @Override
  public @NonNull Stream<JavaPackage> getModuleBasePackages(@NonNull JavaPackage basePackage) {
    return basePackage.getSubPackagesAnnotatedWith(ApplicationModule.class);
  }
}
