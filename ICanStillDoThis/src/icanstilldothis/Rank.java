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
public class Rank {
    private final String[] TierName;
    private int Tier;
    private int ADivision;
    private final int[] Division;
    private int RankValue;
    
    public Rank(){
        TierName =  new String[]{"Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Challenger", "Unranked"};
        Tier = 0;
        ADivision = 0;
        Division = new int[]{160,130,90,40,0};
        RankValue = 0;
    }
    public String getTierName(){
        return TierName[Tier];}
    public void setTier(int i){
        Tier = i;}
    public void setDivision(int o){
        ADivision = o;
    }
    public String getDivName(){
        if((Tier==5) || (Tier == 6)) return "I";
        switch(ADivision){
            case 0: return "I";
            case 1: return "II";
            case 2: return "III";
            case 3: return "IV";
            case 4: return "V";
        } 
        return "";
    }
    public int getRank(){
        double multi = 0.0;
        double divValue = 0.0;
        switch(Tier){
            case 0:
                multi = 0.8;
                divValue = 0;
                break;
            case 1:
                multi = 0.5;
                divValue = 200;
                break;
            case 2:
                multi = 1;
                divValue = 400;
                break;
            case 3:
                multi = 1.5;
                divValue = 600;
                break;
            case 4:
                multi = 2;
                divValue = 800;
                break;
            case 5:
                multi = 0;
                divValue = 1200;
                break;
            case 6:
                multi = 0;
                divValue = 1500;
                break;
            case 7:
                return -1;
        }
        double dies = divValue + multi * Division[ADivision];
        RankValue = (int) Math.round(dies);
        return RankValue;
    }
}
