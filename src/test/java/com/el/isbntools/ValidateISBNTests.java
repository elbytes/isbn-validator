package com.el.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ValidateISBNTests {
   @Test
    public void checkAValid10DigitIsbn(){
       ValidateISBN validator = new ValidateISBN();
       boolean result = validator.checkIsbn("0140449116");
       assertTrue("first value ", result);
       result = validator.checkIsbn("0140177396");
       assertTrue("second value", result);
   }

   @Test
   public void checkAValid13DigitIsbn(){
       ValidateISBN validator = new ValidateISBN();
       boolean result = validator.checkIsbn("9781985757387");
       assertTrue("first value", result);
       result = validator.checkIsbn("9780062316110");
       assertTrue("second value", result);
   }

   @Test
    public void checkAnInvalid10DigitIsbn(){
       ValidateISBN validator = new ValidateISBN();
       boolean result = validator.checkIsbn("0140449117");
       assertFalse(result);
   }

   @Test
   public void checkAnInvalid13DigitIsbn(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkIsbn("9780735224897");
        assertFalse(result);
   }

   @Test
   public void nineDigitIsbnNotAllowed(){
      ValidateISBN validator = new ValidateISBN();
      assertThrows(NumberFormatException.class,
              () -> {
                 validator.checkIsbn("123456789");
              }
              );
   }

   @Test
    public void nonNumeric10DigitIsbnsNotAllowed(){
      ValidateISBN validator = new ValidateISBN();
      assertThrows(NumberFormatException.class, () -> {
          validator.checkIsbn("helloworld");
      });
   }

   @Test
   public void tenDigitIsbnEndingInAnXAreValid(){
       ValidateISBN validator = new ValidateISBN();
       boolean result = validator.checkIsbn("012000030X");
       assertTrue(result);
   }
}
