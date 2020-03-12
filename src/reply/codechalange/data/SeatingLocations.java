package reply.codechalange.data;

public class SeatingLocations
{
	Point point;
	String company;
	boolean available;
	int empId;
	boolean isDevLocation;

	public Point getPoint()
	{
		return point;
	}

	public void setPoint(final Point point)
	{
		this.point = point;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(final String company)
	{
		this.company = company;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(final boolean available)
	{
		this.available = available;
	}

	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(final int empId)
	{
		this.empId = empId;
	}

	public boolean isDevLocation()
	{
		return isDevLocation;
	}

	public void setDevLocation(final boolean devLocation)
	{
		isDevLocation = devLocation;
	}

	public SeatingLocations(final Point point, final boolean available,
			final boolean isDevLocation)
	{
		this.point = point;
		this.available = available;
		this.isDevLocation = isDevLocation;
	}

	public SeatingLocations() {
	}

	@Override
	public String toString() {
		return "SeatingLocations{" + "point=" + point + ", company='" + company + '\'' + ", available=" + available + ", empId=" + empId + ", isDevLocation="
				+ isDevLocation + '}';
	}
}
