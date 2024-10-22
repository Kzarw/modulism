package ru.tbank.javaconf.modulism;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class DocumentationTest {
  ApplicationModules modules = ApplicationModules.of(ModulismApplication.class);

  @Test
  void writeDocumentationSnippets() {

    new Documenter(modules)
      .writeModulesAsPlantUml();
  }
}
