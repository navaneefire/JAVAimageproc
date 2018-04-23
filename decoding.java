/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encodinganddecoding;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 *
 * @author ubuntu
 */
public class decoding {
    public static void main(String []args) throws IOException
    {
        File f = new File("/home/ubuntu/Pictures/comparing/bootencoded.png");
        BufferedImage img1 =  ImageIO.read(f);
        BufferedImage img = new BufferedImage(img1.getHeight(),img1.getWidth(),BufferedImage.TYPE_4BYTE_ABGR);
        img.getGraphics().drawImage(img1, 0, 0, null);
        int length;
        int pixel;
        pixel=img.getRGB(0,0);
        length=(pixel & 3)<<2;
        img.setRGB(0,0, pixel & 0xFFFFFFFC);
        pixel = img.getRGB(0,1);
        length+= (pixel&3);
        img.setRGB(0, 1, pixel & 0xFFFFFFFC);
        int i,j,value,count=0,count1=0;
        char a;
        System.out.println("length is = "+length);
        StringBuffer msg =new StringBuffer();
        int value12=0;
 loop : for(i=0;i<img.getHeight();i++)
        {
            for(j=0;j<img.getWidth();j++)
            {
            if(!(i==0&&j<2))
                    {
                     pixel=img.getRGB(i,j);
                     value=pixel &3;
                     value12=value12<<2;
                     value12=value12 | value;
                     pixel = pixel & 0xFFFFFFFC;
                     img.setRGB(i, j, pixel);
                     count1++;
                     if(count1==4)
                     {
                         a=(char)value12;
                         msg.append(a);
                         count1=0;
                         count++;
                         value12=0;
                     }
                     if(count==length)
                     {
                         break loop;
                     }    
                    }
            }
        }
        if(msg.length()!=0)
        {
            System.out.println("mesage is = "+ msg);
        }
        else
        {
            System.out.println("error");
        }
        File f1 = new File("/home/ubuntu/Pictures/comparing/decrypted.jpg");
        ImageIO.write(img, "png",f1);
    }
}
