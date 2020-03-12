package reply.codechalange.data;

public class Manager
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
}
