package com.el.isbntools;

public class StockManager {

    private ExternalIsbnService service;

    public void setService(ExternalIsbnService service) {
        this.service = service;
    }

    public String getLocatorCode(String isbn) {
        Book book = service.lookup(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4));
        locator.append(book.getAuthor().substring(0, 1));
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
