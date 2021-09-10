package LZ78;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class De78 {

	public static void main(String[] args) {
		String data=read();
		
		Vector<String> table = new Vector<String>();
		Vector <Integer> postion = new Vector<Integer>();
		Vector <Character> next = new Vector<Character>();
		Integer p=-1;
		int c=0;
		table.add("");
		
		for(int i=0; i<data.length();i++)
		{
			if(Character.isDigit(data.charAt(i)))
			{
				int moreDigit=i;
				String digit=""+data.charAt(i);
				while(true)
				{
					moreDigit++;
					if(moreDigit==data.length())
						break;
					if(Character.isDigit(data.charAt(moreDigit)))
						digit+=data.charAt(moreDigit);
					else
					{
						i=moreDigit;
						postion.add(Integer.parseInt(digit));
						digit="";
						break;
					}
					
					
				}
				
			}
			next.add(data.charAt(i));
				
		}
		data="";
		
		while(true)
		{
			if(c==postion.size())
				break;
			p=postion.elementAt(c);
			if(p==0)
			{
				data+=next.elementAt(c);
				table.add(next.elementAt(c)+"");
				c++;
			}
			else
			{
				data+=table.elementAt(p)+next.elementAt(c);
				table.add(table.elementAt(p)+next.elementAt(c));
				c++;
			}
			
		}
		System.out.println(postion);
		System.out.println(next);
		System.out.println(table);
		writee(data);
		System.out.println("Done");
	}
	
	static String read()
	{
		 String data = null;
		 try {
		      File myObj = new File("tag.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  data = myReader.nextLine();
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return data;
	}
	static void writee(String data)
	{
		try {
		      FileWriter myWriter = new FileWriter("deout.txt");
		      myWriter.write(data);
		    	 
		      
		    	  
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}
