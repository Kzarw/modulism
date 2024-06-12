package ru.tbank.javaconf.modulism;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ArchTest {

  private static final Set<String> GENERAL_MODULES = Set.of("operations", "report", "tax");
  public static final String BASE_APP_PACKAGE = "ru.tbank.javaconf.modulism";

  @Test
  void testArchitecture() {
    var importer = new ClassFileImporter().importPackages(BASE_APP_PACKAGE);
    for (var module : GENERAL_MODULES) {
      var entityLayerRules = ArchRuleDefinition.classes()
        .that().resideInAPackage(asPackage(BASE_APP_PACKAGE, module, "entity"))
        .should().onlyBeAccessed()
        .byClassesThat()
        .resideInAnyPackage(
          asPackage(BASE_APP_PACKAGE, module, "entity"),
          asPackage(BASE_APP_PACKAGE, module, "repository"),
          asPackage(BASE_APP_PACKAGE, module, "mapper"),
          asPackage(BASE_APP_PACKAGE, module, "service")
        )
        .because("Entities of module " + module + "should not leak beyond service layer");
      entityLayerRules
        .allowEmptyShould(true)
        .evaluate(importer);
      var repositoryLevelRules = ArchRuleDefinition.classes()
        .that().resideInAPackage(asPackage(BASE_APP_PACKAGE, module, "repository"))
        .should().onlyBeAccessed()
        .byClassesThat()
        .resideInAnyPackage(
          asPackage(BASE_APP_PACKAGE, module, "repository", "impl"),
          asPackage(BASE_APP_PACKAGE, module, "service")
        )
        .because("Repositories of module " + module + " should not leak beyond service layer and can be implemented in the impl package");
      repositoryLevelRules
        .allowEmptyShould(true)
        .evaluate(importer);

      var internalRules = ArchRuleDefinition.classes()
        .that().resideInAPackage(asPackage(BASE_APP_PACKAGE, module, "internal"))
        .should().onlyAccessClassesThat().resideInAPackage(asPackage(BASE_APP_PACKAGE, module, "internal"));

      internalRules.because("Internal package definitions of module " + module + " should not be exposed to consumers");

      internalRules
        .allowEmptyShould(true)
        .evaluate(importer);


    }
  }

  private static String asPackage(String... paths) {
    return String.join(".", paths);
  }
}
