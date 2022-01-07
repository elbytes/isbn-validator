package com.el.isbntools;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StockManagementTests {
    ExternalIsbnService testWebService;
    ExternalIsbnService testDatabaseService;
    StockManager stockManager;

    @Before
    public void setup(){
        testWebService = mock(ExternalIsbnService.class);
        testDatabaseService = mock(ExternalIsbnService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void testCanGetCorrectLocatorCode(){
        when(testWebService.lookup(anyString())).thenReturn(new Book("9780062316097", "Sapiens", "Yuval Noah Harari"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String isbn = "9780062316097";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("6097Y1", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){
        when(testDatabaseService.lookup("9780062316097")).thenReturn(new Book("9780062316097", "ab c", "xyz"));

        String isbn = "9780062316097";
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testDatabaseService).lookup("9780062316097");
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase(){

        when(testDatabaseService.lookup("9780062316097")).thenReturn(null);
        when(testWebService.lookup("9780062316097")).thenReturn(new Book("9780062316097", "ab c", "xyz"));

        String isbn = "9780062316097";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup(isbn);
        verify(testWebService).lookup(isbn);
    }
}
