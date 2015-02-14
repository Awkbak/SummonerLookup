/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icanstilldothis;
/**
 *
 * @author Awkbak
 */
public class Summoner {
    private String Name;
    public String ErrorMessage;
    public Rank Material;
    public int NormalWins;
    
    public Summoner(){
        ErrorMessage = "";
        Name = "";
        Material = new Rank();
        NormalWins = 0;
    }
    public Summoner(String k){
        ErrorMessage = "";
        Name = k;
        Material = new Rank();
        NormalWins = 0;
    }
    
    public int getMMR(){
        if (Material.getRank() == -1){
            if (NormalWins > 180){return (NormalWins-180);}else return 0;
        }
        return Material.getRank();
    }
    public void setName(String s){
        Name = s;
    }
    public String getName(){
        return Name;
    }
    
}
