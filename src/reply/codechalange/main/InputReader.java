package reply.codechalange.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import reply.codechalange.data.Developer;
import reply.codechalange.data.Manager;
import reply.codechalange.data.Point;
import reply.codechalange.data.SeatingLocations;


public class InputReader
{
	public static final String RESOURCES_TXT = "resources/a_solar.txt";
	public static Map<Character, Integer> terrainValueMap = new HashMap<>();

	public static void main(final String[] args)
	{
		//todo change the file with the input file
		final File f = new File(RESOURCES_TXT);
		try
		{
			final FileInputStream inputStream = new FileInputStream(f);
			final Scanner sc = new Scanner(inputStream, "UTF-8");

			System.out.println("Start reading file......");
			//todo add the traversing the file logic understanding the input file


			final String firstLine = Files.readAllLines(Paths.get(RESOURCES_TXT)).get(0);


			final String[] firstArr = firstLine.split("\\s");

			//int customerOffices = Integer.parseInt(firstArr[2]);



			final int cols = Integer.parseInt(firstArr[0]);
			final int rows = Integer.parseInt(firstArr[1]);

			System.out.println("cols   " + cols);

			System.out.println("rows   " + rows);

			//final String[][] officeMap = new String[rows][cols];


			final SeatingLocations[][] seatingLocations = new SeatingLocations[rows][cols];

			final int noOfDevs = Integer.parseInt(Files.readAllLines(Paths.get(RESOURCES_TXT)).get(rows + 1).trim());

			System.out.println("noOfDevs" + noOfDevs);


			for (int i = 0; i < rows; i++)
			{
				final String placeLine = Files.readAllLines(Paths.get(RESOURCES_TXT)).get(i + 1);
				for (int j = 0; j < cols; j++)
				{
					//reading from file 1 and 0 if 1 then store true else store false in grid

					final SeatingLocations seatingLocation = new SeatingLocations();

					if (placeLine.charAt(j) != '#')
					{
						seatingLocation.setAvailable(true);
						if (placeLine.charAt(j) == '_')
							seatingLocation.setDevLocation(true);
					}
					seatingLocation.setPoint(new Point(i, j));

					seatingLocations[i][j] = seatingLocation;

					System.out.println(seatingLocation.toString());

				}
			}

			final List<Developer> developers = new ArrayList<>();

			for (int i = 0; i < noOfDevs; i++)
			{

				pupulateDevelopers(rows, developers, i);
			}


			developers.sort(new Comparator<Developer>()
			{
				@Override
				public int compare(final Developer o1, final Developer o2)
				{
					return Integer.compare(o2.getBonus(), o1.getBonus());
				}
			});




			final int noOfPMs = Integer.parseInt(Files.readAllLines(Paths.get(RESOURCES_TXT)).get(rows + 2 + noOfDevs));


			final List<Manager> managers = new ArrayList<>();

			for (int i = 0; i < noOfPMs; i++)
			{

				pupulateManagers(rows, noOfDevs, managers, i);

			}

			managers.sort(new Comparator<Manager>()
			{
				@Override
				public int compare(final Manager o1, final Manager o2)
				{
					return Integer.compare(o2.getBonus(), o1.getBonus());
				}
			});




			final List<Manager> allocatedManager = new ArrayList<>();

			int manageCount = 0;
			int devCount = 0;
			for (int i = 0; i < rows; i++)
			{
				for (int j = 0; j < cols; j++)
				{
					final SeatingLocations seatingLocation = seatingLocations[i][j];
					if (seatingLocation.isAvailable())
					{

						if (!seatingLocation.isDevLocation())
						{


							final Manager manager = managers.get(manageCount++);
							manager.setSeatingLocation(new Point(i, j));
							manager.setAllocated(true);
							seatingLocation.setEmpId(manager.getId());
							seatingLocation.setCompany(manager.getCompany());
							//
							checkIfDevLocationAvailabel(seatingLocations, i, j, developers, managers, rows, cols);
							checkIfManagerLocationAvailabel(seatingLocations, i, j, developers, managers, rows, cols);

							allocatedManager.add(manager);


						}
						else
						{
							final Developer developer = developers.get(devCount++);
							developer.setSeatingLocation(new Point(i, j));
							developer.setAllocated(true);
							seatingLocation.setEmpId(developer.getId());
							seatingLocation.setCompany(developer.getCompany());
						}

					}

				}
			}

			System.out.println("Finished reading file......");


			for (final Manager manager : managers)
			{
				System.out.println(manager.toString());
			}
			for (final Developer developer : developers)
			{
				System.out.println(developer.toString());
			}


			System.out.println("Start converting response ....");
			//todo add the conversion logic of response to the result file

			WriteObjectToFile(new ArrayList<>());
		}
		catch (final FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void checkIfManagerLocationAvailabel(final SeatingLocations[][] seatingLocations, final int i, final int j,
			final List<Developer> developers, final List<Manager> managers, final int rows, final int cols)
	{
		final SeatingLocations seatingLocation = seatingLocations[i][j];
		if (j + 1 < cols && seatingLocations[i][j + 1].isAvailable() && !seatingLocations[i][j + 1].isDevLocation())
		{

			final Manager toAllocatemanager = managers.stream()
					.filter(manager -> seatingLocation.getCompany().equals(manager.getCompany()) && !manager.isAllocated()).findFirst()
					.orElse(null);

			if (Objects.nonNull(toAllocatemanager))
			{

				toAllocatemanager.setAllocated(true);
				toAllocatemanager.setSeatingLocation(new Point(i, j + 1));
				seatingLocations[i][j + 1].setEmpId(toAllocatemanager.getId());
				seatingLocations[i][j + 1].setAvailable(false);
				seatingLocations[i][j + 1].setCompany(toAllocatemanager.getCompany());

			}

		}
		if (i + 1 < rows && seatingLocations[i + 1][j].isAvailable() && !seatingLocations[i + 1][j].isDevLocation())
		{


			final Manager toAllocatemanager = managers.stream()
					.filter(manager -> seatingLocation.getCompany().equals(manager.getCompany()) && !manager.isAllocated()).findFirst()
					.orElse(null);

			if (Objects.nonNull(toAllocatemanager))
			{
				toAllocatemanager.setSeatingLocation(new Point(i + 1, j));
				toAllocatemanager.setAllocated(true);
				seatingLocations[i + 1][j].setEmpId(toAllocatemanager.getId());
				seatingLocations[i + 1][j].setAvailable(false);
				seatingLocations[i + 1][j].setCompany(toAllocatemanager.getCompany());

			}

		}


	}

	private static void checkIfDevLocationAvailabel(final SeatingLocations[][] seatingLocations, final int i, final int j,
			final List<Developer> developers, final List<Manager> managers, final int rows, final int cols)
	{
		final SeatingLocations seatingLocation = seatingLocations[i][j];
		if (j + 1 < cols && seatingLocations[i][j + 1].isAvailable() && seatingLocations[i][j + 1].isDevLocation())
		{

			final Developer developer1 = developers.stream()
					.filter(developer -> seatingLocation.getCompany().equals(developer.getCompany()) && !developer.isAllocated())
					.findFirst()
					.orElse(null);

			if (Objects.nonNull(developer1))
			{

				developer1.setAllocated(true);
				developer1.setSeatingLocation(new Point(i, j + 1));
				seatingLocations[i][j + 1].setEmpId(developer1.getId());
				seatingLocations[i][j + 1].setAvailable(false);
				seatingLocations[i][j + 1].setCompany(developer1.getCompany());

			}

		}
		if (i + 1 < rows && seatingLocations[i + 1][j].isAvailable() && seatingLocations[i + 1][j].isDevLocation())
		{


			final Developer developer1 = developers.stream()
					.filter(developer -> seatingLocation.getCompany().equals(developer.getCompany()) && !developer.isAllocated())
					.findFirst()
					.orElse(null);

			if (Objects.nonNull(developer1))
			{
				developer1.setSeatingLocation(new Point(i + 1, j));
				developer1.setAllocated(true);
				seatingLocations[i + 1][j].setEmpId(developer1.getId());
				seatingLocations[i + 1][j].setAvailable(false);
				seatingLocations[i + 1][j].setCompany(developer1.getCompany());

			}

		}

	}

	private static void pupulateManagers(final int rows, final int noOfDevs, final List<Manager> managers, final int i)
			throws IOException
	{
		final String managerDetail = Files.readAllLines(Paths.get(RESOURCES_TXT)).get(noOfDevs + rows + i + 3);
		final String[] managerDetailArr = managerDetail.split("\\s");


		final int id = i + 1;
		final String company = managerDetailArr[0];
		final int bonus = Integer.parseInt(managerDetailArr[1]);

		final Manager manager = new Manager(id, company, bonus);

		managers.add(manager);
	}

	private static void pupulateDevelopers(final int rows, final List<Developer> developers, final int i) throws IOException
	{
		final String developerDetail = Files.readAllLines(Paths.get(RESOURCES_TXT)).get(rows + i + 2);
		final String[] developerDetailArr = developerDetail.split("\\s");


		final int id = i + 1;
		final String company = developerDetailArr[0];
		final int bonus = Integer.parseInt(developerDetailArr[1]);
		final List<String> skills = new ArrayList<>();
		final int skillCount = Integer.parseInt(developerDetailArr[2]);

		for (int j = 0; j < skillCount; j++)
		{
			skills.add(developerDetailArr[3 + j]);
		}


		final Developer developer = new Developer(id, company, bonus, skills, skillCount);
		developers.add(developer);

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
