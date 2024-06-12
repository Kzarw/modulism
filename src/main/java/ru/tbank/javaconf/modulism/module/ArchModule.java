package ru.tbank.javaconf.modulism.module;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PACKAGE)
@Documented
public @interface ArchModule {

  /**
   * @return name of the module
   */
  String name();

  /**
   * @return array of modules that required for this module to work
   */
  String[] allowedDependencies() default {};

  /**
   * @return array of packages that is exposed to consumers
   */
  String[] exposedPackages() default {};
}
