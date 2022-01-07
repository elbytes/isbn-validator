package com.el.isbntools;

public class StockManager {

    private ExternalIsbnService webService;
    private ExternalIsbnService databaseService;

    public void setWebService(ExternalIsbnService service) {
        this.webService = service;
    }

    public void setDatabaseService(ExternalIsbnService databaseService) {
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);
        if (book == null) book = webService.lookup(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4));
        locator.append(book.getAuthor().substring(0, 1));
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
