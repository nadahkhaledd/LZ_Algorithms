package LZ77;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class deCompress {
	private static File in;
	public static void main(String[] args) {
		read();
		System.out.print("Done");
	}
	
	

	public static void read()
	{
		Vector <Integer> postion = new Vector<Integer>();
		Vector <Integer> length = new Vector<Integer>();
		Vector <String> next = new Vector<String>();
		
		in = new File("output.txt");
		String data = "";
		try {
			Scanner scanf = new Scanner(in);
			while(scanf.hasNextLine())
			{
				data = scanf.nextLine();
				int i = 0;
				String digit="";
				while(Character.isDigit(data.charAt(i)))
				{
				    digit=digit+data.charAt(i);
				    i++;
				}
				postion.add(Integer.parseInt(digit));
				i++;
				digit="";
				while(Character.isDigit(data.charAt(i)))
				{
				    digit=digit+data.charAt(i);
				    i++;
				}
				length.add(Integer.parseInt(digit));
				i++;
				digit="";
				while(Character.isLetter(data.charAt(i)))
				{
				    digit=digit+data.charAt(i);
				    if(i==data.length()-1)
				    {
				    	break;
				    }
				    else
				    {
				    	i++;
				    }
				    
				}
				
				next.add(digit);
			}
			scanf.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		deCompress(postion,length,next);
	}
	
	
	public static void deCompress(Vector<Integer> pos, Vector<Integer> len, Vector<String> next)
	{
		String data = "";
		int index=0;
		for(int i=0; i<pos.size(); i++)
		{
			if(pos.elementAt(i)==0)
			{
				data+=next.elementAt(i);
			}
			else
			{
				
				index = data.length() - pos.elementAt(i);
				for(int count=0; count<len.elementAt(i); count++)
				{
					data+=data.charAt(index);
					index++;
					
				}
				data+=next.elementAt(i);
				
			}
		}
		
		
		write(data);
	}
	public static void write( String data)
	{
		
		try
		{
			FileWriter out = new FileWriter("lz77de.txt");
			out.write(data);
			out.close();
			
		}
		 catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}
