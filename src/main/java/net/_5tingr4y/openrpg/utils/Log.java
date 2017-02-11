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
package net._5tingr4y.openrpg.utils;

import java.util.Date;

public class Log {

    public static void debug(Class<?> cl, String message) {
        System.err.println(format(cl, "Debug", message));
    }

    public static void debug(Object o, String message) {
        System.err.println(format(o.getClass(), "Debug", message));
    }


    public static void info(Class<?> cl, String message) {
        System.out.println(format(cl, "Info", message));
    }

    public static void info(Object o, String message) {
        System.out.println(format(o.getClass(), "Info", message));
    }


    public static void warn(Class<?> cl, String message) {
        System.err.println(format(cl, "Warning", message));
    }

    public static void warn(Object o, String message) {
        System.err.println(format(o.getClass(), "Warning", message));
    }



    private static String format(Class<?> cl, String level, String message) {
        StringBuilder sb = new StringBuilder("[")
                .append(new Date(System.currentTimeMillis()))
                .append("] [")
                .append(level)
                .append("] [")
                .append(cl.getSimpleName())
                .append("] ")
                .append(message);

        return sb.toString();
    }
}
