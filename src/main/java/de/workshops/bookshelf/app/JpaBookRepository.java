package de.workshops.bookshelf.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JpaBookRepository extends JpaRepository<Book, Long> {

    Book findByAuthorLike(String name);

    @Query(value = "select b from Book b where b.isbn =?1")
    Book findByIsbn(String isbn);
}
