package LZ77;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;



public class compress {
	
	private static FileInputStream in;
	private static Vector<Integer> pos = new Vector<Integer>();
	private static Vector<Integer> length = new Vector<Integer>();
	private static Vector<Character> next = new Vector<Character>();
	private static String searchb;
	
	public static String read() throws FileNotFoundException
	{
		in = new FileInputStream("input.txt");	
		String data = "";
		Scanner scanf = new Scanner(in);
			while(scanf.hasNextLine())
			{
				data+= scanf.nextLine();
			}
			scanf.close(); 
		return data;
	}
	
	public static void write( Vector<Integer> pos, Vector<Integer> len, Vector<Character> next)
	{
		
		try
		{
			FileWriter out = new FileWriter("output.txt");
			for(int i=0;i<pos.size();i++)
		      {
					out.write(pos.elementAt(i)+" "  + len.elementAt(i)+" " + next.elementAt(i)+"\n");
		      }
			out.flush();
			out.close();
		}
		 catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static int search(String sb, String s)
	{
		int index;
			if(sb.contains(s))
				index = sb.length() - sb.lastIndexOf(s);
			else
				index = 0;
		return index;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String read = read();
		searchb = "";
		String add = "";
		int s = 0;
		int index;
		for(int i=0; i<read.length(); i++)
		{
			add+=read.charAt(i);
			index = search(searchb, add);
			if(index==0)
			{
				pos.add(s);
				length.add(add.length()-1);
				next.add( read.charAt(i));
				searchb+=add;
				add = "";
				s = 0;
				}
			
			else
			{
				s = index;
			}
		
			String newsb = "";
			int size = (int) Math.ceil(searchb.length() - searchb.length()/4);
			if(searchb.length()>10)
			{					
				for(int j= size; j<searchb.length(); j++)
				{
					newsb+= searchb.charAt(j);
				}
				searchb = newsb;
			}
		}
		write(pos,length,next);
		System.out.print("Done");		
	}
}
