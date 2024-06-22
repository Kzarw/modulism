@ArchModule(
  name = "report",
  allowedDependencies = {"operations", "tax"}
)
@ApplicationModule(
  displayName = "report",
  allowedDependencies = {"operations::model-api", "operations::service-api", "tax", "tax::model-api", "tax::service-api"}
)
package ru.tbank.javaconf.modulism.report;

import org.springframework.modulith.ApplicationModule;
import ru.tbank.javaconf.modulism.shared.ArchModule;