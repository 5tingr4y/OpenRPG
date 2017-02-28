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

import net._5tingr4y.openrpg.utils.Log;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OpenGLHandler {

    private GLFWErrorCallback errorCallback;

    //window handle
    private long window;

    //data
    private int width = 1, height = 1;
    private boolean fullscreen = false;

    private boolean initialized = false;

    private OpenGLHandler() {
        //prevent instances other than the singleton instance
    }

    //setup and cleanup
    public void setupOpenGL() {
        Log.info(this, "OpenGL setup started");

        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        //initialize GLFW. Most GLFW functions will not work before doing this.
        if(glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        //configure window
        glfwDefaultWindowHints(); //make sure we have the default window hints (should already be, just to be sure)
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); //window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); //window will not be resizable

        long monitor = fullscreen ? glfwGetPrimaryMonitor() : NULL;
        window = glfwCreateWindow(width, height, "OpenRPG", monitor, NULL);

        if(window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        //get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        if(!fullscreen) {
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - width) / 2,
                    (vidmode.height() - height) / 2
            );
        }

        //make the OpenGL context current
        glfwMakeContextCurrent(window);

        //enable v-sync
        glfwSwapInterval(1);

        //make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        //set the clear color
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        initialized = true;
        Log.info(this, "OpenGL setup finished");
    }

    public void cleanupOpenGL() {
        Log.info(this, "OpenGL cleanup started");

        glfwDestroyWindow(window);

        glfwTerminate();
        errorCallback.release();

        Log.info(this, "OpenGL cleanup finished");
    }

    //getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public long getWindowID() {
        return window;
    }

    //setters
    public void setSize(int width_, int height_) {
        width = Math.max(1, width_);
        height = Math.max(1, height_);

        if(initialized) {
            glfwSetWindowSize(window, width, height);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - width) / 2,
                    (vidmode.height() - height) / 2
            );
        }
    }

    public void setFullscreen(boolean fs) {
        fullscreen = fs;
    }


    //static
    private static OpenGLHandler instance;

    public static OpenGLHandler get() {
        return instance == null ? instance = new OpenGLHandler() : instance;
    }
}
