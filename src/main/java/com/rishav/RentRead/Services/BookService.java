package com.rishav.RentRead.Services;


import java.util.*;
import com.rishav.RentRead.Entity.*;
import com.rishav.RentRead.Repository.*;
import com.rishav.RentRead.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private BookRepo bookRepo;


    public List<Book> getallBooks() {
        return bookRepo.findAll();
    }


    public Book create(Book book) {
        if (book.gettitle().trim().isEmpty() || book.getauthor().trim().isEmpty() || book.getgenre().trim().isEmpty()) {
            throw new BlankException("Title/Author/Genre can't be blank");
        }
        return bookRepo.save(book);
    }


    public String delete(long bookId) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            bookRepo.deleteById(bookId);
        } else {
            throw new NotfoundException("Book not found");
        }
        return "Book deleted successfully";
    }


    public Book renttheBook(String email, long bookId) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw new NotfoundException("User not found");
        }
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new NotfoundException("Book not found");
        }
        if(user.get().getaddedBooks().size() == 2){
            throw new BooklimitException("User already have two rented books"); 
        }
        book.get().setavailablity(false);
        user.get().getaddedBooks().add(book.get());
        userRepo.save(user.get());
        bookRepo.save(book.get());
        return book.get();
    }


    public Book returntheBook(String email, long bookId) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw new NotfoundException("User not found");
        }
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new NotfoundException("Book not found");
        }
        if(!userhavetheBook(user.get(), book.get())){
            throw new NotfoundException("Book not rented to the user");  
        }
        if(book.get().getavailablity() == false){
            throw new BooklimitException("The book is already rented out"); 
        }
        book.get().setavailablity(true);  
        userRepo.save(user.get());
        bookRepo.save(book.get());
        return book.get();
    }


    private boolean userhavetheBook(User user, Book book){
        for(int i =0; i < 2; i++){
            if(user.getaddedBooks().get(i).getBookId() == book.getBookId()){
                user.getaddedBooks().remove(i); 
                return true;
            }
        }
        return false;
    }
    
}
