//: c14:TabbedPane1.java
// Demonstrates the Tabbed Pane.
// <applet code=TabbedPane1 width=350 height=200></applet>

import java.awt.Panel;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
public class TapPanel extends Panel
{
TapPanel(chatClient1 app)
{
	  // Initialize the Components
	  chatclient = app;	
	  // Main Panel
	  RightPanel = new CPanel(300, 500);
	    	    
      Panel panel2 = new Panel();
      CardLayout cardlayout = new CardLayout();
      panel2.setLayout(cardlayout);
      UserPanel = new Panel();	  
      UserPanel.add("Center",new Label("User"));
	  	  	
      ImgScrollPanel = new Panel();
	  ImgScrollPanel.setLayout(new BorderLayout());
	         
      BuddyPanel = new Panel();
	  BuddyPanel.setLayout(new BorderLayout());
	  
	  RoomPanel = new Panel();
	  RoomPanel.setLayout(new BorderLayout());
	  
	  panel2.add("UserPanel", UserPanel);
	  panel2.add("RoomPanel", RoomPanel); 
      panel2.add("ImgScrollPanel", ImgScrollPanel); 
      panel2.add("BuddyPanel", BuddyPanel);

      cardlayout.show(panel2, "UserPanel");

      RightTab = new BorderPanel(this,chatclient, cardlayout, panel2, 210, 270);
	  
      RightTab.addTab("USERS", "UserPanel");
      RightTab.addTab("ROOMS", "RoomPanel"); 
      RightTab.addTab("IMAGES", "ImgScrollPanel");
      RightTab.addTab("BUDDY", "BuddyPanel");
      RightPanel.add(RightTab);
      add(RightPanel);  
}

Panel LeftPanel,BuddyPanel,RoomPanel,ImgScrollPanel,UserPanel;
CPanel RightPanel;
BorderPanel RightTab;
chatClient1 chatclient;
public int i;
}













