open module modulism.report {
  requires static lombok;
  requires org.slf4j;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires org.springframework.modulith.api;
  requires modulism.shared;
  requires modulism.operations;
  requires modulism.tax;
  requires spring.beans;
  requires spring.kafka;
  requires spring.web;
}