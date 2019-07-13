package com.soen6441.battleship.model;

public class Location
{
	private Integer x;

	private Integer y;

	public Location(Integer x, Integer y)
	{
		this.x = x;
		this.y = y;
	}

	public Integer getX()
	{
		return this.x;
	}

	public Integer getY()
	{
		return this.y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Location))
		{
			return false;
		}
		Location other = (Location) obj;

		return other.getX() == this.getX() && this.getY() == other.getY();
	}

}
