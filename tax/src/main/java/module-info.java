module modulism.tax {
  requires static lombok;
  requires org.slf4j;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires org.springframework.modulith.api;
  requires modulism.shared;
  requires spring.tx;
  requires spring.data.commons;
  requires org.springframework.modulith.events.api;
  requires modulism.operations;
  requires spring.data.relational;
  requires jakarta.validation;
  requires spring.web;
  requires spring.data.jdbc;
  exports ru.tbank.javaconf.modulism.tax.api.model;
  exports ru.tbank.javaconf.modulism.tax.api.service;
}