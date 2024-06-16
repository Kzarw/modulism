@ArchModule(
  name = "operations",
  exposedPackages = "ru.tbank.javaconf.modulism.module.operations.api.."
)
@ApplicationModule(
  displayName = "operations",
  allowedDependencies = "base"
)
package ru.tbank.javaconf.modulism.module.operations;

import org.springframework.modulith.ApplicationModule;
import ru.tbank.javaconf.modulism.module.ArchModule;