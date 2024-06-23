open module modulism.operations {
  requires static lombok;
  exports ru.tbank.javaconf.modulism.operations;
  exports ru.tbank.javaconf.modulism.operations.api.service;
  exports ru.tbank.javaconf.modulism.operations.api.model;
  requires org.slf4j;
  requires org.springframework.modulith.api;
  requires spring.boot.starter.data.jdbc;
  requires spring.boot.starter.jdbc;
  requires spring.web;
  requires spring.data.relational;
  requires spring.data.commons;
  requires jakarta.validation;
  requires spring.context;
  requires spring.data.jdbc;
  requires spring.beans;
  requires spring.boot.autoconfigure;
  requires modulism.shared;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires spring.kafka;
  requires spring.messaging;
  requires spring.tx;
  requires java.sql;
}