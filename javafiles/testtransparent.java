//When loading images, load them in the following manner...
Image image = getImage(getDocumentBase(), "http://127.0.0.1/example.gif);
waitForImage(image, this);
BufferedImage bufferedImage = getBufferedImage(image, this);
 
//In your main loop, when drawing the graphic, add something like this
//where x and y are coordinates where the image was drawn
 
if (mouseCollides(image, x, y) == true)
{
    //reaction to the mouse being collided with the shape in the image
    System.out.println("We have a collision here");
}
 
 
//Include the following 3 methods
 
//This method detects the alpha of the given pixel and returns true IF the pixel is NOT transparent. 
 
public boolean mouseCollides(BufferedImage image, int imageX, int imageY)
      {
      
         boolean result = false;
      	
         if ( (mouseX >= imageX) && (mouseX < imageX+image.getWidth(this)) && (mouseY >= imageY) && (mouseY < imageY+image.getHeight(this)) )
         {
            int alpha = (image.getRGB(mouseX-imageX, mouseY-imageY) >> 24) & 0xff; 					
            if (alpha == 0) { result = false; } 
            else {result = true; }
         }
      	
         return result;
      
      }
   	
   //This method was extracted from the url above, and simply creates a mediatracker object to wait until the image has been downloaded
       public static boolean waitForImage(Image image, Component c) 
      { 
         MediaTracker tracker = new MediaTracker(c); 
         tracker.addImage(image, 0); 
         try { 
            tracker.waitForAll(); 
         } 
             catch(InterruptedException ie) 
            {} 
         return(!tracker.isErrorAny()); 
      } 
   
   //this method was also extracted from the url above, and is used to create a normal image, into a bufferedimage. I have altered
//it to suit my program, the type of the image being TYPE_4BYTE_ABGR
       public static BufferedImage getBufferedImage(Image image, Component c) 
      { 
      
         BufferedImage bufferedImage = new BufferedImage(image.getWidth(c), image.getHeight(c), BufferedImage.TYPE_4BYTE_ABGR); 
         Graphics2D g2d = bufferedImage.createGraphics(); 
         g2d.drawImage(image, 0, 0, c);
         return(bufferedImage); 
      } 
 
 
