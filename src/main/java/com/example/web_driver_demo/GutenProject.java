package com.example.web_driver_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GutenProject {
    private final MyWebDriver myDriver;
    private static final int MAX_BOOK_ID = 19446;
    private List<Book> tenLongestBooks;

    GutenProject(){
        //https://www.gutenberg.org/cache/epub/19446/pg19446.txt
        myDriver = new MyWebDriver();
    }
    public List<Integer> getTenLongestBooksIds() {
        tenLongestBooks = new ArrayList<>(10);

        getTenBooksIdsWithSize();
        sortBooksBySize();
        getRestBooksAndReplaceBiggerThanSmallestOfAlreadyFounded();

        tenLongestBooks.stream().forEach(System.out::println);

        return tenLongestBooks.stream().map(b->b.id).collect(Collectors.toList());
    }

    private void getRestBooksAndReplaceBiggerThanSmallestOfAlreadyFounded() {
        for (int id = 1201; id <= MAX_BOOK_ID; id++) { 
            String url = getUrlFromId(id);
            myDriver.getPage(url);
            Book currBook = new Book(id, myDriver.getPageSize());
            if(currBook.size> tenLongestBooks.get(0).size){
                tenLongestBooks.set(0,currBook);
                sortBooksBySize();
            }
        }
    }

    private void sortBooksBySize() {
        tenLongestBooks.sort(Comparator.comparing(b -> b.size));
    }

    private void getTenBooksIdsWithSize() {
        tenLongestBooks = new ArrayList<>( Arrays.stream(new Book[]{

                // result for first 1200 pages
                new Book(660, 3138998),
                new Book(669, 3153803),
                new Book(668, 3193109),
                new Book(135, 3254640),
                new Book(674, 4245248),
                new Book(10, 4352389),
                new Book(30, 5104786), // The Bible, King James Version, Complete
                new Book(100, 5468011), // The Complete Works of William Shakespeare
                new Book(200, 8168634), // The Project Gutenberg Gutenberg Encyclopedia, Vol 1
                new Book(673, 25005380), // error
        }).collect(Collectors.toList()) );
    }

    private String getUrlFromId(int id) {
        //https://www.gutenberg.org/cache/epub/19446/pg19446.txt
        String template = "https://www.gutenberg.org/cache/epub/%s/pg%s.txt";
        return String.format(template, id, id);
    }

    class Book{
        Integer id;
        Integer size;

        public Book(Integer id, Integer size) {
            this.id = id;
            this.size = size;
        }

        @Override
        public String toString() {
            String template = "new Book(%s, %s),";
            return String.format(template, id, size);//
        }
    }
}
