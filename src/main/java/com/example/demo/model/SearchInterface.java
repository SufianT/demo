package com.example.demo.model;

import java.util.ArrayList;

interface SearchInterface {

      public default ArrayList<Book> SearchByAuthor(String author){
          return null;
      }
      public default ArrayList<Book>  SearchBytitle(String title){

          return null;
      }
     public default ArrayList<Book>  SearchByISBN(int ISBN){
         return null;
     }

}
