# book-catalog
Trying a Book Catalog application



### Contents --> whats in this repo
* Application will be in Java
* idea is to have a central data model, services and feature, contained in the application
* the application will have different client-interfaces
* Command line interface
* webapp interface
* mobile-app interface
* the code will be structured to show organization of model, respository, services
* code will have test case coverage

### Book-catalog features
* start with default catalog, populated with four books
* ability to reset data to above four.
* CRUD features
  * ability to add books to the catalog
  * ability to update books in the catalog
  * ability to remove books from the catalog
* books can be owned-by-self, borrowed-from-others, lend-to-others
* book-listing features -->
  * book attributes: title, author, genre, isdn-code, owned-by-name, borrowed-from-name, borrowed-on-date, lend-to-name, lend-on-date 
  * search books by title or author or few more above attributes
* each-book features -->
  * on each book, have attributes for actively-reading-flag, number-of-pages-read, notes-documented-flag, notes-short-description, summary-presented-to, summary-presented-on-date
* any other features -- to be decided.


### Java usage
* use gradle to manage dependencies
* gradle init is using config 4
* for building use ```gradle clean build```
* to run test classes use ```gradle test```
* to run the code or main class use ```gradle --quiet --console=plain run```

