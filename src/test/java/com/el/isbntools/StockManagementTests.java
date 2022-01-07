package com.el.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StockManagementTests {
    @Test
    public void testCanGetCorrectLocatorCode(){
        ExternalIsbnService testService = new ExternalIsbnService() {
            @Override
            public Book lookup(String isbn) {
                return new Book("9780062316097", "Sapiens", "Yuval Noah Harari");
            }
        };
        StockManager stockManager = new StockManager();
        stockManager.setService(testService);
        String isbn = "9780062316097";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("6097Y1", locatorCode);
    }
}
