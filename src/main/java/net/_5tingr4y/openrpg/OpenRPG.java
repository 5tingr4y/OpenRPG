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
package net._5tingr4y.openrpg;

import net._5tingr4y.openrpg.utils.Log;

public class OpenRPG {

    public static void main(String[] args) {
        processArgs(args);

        Log.info(OpenRPG.class, "OpenRPG starting");

        GameController gc = GameController.get();

        //TODO: read size from settings
        gc.getOGLHandler().setSize(1024, 576);

        gc.start();

        Log.info(OpenRPG.class, "Game Shutting down regularly");
    }

    private static void processArgs(String[] args) {
        for(String arg: args) {
            switch(arg) {
                case "debuglog": Log.debug = true; break;

                default: Log.warn(OpenRPG.class, "Invalid program argument: \"" + arg + "\"; ignoring..."); break;
            }
        }
    }
}
