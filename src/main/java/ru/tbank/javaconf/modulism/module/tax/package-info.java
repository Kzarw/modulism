@ArchModule(
  name = "tax",
  allowedDependencies = {"operations"},
  exposedPackages = "ru.tbank.javaconf.modulism.module.tax.."
)
@ApplicationModule(
  displayName = "tax",
  allowedDependencies = {"operations::model-api", "operations::service-api" /*, "operations::api" */}
)
package ru.tbank.javaconf.modulism.module.tax;

import org.springframework.modulith.ApplicationModule;
import ru.tbank.javaconf.modulism.module.ArchModule;