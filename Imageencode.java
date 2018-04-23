/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encodinganddecoding;

/**
 *
 * @author ADMIN
 */

import java.util.Scanner;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
public class Imageencode {

	/**
	 * @param args
     * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
         Scanner sc = new Scanner(System.in);
             String imagepath ="/home/ubuntu/Pictures/comparing/boot.png";
             System.out.println("enter the message");
             String message =sc.next();   
             File f = new File(imagepath);
             BufferedImage src = ImageIO.read(f);
             BufferedImage img = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                                       img.getGraphics().drawImage(src, 0, 0, null);
             File copy = new File("/home/ubuntu/Pictures/boot1.png");
             ImageIO.write(img, "png", copy);
             int count=0,length= 4 * message.length(),msglen=message.length();
             int msgbit;
             char a;
             int i,j,mod=0,bitint,pixel;
             msgbit=msglen>>2;
             pixel=img.getRGB(0,0);
             pixel=pixel & 0xFFF8F8FC | msgbit;
             System.out.println("bit3 and4 is "+msgbit);
             img.setRGB(0,0,pixel);
             msgbit=msglen & 3;
             pixel=img.getRGB(0,1);
             pixel=pixel & 0xFFF8F8FC | msgbit;
             System.out.println("msgbit12 is  "+msgbit);
             img.setRGB(0,1,pixel);
            label: for(i=0;i<img.getHeight();i++)
             {
            	 for(j=0;j<img.getWidth();j++)
            	 {
                         if(!(i==0 && (j==0||j==1)))
                         {
            		 
            		 
            		 pixel=img.getRGB(i, j);
            		 a=message.charAt(count);
            	     bitint = (a>>(6-mod))& 0x3;
            	     pixel=( pixel & 0xFFF8F8FC)|bitint ; 
                     mod+=2;
            	     if(mod==8)
            	     {
            	    	 mod=0;
            	    	 count++;
            	     }
            	     img.setRGB(i, j,pixel);
                     System.out.println("inside value before save value "+(pixel&3));
                     System.out.println("inside value after save "+(img.getRGB(i,j)& 0x3));
                     
            		 }
            		 if(count==message.length())
            		 {
            		 break label;
            		 }
                     
            	 }
             }
             File encryption = new File("/home/ubuntu/Pictures/bootencoded.png");
             ImageIO.write(img, "png",encryption);
             BufferedImage img2 = ImageIO.read(encryption);
             System.out.println("image saved    \n \n \n");
             int count3=0;
   loop1:    for(i=0;i<img2.getHeight();i++)
             {
                 for(j=0;j<img2.getWidth();j++)
                 {
                     if(!(i==0&&j<=1))
                     {
                         pixel=img2.getRGB(i, j);
                         System.out.println("bit after save"+(pixel& 0x3));
                         count3++;
                     }
                     if(count3>=length)
                     {
                         break loop1;
                      }
                 }
                 
             }
   System.out.println("\n \n \n \n");
   count3=0;
   BufferedImage img3 = ImageIO.read(new File("/home/ubuntu/Pictures/comparing/boot.png"));
    loop2:    for(i=0;i<img3.getHeight();i++)
             {
                 for(j=0;j<img3.getWidth();j++)
                 {
                     if(!(i==0&&j<=1))
                     {
                         pixel=img3.getRGB(i, j);
                         System.out.println("bit first "+(pixel& 0x3));
                         count3++;
                     }
                     if(count3>=length)
                     {
                         break loop2;
                      }
                 }
             }
	} /*C:\\Users\\ADMIN\\Pictures\\rain lighting.png*/
}

