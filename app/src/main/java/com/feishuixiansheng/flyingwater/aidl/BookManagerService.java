package com.feishuixiansheng.flyingwater.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author dupengfei
 * @create 2019/2/26 0026
 * @Describe
 */
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import android.os.Binder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class BookManagerService extends Service {

    private static final String TAG = "BMS";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
//    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList =
////            new CopyOnWriteArrayList<IOnNewBookArrivedListener>();

    //解决 not found ，can not unregister
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<IOnNewBookArrivedListener>();

    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
//            SystemClock.sleep(5000);
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }


//        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
//                throws RemoteException {
//            int check = checkCallingOrSelfPermission("com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE");
//            Log.d(TAG, "check=" + check);
//            if (check == PackageManager.PERMISSION_DENIED) {
//                return false;
//            }
//
//            String packageName = null;
//            String[] packages = getPackageManager().getPackagesForUid(
//                    getCallingUid());
//            if (packages != null && packages.length > 0) {
//                packageName = packages[0];
//            }
//            Log.d(TAG, "onTransact: " + packageName);
//            if (!packageName.startsWith("com.ryg")) {
//                return false;
//            }
//
//            return super.onTransact(code, data, reply, flags);
//        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener)
                throws RemoteException {
            mListenerList.register(listener);
//
//            final int N = mListenerList.beginBroadcast();
//            mListenerList.finishBroadcast();
//            Log.d(TAG, "registerListener, current size:" + N);

            //CopyOnWriteArrayList时候
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener);
//            } else {
//                Log.e(TAG, "already exists.");
//            }
//            Log.e(TAG, "registerListener,size:" + mListenerList.size());
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener)
                throws RemoteException {
            boolean success = mListenerList.unregister(listener);
//
//            if (success) {
//                Log.d(TAG, "unregister success.");
//            } else {
//                Log.d(TAG, "not found, can not unregister.");
//            }
//            final int N = mListenerList.beginBroadcast();
//            mListenerList.finishBroadcast();
//            Log.d(TAG, "unregisterListener, current size:" + N);

            //CopyOnWriteArrayList时候
//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener);
//                Log.e(TAG, "unregister listener succeed.");
//            } else {
//                Log.e(TAG, "not found,can not unregister.");
//            }
//            Log.e(TAG, "unregisterListener,current size:" + mListenerList.size());
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
//        int check = checkCallingOrSelfPermission("com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE");
//        Log.d(TAG, "onbind check=" + check);
//        if (check == PackageManager.PERMISSION_DENIED) {
//            return null;
//        }
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private void onNewBookArrived(Book book) throws RemoteException {
//        mBookList.add(book);
//        Log.e(TAG, "onNewBookArrived,notify listeners:" + mListenerList.size());
//        for (int i = 0; i < mListenerList.size(); i++) {
//            IOnNewBookArrivedListener listener = mListenerList.get(i);
//            Log.e(TAG, "onNewBookArrived,notify listener:" + listener);
//            listener.onNewBookArrived(book);
//        }
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            // do background processing here.....
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
