package org.watzlawek.views.activities;

import org.watzlawek.R;
import org.watzlawek.views.adapters.GroupadminContactAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Activity to display the groupadministration.
 * 
 * @author Safran Quader
 * 
 * @version 2015-01-09
 */ 

                   // zu tun: h�ckchen setzen, speichern, abbrechen
public class GroupadminActivity extends Activity {
	
//	private grouplist lokallist;
	private String name="blubb";
	private int groupnumber;
	@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupadmin);
        Bundle extras = getIntent().getExtras();
        groupnumber = extras.getInt("groupnr");
       // name = ?;
        GroupadminContactAdapter gAdapter = new GroupadminContactAdapter(getBaseContext());
        gAdapter.setname(name);
        
        
        ListView listView = (ListView) findViewById(R.id.groupadmin_contactlist);
        listView.setAdapter(gAdapter);
        // h�ckchen setzen   wird togglebutton gedr�ckt?
        listView.setOnItemClickListener(new OnItemClickListener() 
        {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			//	angeklickten kontakt in lokale liste �bernehmen
				// oder aus lokaler liste herausnehmen
				//togglebutton geklickt?
			}
		});
        
        //button "speichern"
        Button safebutton = (Button) findViewById(R.id.groupadmin_safe_button);
        safebutton.setOnClickListener(new OnClickListener()                                
        {
			public void onClick(View v) {
				// gruppeneinstellungen �bernehmen
				    //gruppennamen auf text von groupadmin_groupname_edittext setzen
				    //kontaktliste der gruppen mit lokaler liste �berschreiben
				// activity schlie�en und zur alten zur�ckspringen
			}
		});
        
      //button "abbrechen"
        Button cancelbutton = (Button) findViewById(R.id.groupadmin_cancel_button);
        cancelbutton.setOnClickListener(new OnClickListener()                                
        {
			public void onClick(View v) {

				// activity schlie�en und zur alten zur�ckspringen
			}
		});
	} 
	
}