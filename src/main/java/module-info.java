open module modulism.main {
  requires static lombok;
  requires org.slf4j;
  requires org.springframework.modulith.api;
  requires org.springframework.modulith.core;
  requires kafka.clients;
  requires spring.boot.autoconfigure;
  requires spring.boot.starter.data.jdbc;
  requires spring.context;
  requires spring.tx;
  requires spring.boot;
  requires spring.core;
  requires spring.jdbc;
  requires spring.kafka;
  requires spring.beans;
  requires spring.web;
  requires spring.data.relational;
  requires spring.data.commons;
  requires spring.data.jdbc;
  requires java.sql;
  requires transitive org.yaml.snakeyaml;
  requires transitive com.fasterxml.jackson.databind;
  requires transitive jakarta.annotation;
  requires com.tngtech.archunit;

  requires modulism.operations;
  requires modulism.tax;
  requires modulism.report;
}