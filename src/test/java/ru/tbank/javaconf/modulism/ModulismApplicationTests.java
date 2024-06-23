package ru.tbank.javaconf.modulism;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import ru.tbank.javaconf.modulism.root.ModulismApplication;

@SpringBootTest
class ModulismApplicationTests {

	@Test
	void contextLoads() {
	}

  @Test
  void verifyArchitecture() {
    var modules = ApplicationModules.of(ModulismApplication.class);
    ApplicationModules.of(ModulismApplication.class).verify();
  }

}
