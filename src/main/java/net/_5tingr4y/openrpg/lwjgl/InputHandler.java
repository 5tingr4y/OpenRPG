package net._5tingr4y.openrpg.lwjgl;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

public class InputHandler {

    OpenGLHandler oglHandler;

    private GLFWKeyCallback menuKeyCallback;
    private GLFWKeyCallback gameKeyCallback;

    private boolean isGameCallback;

    private InputHandler() {
        oglHandler = OpenGLHandler.get();

        initKeyCallbacks();
        isGameCallback = false;
    }

    public void init() {
        glfwSetKeyCallback(oglHandler.getWindowID(), menuKeyCallback);
    }

    public void switchToMenuCallback() {
        if(isGameCallback) {
            glfwSetKeyCallback(oglHandler.getWindowID(), menuKeyCallback);
            isGameCallback = false;
        }
    }

    public void switchToGameCallback() {
        if(!isGameCallback) {
            glfwSetKeyCallback(oglHandler.getWindowID(), gameKeyCallback);
            isGameCallback = true;
        }
    }

    private void initKeyCallbacks() {
        menuKeyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GLFW_TRUE); // We will detect this in our rendering loop
            }
        };

        gameKeyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GLFW_TRUE); // We will detect this in our rendering loop
            }
        };
    }

    public void cleanUp() {
        menuKeyCallback.release();
        gameKeyCallback.release();
    }

    //statics
    private static InputHandler instance;

    public static InputHandler get() {
        return instance == null ? instance = new InputHandler() : instance;
    }
}
