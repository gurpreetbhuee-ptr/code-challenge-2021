package reply.codechalange.data;

import java.util.List;


public class Developer
{

	int id;
	String company;
	int bonus;
	List<String> skills;
	Point seatingLocation;

	public Developer(final int id, final String company, final int bonus, final List<String> skills)
	{
		this.id = id;
		this.company = company;
		this.bonus = bonus;
		this.skills = skills;
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

	public Point getSeatingLocation()
	{
		return seatingLocation;
	}

	public void setSeatingLocation(final Point seatingLocation)
	{
		this.seatingLocation = seatingLocation;
	}

	public int getBonus()
	{
		return bonus;
	}

	public void setBonus(final int bonus)
	{
		this.bonus = bonus;
	}

	public List<String> getSkills()
	{
		return skills;
	}

	public void setSkills(final List<String> skills)
	{
		this.skills = skills;
	}


}
