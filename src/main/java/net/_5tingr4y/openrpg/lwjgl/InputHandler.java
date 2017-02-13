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
package net._5tingr4y.openrpg.lwjgl;

import net._5tingr4y.openrpg.lwjgl.keyCallbacks.GameKeyCallback;
import net._5tingr4y.openrpg.lwjgl.keyCallbacks.MenuKeyCallback;
import net._5tingr4y.openrpg.lwjgl.keyCallbacks.TutorialKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

public class InputHandler {

    OpenGLHandler oglHandler;

    private Callbacks currentCallback;

    private InputHandler() {
        oglHandler = OpenGLHandler.get();

        currentCallback = Callbacks.MENU;
    }

    public void init() {
        glfwSetKeyCallback(oglHandler.getWindowID(), currentCallback.callback);
    }

    public boolean setKeyCallback(Callbacks callback) {
        if(callback == currentCallback) return false;

        currentCallback = callback;
        glfwSetKeyCallback(oglHandler.getWindowID(), currentCallback.callback);

        return true;
    }

    public void cleanUp() {
        Callbacks.MENU.callback.release();
        Callbacks.GAME.callback.release();
        Callbacks.TUTORIAL.callback.release();
    }

    //statics
    private static InputHandler instance;

    public static InputHandler get() {
        return instance == null ? instance = new InputHandler() : instance;
    }

    public enum Callbacks {
        MENU(new MenuKeyCallback()),
        GAME(new GameKeyCallback()),
        TUTORIAL(new TutorialKeyCallback());

        private GLFWKeyCallback callback;

        Callbacks(GLFWKeyCallback callback_) {
            callback = callback_;
        }
    }
}
