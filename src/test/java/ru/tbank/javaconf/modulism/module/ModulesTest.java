package ru.tbank.javaconf.modulism.module;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.modules.syntax.ModuleDependencyScope;

import static com.tngtech.archunit.library.modules.syntax.ModuleRuleDefinition.modules;

@AnalyzeClasses(packagesOf = ModulesTest.class)
public class ModulesTest {

  @ArchTest
  @SuppressWarnings("unused")
  static final ArchRule modulesShouldBeCorrectlyExposed = modules().definedByAnnotation(ArchModule.class)
    .should().respectTheirAllowedDependenciesDeclaredIn(
      "allowedDependencies",
      ModuleDependencyScope.consideringOnlyDependenciesBetweenModules()
    )
    .andShould().onlyDependOnEachOtherThroughPackagesDeclaredIn("exposedPackages");
}
