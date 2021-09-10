package LZ78;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class LZ78 {

	public static void main(String[] args) {
		String data=read();
		
		Vector<String> table = new Vector<String>();
		Vector <Integer> postion = new Vector<Integer>();
		Vector <Character> next = new Vector<Character>();
		Integer p=-1;
		int c=0;
		String d="";
		table.add("");
		int f=0;
		while(true)
		{
			if(c==data.length())
			{
				if(p!=0)
				{
					postion.add(0);
					next.add(data.charAt(c-1));
				}
				break;
			}
			
			d+=data.charAt(c);
			p=search(table,d);
			if(p==0)
			{
				table.add(d);
				postion.add(f);
				next.add(data.charAt(c));
				c++;
				d="";
				f=0;
			}
			else
			{
				c++;
				f=p;
				
			}
			
		}
		System.out.println(table);
		System.out.println(postion);
		System.out.println(next);
		writee(postion,next);
		System.out.println("Done");

	}
	static Integer search(Vector<String> table,String s)
	{
		for(Integer i=1;i<table.size();i++)
		{
			if(table.get(i).equals(s))
				return i;
		}
		return 0;
	}
	static String read()
	{
		 String data = null;
		 try {
		      File myObj = new File("test.txt");
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
	static void writee(Vector <Integer> postion,Vector <Character> next)
	{
		try {
		      FileWriter myWriter = new FileWriter("tag.txt");
		      for(int i=0;i<postion.size();i++)
		      {
		    	  myWriter.write(postion.elementAt(i)+""+next.elementAt(i));
		  
		      }
		    	  
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}
