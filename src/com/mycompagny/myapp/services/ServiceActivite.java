/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompagny.myapp.entities.Activite;

import com.compagny.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceActivite {
    public ArrayList<Activite> activites;
    
    public static ServiceActivite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceActivite() {
          req = new ConnectionRequest();
    }
    
    public static ServiceActivite getInstance(){
         if (instance == null) {
            instance = new ServiceActivite();
        }
        return instance;
    }
    
    public ArrayList<Activite> parseActivites(String jsonText){
                try {

                    System.out.println(jsonText);
            activites=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            
            for(Map<String,Object> obj : list){
                Activite a = new Activite();
                                
                float id = Float.parseFloat(obj.get("idA").toString());
                a.setId_a((int) id);
                a.setNom_a(obj.get("nomA").toString());
                float cat = Float.parseFloat(obj.get("catAge").toString());
                a.setCat_age((int) cat);
                a.setType(obj.get("type").toString());
                a.setImage(obj.get("image").toString());
                //float id_e = Float.parseFloat(obj.get("idEnfant").toString());
                //a.setId_enfant((int) id_e);
                               
                activites.add(a);


            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        }
        return activites;
    }
    
    public ArrayList<Activite> getAllActivites(){
        String url = Statics.BASE_URL+"activite/mobile/aff";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                activites = parseActivites(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return activites;
    }
    
    public ArrayList<Activite> getUser(int id){
        String url = Statics.BASE_URL+"/activite/mobile/?id_a="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                activites = parseActivites(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return activites;
    }
    
    public boolean addActivite(Activite u) {
       String ch ="0308ab928edd715bd261ef1af50e0b91.jpeg";
        String url = Statics.BASE_URL + "activite/mobile/new?nom_a="+u.getNom_a()+"&cat_age="+u.getCat_age()+"&type="+u.getType()+"&image="+ch+"&id_enfant="+11; //création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
            public boolean editActivite(Activite u) {
                String ch= "0308ab928edd715bd261ef1af50e0b91.jpeg";
        String url = Statics.BASE_URL + "/activite/UpdateActiviteJSON?id_a="+u.getId_a()+"&nom_a="+u.getNom_a()+"&cat_age="+u.getCat_age()+"&image="+ch+"&id_enfant="+11; //création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
            
             public boolean deleteActivite(int id_a) {
        String url = Statics.BASE_URL + "/activite/DeleteActiviteJSON?id_a=" + id_a;
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
}
