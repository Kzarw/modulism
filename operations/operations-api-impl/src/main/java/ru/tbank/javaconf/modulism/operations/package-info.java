@ArchModule(
  name = "operations",
  exposedPackages = "ru.tbank.javaconf.modulism.operations.api.."
)
@ApplicationModule(
  displayName = "operations",
  allowedDependencies = "base"
)
package ru.tbank.javaconf.modulism.operations;

import org.springframework.modulith.ApplicationModule;
import ru.tbank.javaconf.modulism.shared.ArchModule;