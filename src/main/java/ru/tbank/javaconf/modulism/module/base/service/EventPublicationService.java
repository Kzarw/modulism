package ru.tbank.javaconf.modulism.module.base.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tbank.javaconf.modulism.module.base.entity.EventPublication;
import ru.tbank.javaconf.modulism.module.base.repository.EventPublicationRepository;

@Service
@RequiredArgsConstructor
public class EventPublicationService {

  private final EventPublicationRepository eventPublicationRepository;

  public Iterable<EventPublication> getPublications() {
    return eventPublicationRepository.findAll();
  }
}
