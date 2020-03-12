package reply.codechalange.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager implements Employee
{

	int id;
	String company;
	int bonus;
	Point seatingLocation;

	public Manager(final int id, final String company, final int bonus)
	{
		this.id = id;
		this.company = company;
		this.bonus = bonus;
	}

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(final String company)
	{
		this.company = company;
	}

	public int getBonus()
	{
		return bonus;
	}

	@Override
	public List<String> getSkills()
	{
		return Collections.EMPTY_LIST;
	}

	public void setBonus(final int bonus)
	{
		this.bonus = bonus;
	}

	public Point getSeatingLocation()
	{
		return seatingLocation;
	}

	public void setSeatingLocation(final Point seatingLocation)
	{
		this.seatingLocation = seatingLocation;
	}

	@Override
	public String toString() {
		return "Manager{" + "id=" + id + ", company='" + company + '\'' + ", bonus=" + bonus + ", seatingLocation=" + seatingLocation + '}';
	}
}
