package org.watzlawek.contactmanager;


import java.util.*;
import org.watzlawek.*;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;  


public class ContactDatabaseHandler extends SQLiteOpenHelper{

	// private static String DB_PATH = "/data/data/org.watzlawek.contactmanager/databases/";
	// private SQLiteDatabase ContactDB;
	
	private static final String DATABASE_NAME = "ContactDB";
	private static final int DATABASE_VERSION = 1;
	private static final String DB_TABLE_NAME = "contacts";
	private static final String DB_COLUMN_JID = "jid";
	private static final String DB_COLUMN_USERNAME = "username";
	private static final String DB_COLUMN_NOTE = "note";
	private static final String DB_COLUMN_SERVERID = "serverid";
	private static final String DB_COLUMN_INVISIBLE = "invisible";
	

	private class Contact{
		public String jid;
		public String username;
		public String note;
		public int serverID;
		public boolean visible;
		
		public Contact(String inJID, String inUsername, String inNote, int inSID, boolean inVisible) {
			jid = inJID;
			username = inUsername;
			note = inNote;
			serverID = inSID; // in der DB haben wir das "id" klein geschrieben
			visible = inVisible;
		}
	}

	public ContactDatabaseHandler(android.content.Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(android.database.sqlite.SQLiteDatabase db) {
		String createDB = "CREATE TABLE " +DB_TABLE_NAME +"(" +
				DB_COLUMN_JID +" VARCHAR(30) PRIMARY KEY,"+
				DB_COLUMN_USERNAME +" VARCHAR(30)" +
				DB_COLUMN_NOTE +" VARCHAR(140),"+
				DB_COLUMN_SERVERID + " INTEGER"+
				DB_COLUMN_INVISIBLE +" INTEGER)";
		db.execSQL(createDB);
	}

	/**
	 * 
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}


	
	private void open_db() throws SQLException{ // oeffnet oder erstellt die Datenbank
	//	SQLiteDatabase db = SQLiteDatabase.openDatabase(DATABASE_NAME,
		//		null, SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		
		/**
		 *  String contactPath = DB_PATH + DB_NAME;
			contactDataBase = SQLiteDatabase.openDatabase(contactPath, null, SQLiteDatabase.OPEN_READONLY);
			READONLY ist doof, das m�ssen wir noch �ndern
		 */
		//oder 
		/** private MySQLiteHelper dbHelper;
		 *  private SQLiteDatabase database;
		 * database = dbHelper.getWritableDatabase();
		 */
		
		
	}

	
	
	private void close_db() { 
		// TODO - implement ContactDatabaseHandler.close_db
		throw new UnsupportedOperationException();
		
		//Class: "SQLiteClosable" 	An object created from a SQLiteDatabase that can be closed.
		// http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
		// oder
		/**
		 * private MySQLiteHelper dbHelper;
		 *  dbHelper.close();
		 */
	}

	
	
	/**
	 * This method compares the contacts on the server(serverID) with the 
	 * corresponding contacts in the DB. It pulls the contacts of the DB
	 * and compares then.
	 * @param contacts
	 */
	public void compareContacts(Vector<IMChat> contacts, int serverID) {
		/*Vector<String> jidList = read_jids();
		Vector<Integer> sID = getServerIDs(); */
		Vector<Contact> dbContacts = getDBContacts();
		
		// Create a Vector<Contact> where only contacts are saved,
		// which are visible and available on the currently connected server
		Vector<Contact> neededContacts = new Vector<Contact>();
		for (Contact c : dbContacts){
			if (c.serverID == serverID && c.visible == true) 
				neededContacts.add(c);
		}
		
		// Create a new Vector<Contact>, which extracts all important 
		// information of IMChat and stores it. 
		Vector<Contact> serverContacts = new Vector<Contact>();
		for (IMChat c : contacts) {
			Contact contact = new Contact(((XMPPChat)c).get_jid(), c.get_username(), c.get_note(), serverID, c.isVisible());
		}
		// Now the comparison can start: 
		
	}

	
	private Vector<Contact> getDBContacts() {
		// This is the real reading-method with DB access.
		// ...
		return new Vector<Contact>(); // Dummy
	}
	
	
	public Vector<String> getJIDsUsernames(int serverID) {
		Vector<Contact> dbContacts = getDBContacts();
		/* 
		 * Here must be filtered: Is the contact in the ServerID, which is 
		 * requesting this List and is the contact visible?
		 */
		return new Vector<String>(); // Dummy
	}
	
	
	
	/**
	 * This method insert a given contact into the DB if it doesn't already exist.
	 * 
	 * @param in_jid
	 * @param in_username
	 * @param in_note
	 * @param in_SID
	 * @param in_invisible
	 * 
	 */
	private void insertContact(String in_jid, String in_username, String in_note, int in_SID, boolean in_invisible) {
		//private SQLiteStatement  statement = null;       // SQL Anweisung
	    //private ...  result   = null;       		// SQL Ergebnis
		
		//throw new UnsupportedOperationException();
		//https://www.youtube.com/watch?v=dOA8RkTr5AI
	}

	
	private Vector<String> read_jids(int in_SID) {
		// TODO - implement ContactDatabaseHandler.read_jids
		throw new UnsupportedOperationException();
		//Vector<String> jid_vector;
		// "Select jid from contacts where serverid = " +in_SID
		
		//return jid_vector;
	}
	
	
	/**
	 * 
	 * @param in_jid
	 * @param in_username
	 * @param in_note
	 * @param in_invisible
	 */
	private void updateContact(String in_jid, String in_username, String in_note, int in_invisible) {
		// TODO - implement ContactDatabaseHandler.updateContact
		throw new UnsupportedOperationException();
	}

}
