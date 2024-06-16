package ru.tbank.javaconf.modulism.module.base.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tbank.javaconf.modulism.module.base.entity.EventPublication;

import java.util.UUID;

public interface EventPublicationRepository extends CrudRepository<EventPublication, UUID> {
}
