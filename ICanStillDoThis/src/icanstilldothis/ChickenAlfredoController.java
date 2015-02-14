/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icanstilldothis;

import constant.Region;
import dto.League.League;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.java.riotapi.RiotApi;
import java.util.*;
import com.google.gson.*;
import main.java.riotapi.RiotApi;
import main.java.riotapi.RiotApiException;
import constant.Region;
import dto.Summoner.*;
import dto.League.*;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
/**
 * FXML Controller class
 *
 * @author Awkbak
 */
public class ChickenAlfredoController implements Initializable {
    private HashMap<String, Summoner> allSumms = new HashMap<>();
    private Summoner current = new Summoner();
    private String selected = "";
    @FXML 
    private TabPane tbPane;
    @FXML
    private Tab SummManager;
    @FXML
    private AnchorPane sMan;
    @FXML
    private AnchorPane tMan;
    @FXML
    private Tab TeamManager;
    @FXML
    private Label lblRank;
    @FXML
    private Label lblNWins;
    @FXML
    private Label lblMMR;
    @FXML
    private Label lblMessage;
    @FXML
    private Button btnSmit;
    @FXML
    private Button btnRmv;
    @FXML
    private TextField txtSumm;
    @FXML
    private ProgressIndicator impProc;
    @FXML
    private ProgressIndicator impProg;
    @FXML
    private ListView summList;
    @FXML
    private void remSumm(){
        
    }
    @FXML
    private void remvClick(){
        String j = summList.getSelectionModel().getSelectedItem().toString();
        summList.getItems().remove(summList.getSelectionModel().getSelectedItem());
        allSumms.remove(j);
    }   
    @FXML
    private void handleClick() throws RiotApiException, InterruptedException{
        impProc.setVisible(true);
        impProg.setVisible(false);
        String f = txtSumm.getText();
        current = new Summoner(f);
        String replaceAll = f.replaceAll("\\s+", "");
        f = replaceAll;
        replaceAll = f.toLowerCase();
        f = replaceAll;
        System.out.println(f);
        final String i = f;
        
        CompletableFuture<Summoner> F = CompletableFuture.supplyAsync(() ->{
            current = getInfo(i);
            return null;
        });
        F.whenComplete((ok,ex)->{
            Platform.runLater(()->{
                finished();
                update();
            });
        });
        
    }
    public void finished(){
        allSumms.put(current.getName(), current);
        summList.getItems().add(current.getName());
        summList.getSelectionModel().select(current.getName());
        impProg.setVisible(true);
        impProc.setVisible(false);
    }
    public Summoner getInfo(String m){
    String imp = "";
    RiotApi mykey = new RiotApi("c9e2460d-dd6b-4ab3-8991-a815b8d01a57");
    try{
        dto.Summoner.Summoner summo = mykey.getSummonersByName(Region.NA, m).get(m);
        String p = summo.toString();
        long id = summo.getId();
        Map<String, List<League>> league = mykey.getLeagueEntryBySummoners(id);
        imp = league.get(""+id).get(0).getTier();
        switch(imp){
            case "BRONZE": current.Material.setTier(0);
                break;
            case "SILVER": current.Material.setTier(1);
                break;
            case "GOLD": current.Material.setTier(2);
                break;
            case "PLATINUM": current.Material.setTier(3);
                break;
            case "DIAMOND": current.Material.setTier(4);
                break;
            case "MASTER": current.Material.setTier(5);
                break;
            case "CHALLENGER": current.Material.setTier(6);
                break;
            default: current.Material.setTier(7);
                break;
        }
        imp = league.get(""+id).get(0).getEntries().get(0).getDivision();
        switch(imp){
            case "V":
                current.Material.setDivision(4);
                break;
            case "IV":
                current.Material.setDivision(3);
                break;
            case "III":
                current.Material.setDivision(2);
                break;
            case "II":
                current.Material.setDivision(1);
                break;
            case "I":
                current.Material.setDivision(0);
                break;
            default:
                current.Material.setDivision(-1);
                break;
        }
        current.ErrorMessage = "";
    }catch(Exception e){
        current.Material.setTier(7);
        current.Material.setDivision(-1);
        current.ErrorMessage = e.toString();
    }
    return current;
}
    /**
     * Initializes the controller class.
     */
    public void update(){
        lblMessage.setText("Request Message: " + current.ErrorMessage);
        lblRank.setText("Rank: "+current.Material.getTierName()+" "+current.Material.getDivName());
        lblNWins.setText("Normal Wins: "+ current.NormalWins);
        lblMMR.setText("MMR: " + current.getMMR());
    }
    
    public void selectObj(){
        String select;
        
        if(!selected.equals(summList.getSelectionModel().getSelectedItem())){
            current = allSumms.get(""+summList.getSelectionModel().getSelectedItem());
            update();
        }
    }
    
    public ChickenAlfredoController(){
        
    }
    
    private ICanStillDoThis mainApp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setMainApp(ICanStillDoThis mainapp ){
        this.mainApp = mainapp;
    }
    
}
