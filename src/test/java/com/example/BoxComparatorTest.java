package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import static com.example.BoxComparisonResult.*;

class BoxComparatorTest {

  @Test
  void testEqualBoxes() {
    assertEquals(EQUAL, BoxComparator.compareBoxes(1, 2, 3, 3, 2, 1));
    assertEquals(EQUAL, BoxComparator.compareBoxes(5, 5, 5, 5, 5, 5));
    assertEquals(EQUAL, BoxComparator.compareBoxes(10, 20, 30, 30, 10, 20));
  }

  @Test
  void testFirstBoxSmaller() {
    assertEquals(FIRST_SMALLER, BoxComparator.compareBoxes(1, 2, 3, 2, 3, 4));
    assertEquals(FIRST_SMALLER, BoxComparator.compareBoxes(5, 5, 5, 6, 6, 6));
    assertEquals(FIRST_SMALLER, BoxComparator.compareBoxes(1, 1, 1, 1, 1, 2));
  }

  @Test
  void testFirstBoxLarger() {
    assertEquals(FIRST_LARGER, BoxComparator.compareBoxes(4, 5, 6, 3, 4, 5));
    assertEquals(FIRST_LARGER, BoxComparator.compareBoxes(10, 10, 10, 9, 9, 9));
    assertEquals(FIRST_LARGER, BoxComparator.compareBoxes(2, 3, 4, 1, 2, 3));
    assertEquals(FIRST_LARGER, BoxComparator.compareBoxes(5, 2, 8, 6, 1, 4));
  }

  @Test
  void testIncomparableBoxes() {
    assertEquals(INCOMPARABLE, BoxComparator.compareBoxes(1, 8, 3, 2, 2, 7));
    assertEquals(INCOMPARABLE, BoxComparator.compareBoxes(5, 2, 1, 4, 3, 1));
    assertEquals(INCOMPARABLE, BoxComparator.compareBoxes(10, 1, 1, 2, 2, 2));
    assertEquals(INCOMPARABLE, BoxComparator.compareBoxes(2, 3, 5, 4, 2, 4));
  }

  @ParameterizedTest
  @CsvSource({
      "1, 2, 3, 1, 2, 3, EQUAL",
      "1, 2, 3, 3, 2, 1, EQUAL",
      "1, 1, 1, 2, 2, 2, FIRST_SMALLER",
      "3, 3, 3, 2, 2, 2, FIRST_LARGER",
      "1, 8, 3, 2, 2, 7, INCOMPARABLE",
      "2, 3, 5, 4, 2, 4, INCOMPARABLE"
  })
  void testParameterized(int a1, int b1, int c1, int a2, int b2, int c2, BoxComparisonResult expected) {
    assertEquals(expected, BoxComparator.compareBoxes(a1, b1, c1, a2, b2, c2));
  }

  @Test
  void testEdgeCases() {
    assertEquals(FIRST_SMALLER, BoxComparator.compareBoxes(1, 1, 1, 1000, 1000, 1000));
    assertEquals(EQUAL, BoxComparator.compareBoxes(1000, 1000, 1000, 1000, 1000, 1000));
    assertEquals(INCOMPARABLE, BoxComparator.compareBoxes(1000, 1, 1, 500, 500, 500));
  }

  @Test
  void testWithRotation() {
    assertEquals(EQUAL, BoxComparator.compareBoxes(2, 3, 1, 1, 2, 3));
    assertEquals(FIRST_SMALLER, BoxComparator.compareBoxes(2, 3, 4, 5, 3, 4));
    assertEquals(FIRST_LARGER, BoxComparator.compareBoxes(5, 4, 6, 4, 3, 5));
  }
}