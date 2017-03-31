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
package net._5tingr4y.openrpg.data.world;

import net._5tingr4y.openrpg.data.entity.Entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class World {

    private List<Entity> entities;

    private boolean active = false;
    private boolean render = false;

    public World() {
        entities = new LinkedList<>();
    }

    //update and render
    public void update() {
        if(active) {
            Collections.sort(entities);

            entities.forEach(Entity::tick);
            entities.forEach(Entity::applyPosition);

            Iterator<Entity> iterator = entities.iterator();
            while(iterator.hasNext()) {
                Entity e = iterator.next();

                if(e.isDead())
                    iterator.remove();
            }
        }
    }

    public void render() {
        if(render) {
            entities.forEach(Entity::render);
        }
    }

    //setters
    public void setActive(boolean active_) {
        active = active_;
    }

    public void setRender(boolean render_) {
        render = render_;
    }

    public void addEntity(Entity e) {
        if(e != null && !e.isDead())
            entities.add(e);
    }
}
