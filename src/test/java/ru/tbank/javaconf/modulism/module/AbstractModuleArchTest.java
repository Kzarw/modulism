package ru.tbank.javaconf.modulism.module;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

public abstract class AbstractModuleArchTest {

  @ArchTest
  static final ArchRule classesShouldOnlyBeAccessedInReportsModule = Architectures
    .layeredArchitecture()
    .consideringOnlyDependenciesInLayers()
    .layer("Controller").definedBy("..controller..")
    .layer("Service").definedBy("..service..")
    .layer("Service-Api").definedBy("..api.service..")
    .layer("Mapper").definedBy("..mapper..")
    .layer("Repository").definedBy("..repository..")
    .layer("Dto").definedBy("..dto..")
    .layer("API Model").definedBy("..api.model..")
    .layer("Entity").definedBy("..entity..")
    .withOptionalLayers(true)
    .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
    .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
    .whereLayer("API Model").mayOnlyAccessLayers("Dto", "Service-Api", "Service", "Controller")
    .whereLayer("Mapper").mayOnlyBeAccessedByLayers("Service")
    .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
    .whereLayer("Dto").mayOnlyBeAccessedByLayers("Service", "Controller", "Mapper", "Service-Api")
    .whereLayer("Entity").mayNotAccessAnyLayer()
    .whereLayer("Entity").mayOnlyBeAccessedByLayers("Mapper", "Service", "Repository")
    .allowEmptyShould(true);

}
