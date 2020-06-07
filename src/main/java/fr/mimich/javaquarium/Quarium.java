/*
 * This file is part of JavaQuarium, licensed under the MIT License.
 *
 *  Copyright (c)  Mimich
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.mimich.javaquarium;

import fr.mimich.javaquarium.aquarium.AquariumManager;
import fr.mimich.javaquarium.aquarium.fish.Fish;
import fr.mimich.javaquarium.aquarium.fish.FishType;
import fr.mimich.javaquarium.aquarium.fish.IFish;
import fr.mimich.javaquarium.aquarium.seaweed.ISeaWeed;
import fr.mimich.javaquarium.aquarium.seaweed.Seaweed;
import fr.mimich.javaquarium.task.AquariumTimeTask;
import fr.mimich.javaquarium.utils.ThreadFactoryBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.logging.Logger;

public final class Quarium {

    private final AquariumManager aquariumManager;

    private final Set<IFish> fish = new HashSet<IFish>();

    private final Set<ISeaWeed> seaWeeds = new HashSet<ISeaWeed>();

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().name("quarium-scheduler"));

    private  AquariumTimeTask aquariumTimeTask;

    private final Logger LOGGER = Logger.getLogger(Quarium.class.getName());

    /**
     * Constructor of Quarium class.
     */

    public Quarium() {

        this.aquariumManager = new AquariumManager(this);

    }

    /**
     * Methode for start the project
     */

    public final void start() {

        this.getLogger().info("Starting the Aquarium programs simulation...");


        this.aquariumManager.addFish(new Fish("Aziz", false, FishType.BAR));

        this.aquariumManager.addFish(new Fish("Yasmine", true, FishType.CARPE));

        this.aquariumManager.addSeaweed(new Seaweed());


        this.aquariumTimeTask = new AquariumTimeTask(this, 4);

    }

    /**
     * Methode for stop the project
     */

    public final void stop() {

        this.getLogger().info("Stopping the Aquarium programs simulation...");

        this.getLogger().info("Purge quarium...");

        final int seaWeedsCount = this.seaWeeds.size();

        this.seaWeeds.clear();

        this.getLogger().info("In total " + seaWeedsCount + " SeaWeeds was purged from the Aquarium...");

        final int fishCount = this.seaWeeds.size();

        this.fish.clear();

        this.getLogger().info("In total " + fishCount + " Fish was purged from the Aquarium...");

    }

    /**
     * Methode for get the aquarium manager.
     *
     * @return aquariumManager For Manage the aquarium.
     */

    public AquariumManager getAquariumManager() {

        return aquariumManager;

    }

    /**
     * Methode for get the fish collection.
     *
     * @return fish For Get all Fish on the quarium.
     */

    public final Set<IFish> getFish() {

        return this.fish;

    }

    /**
     * Methode for get seaweed collection.
     *
     * @return seaWeeds For Get all SeaWeeds on the quarium.
     */

    public final Set<ISeaWeed> getSeaWeeds() {

        return this.seaWeeds;

    }

    /**
     * Methode for get the scheduler.
     *
     * @return scheduler For get Manage thread on custom scheduler.
     */

    public final ScheduledExecutorService getScheduler() {

        return this.scheduler;

    }

    /**
     * Methode for get the Logger.
     *
     * @return LOGGER For log on the project.
     */

    public final Logger getLogger() {

        return this.LOGGER;

    }

    /**
     * Methode for get the AquariumTimeTask.
     *
     * @return aquariumTimeTask For get all informations from this class.
     */

    public final AquariumTimeTask getAquariumTimeTask() {

        return this.aquariumTimeTask;

    }

}
