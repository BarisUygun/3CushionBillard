import java.awt.Color;


public class cues extends cue {
	
     public Color cueColor;
     
    public cues(Color col,int speed){
    	 cueColor=col;
    	 cueSpeed=speed;
     }
    public void setCueColor(Color cl){
    	cueColor=cl;
    }
    public void setCueSpeed(int spd){
    	cueSpeed=spd;
    }

}
