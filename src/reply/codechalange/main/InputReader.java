package reply.codechalange.main;

import reply.codechalange.data.Customer;
import reply.codechalange.data.Output;
import reply.codechalange.data.Point;
import reply.codechalange.data.RouteIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class InputReader
{
	public static Map<Character, Integer> terrainValueMap = new HashMap<>();

	public static void main(final String[] args)
	{
		terrainValueMap = getTerrainMap();
		//todo change the file with the input file
		final File f = new File("resources/1_victoria_lake.txt");
		try
		{
			final FileInputStream inputStream = new FileInputStream(f);
			final Scanner sc = new Scanner(inputStream, "UTF-8");

			System.out.println("Start reading file......");
			//todo add the traversing the file logic understanding the input file

			final String firstLine = sc.nextLine();
			final String[] firstArr = firstLine.split("\\s");
			int customerOffices = Integer.parseInt(firstArr[2]);

			final int n = Integer.parseInt(firstArr[0]);
			final int m = Integer.parseInt(firstArr[1]);
			final int[][] grid = new int[m][n];

			final List<Customer> customers = new ArrayList<>();
			int customerId = 1;
			while (customerOffices > 0)
			{
				final String cutomerOffice = sc.nextLine();
				final String[] arrCustOffice = cutomerOffice.split("\\s");

				final Point point = new Point(Integer.parseInt(arrCustOffice[0]) - 1, Integer.parseInt(arrCustOffice[1]) - 1, null);
				final Customer customer = new Customer(customerId, point,
						Integer.parseInt(arrCustOffice[2]));
				customers.add(customer);

				customerOffices--;
				customerId++;
			}

			int countRow = 0;

			while (sc.hasNextLine())
			{

				final String line = sc.nextLine();
				for (int i = 0; i < n; i++)
				{
					grid[countRow][i] = terrainValueMap.get(line.charAt(i));
				}
				countRow++;
			}


			System.out.println("Finished reading file......");


			System.out.println("Start converting response ....");

			WriteObjectToFile(new ArrayList<>());
		}
		catch (final FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private static Map<Character, Integer> getTerrainMap()
	{
		final Map<Character, Integer> terrainValueMap = new HashMap<>();
		terrainValueMap.put('~', 800);
		terrainValueMap.put('*', 200);
		terrainValueMap.put('+', 150);
		terrainValueMap.put('X', 120);
		terrainValueMap.put('_', 100);
		terrainValueMap.put('H', 70);
		terrainValueMap.put('T', 50);
		terrainValueMap.put('#', 0);

		return terrainValueMap;
	}


	public static void WriteObjectToFile(final List<Object> objects)
	{
		try
		{
			final PrintWriter out = new PrintWriter(new FileWriter("resources/output.txt", false), true);
			out.write(" Test " + " Value ");
			out.write(objects.size() + "\n");
			objects.forEach(result ->
			{
				out.write(" " + "  ");

			});
			out.close();
			System.out.println("The Object  was succesfully written to a file");

		}
		catch (final Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
