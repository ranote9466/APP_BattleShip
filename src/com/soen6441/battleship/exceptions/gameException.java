package com.soen6441.battleship.exceptions;

public class gameException extends Exception
{
	private String description;

	public gameException(String description)
	{
		super();
		this.description = description;
	}

	public String getDescription()
	{
		return this.description;
	}
}
