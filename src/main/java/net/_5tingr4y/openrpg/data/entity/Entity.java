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
package net._5tingr4y.openrpg.data.entity;

public class Entity {

    protected float x, y;
    protected float dx, dy;

    private boolean isDead;

    public Entity(int x_, int y_) {
        x = x_;
        y = y_;
        dx = dy = 0;

        isDead = false;
    }

    public void applyPosition() {
        x += dx;
        y += dy;
        dx = dy = 0;
    }

    //update and render
    public void tick() {
        //nothing by default
    }

    public void render() {

    }

    //getters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isDead() {
        return isDead;
    }

    //setters
    public void setX(float x_) {
        x = x_;
    }

    public void setY(float y_) {
        y = y_;
    }

    public void moveX(float dx_) {
        dx += dx_;
    }

    public void moveY(double dy_) {
        dy += dy_;
    }

    public void resetPositionDelta() {
        dx = dy = 0;
    }

    public void die() {
        isDead = true;
    }
}
