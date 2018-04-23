import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
class imagecompare
{
	public static void main(String[] args) throws Exception
	{
		File imagefold = new File("C:/xampp/htdocs/photo");
		     File[]  filearray =  imagefold.listFiles();
			 File defaultf=new File("C:/xampp/htdocs/photo/default.jpg");
             BufferedImage defaultfimage  =   ImageIO.read(defaultf);
              ByteArrayOutputStream baos = new ByteArrayOutputStream();
              ImageIO.write(defaultfimage,"jpg",baos);
              byte[] bytearray = baos.toByteArray();
             int i=0;
			 int flag=0;
			 for(i=0;i<filearray.length;i++)
			 {
				 flag=0;
				 if(!filearray[i].getAbsoluteFile().equals(defaultf.getAbsoluteFile()))
				 {
				 BufferedImage checkimage  =   ImageIO.read(filearray[i]);
              ByteArrayOutputStream checkbaos = new ByteArrayOutputStream();
              ImageIO.write(checkimage,"jpg",checkbaos);
              byte[] checkbytearray = checkbaos.toByteArray();
				 int j=0;
				 while(j<bytearray.length)
				 {
					 if(bytearray[j]!=checkbytearray[j])
					 {
						 flag=1;
						 break;
					 }
					 j++;
				 }
				 if(bytearray.length!=checkbytearray.length)
				 {
					flag=1; 
				 }
				 if(flag==0)
				 {
					 defaultf.delete();
					  System.out.println("no");
					 break;
				 }
				 }
			 }
             if(flag==1)
			 {
				 File a = new File("C:/xampp/htdocs/photo/"+filearray.length+".jpg"); 
				 defaultf.renameTo(a);
				 System.out.println("yes");
             }  				 
	}
}