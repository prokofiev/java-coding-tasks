# Задача "банк"

## Основная задача (20 минут)

Реализуйте сервис для работы с банковскими счетами с базовой валидацией.

### Требования:

1. Создайте класс `BankAccount` с полями:
    - `accountNumber` (String) - номер счета
    - `balance` (BigDecimal) - текущий баланс
    - `ownerName` (String) - имя владельца

2. Создайте класс `BankService` со следующими методами:
    - `BankAccount createAccount(String accountNumber, String ownerName, BigDecimal initialDeposit)`
    - `void transfer(BankAccount from, BankAccount to, BigDecimal amount)`
    - `BigDecimal getBalance(String accountNumber)`

3. Валидация:
    - Номер счета должен содержать только цифры и быть длиной 10 символов
    - Начальный депозит не может быть отрицательным
    - Перевод не может быть отрицательным
    - При переводе должно быть достаточно средств на счете отправителя

### Тестовые случаи для проверки:
   - Создание счета с валидными данными 
   - Создание счета с невалидным номером (буквы, короткий/длинный номер)
   - Создание счета с отрицательным начальным депозитом 
   - Перевод с недостаточным балансом 
   - Перевод отрицательной суммы 
   - Перевод на несуществующий счет

### Примеры для самопроверки:

```java
// Создание счета
BankService service = new BankService();
BankAccount account1 = service.createAccount("1234567890", "John Doe", new BigDecimal("1000.00"));

// Получение баланса
BigDecimal balance = service.getBalance("1234567890"); // 1000.00

// Перевод между счетами
BankAccount account2 = service.createAccount("0987654321", "Jane Smith", new BigDecimal("500.00"));
service.transfer(account1, account2, new BigDecimal("200.00"));

// Проверка балансов после перевода
service.getBalance("1234567890"); // 800.00
service.getBalance("0987654321"); // 700.00
```



