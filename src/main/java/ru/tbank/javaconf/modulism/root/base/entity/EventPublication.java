package ru.tbank.javaconf.modulism.root.base.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("EVENT_PUBLICATION")
@Getter
@Setter
public class EventPublication {
  @Id
  private UUID id;
  private Instant completionDate;
  private String eventType;
  private String listenerId;
  private Instant publicationDate;
  @Column("SERIALIZED_EVENT")
  private String event;
}
