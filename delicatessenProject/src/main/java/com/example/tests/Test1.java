package com.example.tests;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.beans.Book;
import com.example.beans.Category;
import com.example.beans.Product;
import com.example.exceptions.IllegalProductPriceException;
import com.example.exceptions.expirationDateException;
import com.example.exceptions.productDuplicationException;
import com.example.facaeds.AdminFacade;

public class Test1 {

	@Autowired

	private static  AdminFacade facade;

	 public static void main(String[] args) {
//		 Product p = new Book("fontanela", 97, Category.books, 25, "meir shalev", 2007, "heb");
//			try {
//				System.out.println( facade.addProduct(p));
//			} catch (productDuplicationException | expirationDateException | IllegalProductPriceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}

//	public void add() {
//		Product p = new Book("fontanela", 97, Category.books, 25, "meir shalev", 2007, "heb");
//		try {
//			System.out.println(facade.addProduct(p));
//		} catch (productDuplicationException | expirationDateException | IllegalProductPriceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}

