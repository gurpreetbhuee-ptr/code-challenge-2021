package reply.codechalange.data;

public class Customer
{
	int customerId;
	Point point;
	int reward;

	public Customer(final int customerId, final Point point, final int reward)
	{
		this.customerId = customerId;
		this.point = point;
		this.reward = reward;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(final int customerId)
	{
		this.customerId = customerId;
	}

	public Point getPoint()
	{
		return point;
	}

	public void setPoint(final Point point)
	{
		this.point = point;
	}

	public int getReward()
	{
		return reward;
	}

	public void setReward(final int reward)
	{
		this.reward = reward;
	}
}
