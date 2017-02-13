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

import net._5tingr4y.openrpg.lwjgl.InputHandler;
import net._5tingr4y.openrpg.lwjgl.OpenGLHandler;
import net._5tingr4y.openrpg.utils.Log;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;

public class GameController {

    private OpenGLHandler oglHandler;
    private InputHandler inputHandler;

    private long currentTick;
    private boolean running;

    private GameController() {
        currentTick = 0;

        oglHandler = OpenGLHandler.get();
        inputHandler = InputHandler.get();
    }

    public void start() {
        Log.info(this, "LWJGL setup starting");
        oglHandler.setupOpenGL();
        inputHandler.init();

        Log.info(this, "LWJGL setup complete");
        running = true;
        loop();
    }

    private void loop() {
        try {
            while(running && glfwWindowShouldClose(oglHandler.getWindowID()) == GLFW_FALSE) {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
                glfwSwapBuffers(oglHandler.getWindowID()); // swap the color buffers


                glfwPollEvents();


                //finally, increment the tick counter
                currentTick++;
            }
        } finally {
            running = false;
            Log.info(this, "LWJGL cleanup starting");
            oglHandler.cleanupOpenGL();
            Log.info(this, "LWJGL cleanup complete");
        }
    }

    //getters
    public long getCurrentTick() {
        return currentTick;
    }


    //statics
    private static GameController instance;

    public static GameController get() {
        return instance == null ? instance = new GameController() : instance;
    }
}
