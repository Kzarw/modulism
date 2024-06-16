# Модульность в приложениях

## ArchUnit

Тестовая библиотека. Чтобы анализировать на уровне аннотаций собственно аннотацию нужно завести самостоятельно.
В качестве примера аннотация `@ArchModule` практически из документации.

Само по себе описано очень подробно в официальной документации: https://www.archunit.org/userguide/html/000_Index.html

## Spring Modulith

На что стоит посмотреть:

1) Тесты и документация. Обратить внимание, что искуственный модуль `base` не отображается на сгенерированной диаграмме,
если не импортировать, например контроллер в контроллер другого модуля. См. `ru.tbank.javaconf.modulism.operations.module.controller.OperationController`

2) Пока не добавлен `spring-modulith-runtime` модульность существует только на уровне тестов. Впрочем эта зависимость тянется
вместе с `spring-modulith-starter-insight`.

3) Дефолтный layout предполагает что все модули расположены непосредственно в пакете с `*Application.java`. Для кастомизации
нужно тянуть `spring-modulith-core` в compile-time.

4) Доступность модулей определяется либо по имени пакета либо по FQ-пакета. displayName не влияет никак.

5) По-умолчанию, доступны только классы в корне модуля. `@NamedInterface` позволяет открывать дополнительные пакеты. Но есть нюанс
```java 
@NamedInterface("foo") 
package module.foo;
```
```java
@NamedInterface("foobar")
package module.foo.bar;
```

```java
@ApplicationModule(
  allowedDependencies = { "module::foo", "module::foobar" } 
)
package other;
```
Будет заваливать тест.

6) В БД Event'ы пишутся только если _потребители_ аннотированы `@TransactionalEventListener`.
При этом если сам метод не транзакционный, то запись произойдёт но EventHandler не будет вызван. 
7) Вызовы между модулями трассируются если вызывается либо напрямую реализация метода, либо вызывается `@Controller`. Просто вызвать интерфейс сервиса не отработает
См. иерархию `OperationService` -> `InternalOperationService` -> `OperationsServiceImpl`
