/*
 * This file is part of OpenRPG, a small open-source RPG game.
 * Copyright (C) 2017  Raymond "5tingr4y" Kampmann <https://5tingr4y.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net._5tingr4y.openrpg.data;

import net._5tingr4y.openrpg.data.world.World;

public class GameData {

    private World gameWorld;
    private World menuWorld;

    private GameData() {
        gameWorld = new World();
        menuWorld = new World();
    }

    public void updateWorlds() {
        gameWorld.update();
        menuWorld.update();
    }

    public void renderWorld() {
        gameWorld.render();
        menuWorld.render();
    }

    //static
    private static GameData instance;

    public static GameData get() {
        return instance == null ? instance = new GameData() : instance;
    }
}
