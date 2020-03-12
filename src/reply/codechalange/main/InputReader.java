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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import reply.codechalange.data.*;


public class InputReader
{
	public static final String RESOURCES_TXT = "resources/d_maelstrom.txt";
	public static Map<Character, Integer> terrainValueMap = new HashMap<>();




	public static void main(final String[] args)
	{
		//todo change the file with the input file
		final File f = new File(RESOURCES_TXT);

		List<String> lines = new ArrayList<>();

		try {
			lines = Files.readAllLines(Paths.get(RESOURCES_TXT));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		try
		{
			final FileInputStream inputStream = new FileInputStream(f);
			final Scanner sc = new Scanner(inputStream, "UTF-8");

			System.out.println("Start reading file......");
			//todo add the traversing the file logic understanding the input file


			final String firstLine = lines.get(0);


			final String[] firstArr = firstLine.split("\\s");

			//int customerOffices = Integer.parseInt(firstArr[2]);



			final int cols = Integer.parseInt(firstArr[0]);
			final int rows = Integer.parseInt(firstArr[1]);

			System.out.println("cols   " + cols);

			System.out.println("rows   " + rows);

			//final String[][] officeMap = new String[rows][cols];


			final SeatingLocations[][] seatingLocations = new SeatingLocations[rows][cols];

			final int noOfDevs = Integer.parseInt(lines.get(rows + 1).trim());

			System.out.println("noOfDevs" + noOfDevs);


			for (int i = 0; i < rows; i++)
			{
				final String placeLine = lines.get(i + 1);
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

				}
			}

			final List<Developer> developers = new ArrayList<>();

			for (int i = 0; i < noOfDevs; i++)
			{

				pupulateDevelopers(rows, developers, i, lines);
			}


			developers.sort(new Comparator<Developer>()
			{
				@Override
				public int compare(final Developer o1, final Developer o2)
				{
					return Integer.compare(o2.getBonus(), o1.getBonus());
				}
			});




			final int noOfPMs = Integer.parseInt(lines.get(rows + 2 + noOfDevs));


			final List<Manager> managers = new ArrayList<>();

			for (int i = 0; i < noOfPMs; i++)
			{

				pupulateManagers(rows, noOfDevs, managers, i, lines);

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
							checkIfDevLocationAvailabel(seatingLocations, i, j, developers, managers, rows, cols);
							checkIfManagerLocationAvailabel(seatingLocations, i, j, developers, managers, rows, cols);
						}

					}

				}
			}




			final List<Employee> employeeList = new ArrayList<>();

			developers.sort(new Comparator<Developer>()
			{
				@Override
				public int compare(final Developer o1, final Developer o2)
				{
					return Integer.compare(o1.getId(), o2.getId());
				}
			});

			managers.sort(new Comparator<Manager>()
			{
				@Override
				public int compare(final Manager o1, final Manager o2)
				{
					return Integer.compare(o1.getId(), o2.getId());
				}
			});


			employeeList.addAll(developers);
			employeeList.addAll(managers);
			WriteObjectToFile(new ArrayList<>(employeeList));
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

	private static void pupulateManagers(final int rows, final int noOfDevs, final List<Manager> managers, final int i, final List<String> lines)
			throws IOException
	{
		final String managerDetail = lines.get(noOfDevs + rows + i + 3);
		final String[] managerDetailArr = managerDetail.split("\\s");


		final int id = i + 1;
		final String company = managerDetailArr[0];
		final int bonus = Integer.parseInt(managerDetailArr[1]);

		final Manager manager = new Manager(id, company, bonus);

		managers.add(manager);
	}

	private static void pupulateDevelopers(final int rows, final List<Developer> developers, final int i, final List<String> lines) throws IOException
	{
		final String developerDetail = lines.get(rows + i + 2);
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

	public static void WriteObjectToFile(final List<Employee> objects)
	{
		try
		{
			final PrintWriter out = new PrintWriter(new FileWriter("resources/d_maelstrom_response.txt", false), true);
			objects.forEach(emp ->
			{
				out.write(emp + "\n");

			});
			out.close();
			System.out.println("The Object  was successfully written to a file");

		}
		catch (final Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
