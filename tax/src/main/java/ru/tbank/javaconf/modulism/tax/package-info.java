@ArchModule(
  name = "tax",
  allowedDependencies = {"operations"},
  exposedPackages = "ru.tbank.javaconf.modulism.tax.."
)
@ApplicationModule(
  displayName = "tax",
  allowedDependencies = {"operations::model-api", "operations::service-api" /*, "operations::api" */}
)
package ru.tbank.javaconf.modulism.tax;

import org.springframework.modulith.ApplicationModule;
import ru.tbank.javaconf.modulism.shared.ArchModule;