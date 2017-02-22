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
package net._5tingr4y.openrpg.settings;

import net._5tingr4y.openrpg.utils.Log;
import net._5tingr4y.openrpg.utils.LoggingUtils;

import java.io.*;
import java.util.Properties;

public class Settings {

    private LanguageManager languageManager;

    private Properties settings;
    private Properties defaultSettings;

    public Settings() {
        languageManager = new LanguageManager();
    }

    public void loadSettings() throws IOException {
        Log.info(this, "Loading settings");

        defaultSettings = new Properties();

        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("settings/defaultSettings.properties");
        defaultSettings.load(is);

        settings = new Properties();

        File settingsFile = new File("settings.properties");
        if(!settingsFile.exists()) {
            //settings file doesn't exist, create new one
            Log.info(this, "Settings file not found, creating file for default settings");

            for(String key: defaultSettings.stringPropertyNames()) {
                settings.setProperty(key, defaultSettings.getProperty(key));
            }

            settings.store(new FileWriter(settingsFile), null);
//            is = Thread.currentThread().getContextClassLoader()
//                    .getResourceAsStream("settings/defaultSettings.properties");
//
//            FileUtils.copyInputStreamToFile(is, settingsFile);
//
//            settings.load(new FileReader(settingsFile));
        } else {

            settings.load(new FileReader(settingsFile));

            Log.info(this, "Settings file found; read the following settings:\n" + LoggingUtils.mapAsTable(settings) + "\n" +
                    "Proceeding with adding missing entries");

            //add all non-mapped keys with their default values
            defaultSettings.stringPropertyNames().stream().filter(key -> settings.getProperty(key) == null)
                    .forEach(key -> settings.setProperty(key, defaultSettings.getProperty(key)));


            //noinspection ResultOfMethodCallIgnored
            settingsFile.delete();
            settings.store(new FileWriter(settingsFile), null);
        }

        Log.info(this, "Loading settings complete; the final settings are:\n" + LoggingUtils.mapAsTable(settings));
    }
}
