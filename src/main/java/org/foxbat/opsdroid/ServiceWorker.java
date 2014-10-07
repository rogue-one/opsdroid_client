package org.foxbat.opsdroid;

import android.content.Context;
import android.net.ConnectivityManager;
import org.foxbat.opsdroid.model.OpswiseMasterManager;
import org.foxbat.opsdroid.rest.RequestDispatcher;
import org.foxbat.opsdroid.rest.UrlSynthesizer;
import org.foxbat.opsdroid.utils.AppObjectRepository;
import org.json.JSONArray;

/**
 * Created by chlr on 9/29/14.
 */
public class ServiceWorker {


    public void refreshDataForDate(int datekey) {
        ConnectivityManager cm = (ConnectivityManager)AppObjectRepository.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected() == true) {
            JSONArray arr = (new RequestDispatcher()).getJsonArrayResponse(new UrlSynthesizer().task_date(datekey));
            OpswiseMasterManager ops_master_mgr = new OpswiseMasterManager();
            ops_master_mgr.deleteDate(datekey);
            ops_master_mgr.populateTable(arr);
        }
    }





}