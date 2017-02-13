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
