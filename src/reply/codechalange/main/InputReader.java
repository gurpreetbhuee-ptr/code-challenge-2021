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
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
		//todo change the file with the input file
		final File f = new File("resources/a_solar.txt");
		try
		{
			final FileInputStream inputStream = new FileInputStream(f);
			final Scanner sc = new Scanner(inputStream, "UTF-8");

			System.out.println("Start reading file......");
			//todo add the traversing the file logic understanding the input file

			final String firstLine = sc.nextLine();
			final String[] firstArr = firstLine.split("\\s");
			int customerOffices = Integer.parseInt(firstArr[2]);

			final int cols = Integer.parseInt(firstArr[0]);
			final int rows = Integer.parseInt(firstArr[1]);
			final String[][] officeMap = new String[rows][cols];

			final String noOfDevs = Files.readAllLines(Paths.get("resources/a_solar.txt")).get(rows+2);



			for (int i = 0; i < rows; i++) {
				String placeLine = Files.readAllLines(Paths.get("resources/a_solar.txt")).get(rows+3);
				for (int j = 0; j < cols; j++) {
					//reading from file 1 and 0 if 1 then store true else store false in grid
					officeMap[i][j] = String.valueOf(placeLine.charAt(j));

				}
			}





































			



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



			System.out.println("Finished reading file......");


			System.out.println("Start converting response ....");
			//todo add the conversion logic of response to the result file

			WriteObjectToFile(new ArrayList<>());
		}
		catch (final FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
