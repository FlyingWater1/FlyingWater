// IBookManager.aidl
package com.feishuixiansheng.flyingwater.aidl;

// Declare any non-default types here with import statements
import com.feishuixiansheng.flyingwater.aidl.Book;
import com.feishuixiansheng.flyingwater.aidl.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}