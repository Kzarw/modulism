package ru.tbank.javaconf.modulism.root.base;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.javaconf.modulism.root.base.entity.EventPublication;
import ru.tbank.javaconf.modulism.root.base.service.EventPublicationService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsPublicationsController {

  private final EventPublicationService eventPublicationService;

  @GetMapping
  Iterable<EventPublication> listEventPublications() {
    return eventPublicationService.getPublications();
  }
}
