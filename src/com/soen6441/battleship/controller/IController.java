/**
 * 
 */
package com.soen6441.battleship.controller;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Game;

/**
 * @author e_uhegb
 *
 */
public interface IController
{
	public Game start() throws gameException;
}
