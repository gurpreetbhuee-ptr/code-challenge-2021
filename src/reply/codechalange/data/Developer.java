package reply.codechalange.data;

import java.util.List;


public class Developer implements Employee
{

	int id;
	String company;
	int bonus;
	List<String> skills;
	Point seatingLocation;
	int skillCount;
	boolean isAllocated;


	public Developer(final int id, final String company, final int bonus, final List<String> skills, final int skillCount) {
		this.id = id;
		this.company = company;
		this.bonus = bonus;
		this.skills = skills;
		this.skillCount = skillCount;
	}

	public Developer(final int id, final String company, final int bonus, final List<String> skills, final Point seatingLocation,
			final int skillCount)
	{
		this.id = id;
		this.company = company;
		this.bonus = bonus;
		this.skills = skills;
		this.seatingLocation = seatingLocation;
		this.skillCount = skillCount;
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


	public int getSkillCount()
	{
		return skillCount;
	}

	public void setSkillCount(final int skillCount)
	{
		this.skillCount = skillCount;
	}

	public boolean isAllocated()
	{
		return isAllocated;
	}

	public void setAllocated(final boolean allocated)
	{
		isAllocated = allocated;
	}
}
