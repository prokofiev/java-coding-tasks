package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

  private BankService bankService;

  @BeforeEach
  void setUp() {
    bankService = new BankService();
  }

  @Test
  void shouldCreateAccountWithValidData() {
    // when
    BankAccount account = bankService.createAccount(
        "1234567890", "John Doe", new BigDecimal("1000.00"));

    // then
    assertNotNull(account);
    assertEquals("1234567890", account.getAccountNumber());
    assertEquals("John Doe", account.getOwnerName());
    assertEquals(new BigDecimal("1000.00"), account.getBalance());
  }

  @Test
  void shouldThrowExceptionWhenInvalidAccountNumber() {
    // when & then
    assertThrows(IllegalArgumentException.class, () -> {
      bankService.createAccount("123", "John Doe", new BigDecimal("1000.00"));
    });
  }

  @Test
  void shouldTransferMoneyBetweenAccounts() {
    // given
    BankAccount from = bankService.createAccount(
        "1234567890", "John Doe", new BigDecimal("1000.00"));
    BankAccount to = bankService.createAccount(
        "0987654321", "Jane Smith", new BigDecimal("500.00"));

    // when
    bankService.transfer(from, to, new BigDecimal("200.00"));

    // then
    assertEquals(new BigDecimal("800.00"), bankService.getBalance("1234567890"));
    assertEquals(new BigDecimal("700.00"), bankService.getBalance("0987654321"));
  }

  @Test
  void shouldThrowExceptionWhenInsufficientBalance() {
    // given
    BankAccount from = bankService.createAccount(
        "1234567890", "John Doe", new BigDecimal("100.00"));
    BankAccount to = bankService.createAccount(
        "0987654321", "Jane Smith", new BigDecimal("500.00"));

    // when & then
    assertThrows(IllegalStateException.class, () -> {
      bankService.transfer(from, to, new BigDecimal("200.00"));
    });
  }
}