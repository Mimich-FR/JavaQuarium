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

package fr.mimich.javaquarium.task;

import fr.mimich.javaquarium.Quarium;
import fr.mimich.javaquarium.aquarium.fish.IFish;
import fr.mimich.javaquarium.exception.QuariumCycleCountException;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public final class AquariumTimeTask implements Runnable {

    private ScheduledFuture scheduledFutureAquariumTimeTask;

    public int cycleNumber;

    private int cycle;

    private final Quarium quarium;

    /**
     * Constructor of AquariumTimeTask class.
     *
     * @param quarium Return instance.
     * @param cycle   The finale number for cycle.
     */

    public AquariumTimeTask(final Quarium quarium, final int cycle) {

        this(quarium);

        this.cycle = cycle;

    }

    /**
     * Constructor of AquariumTimeTask class.
     *
     * @param quarium Return instance.
     */

    public AquariumTimeTask(final Quarium quarium) {


        this.cycle = 0;

        this.quarium = quarium;

        this.scheduledFutureAquariumTimeTask = this.quarium.getScheduler().scheduleWithFixedDelay(this, 0, 2, TimeUnit.SECONDS);

    }


    public void run() {

        if (this.cycle <= 0) {

            try {

                throw new QuariumCycleCountException(this.cycle + " is not correct cycle count");

            } catch (QuariumCycleCountException e) {

                e.printStackTrace();

            }


            this.scheduledFutureAquariumTimeTask.cancel(true);

            return;

        }

        this.cycleNumber++;

        if (this.cycle == cycleNumber) {

            this.scheduledFutureAquariumTimeTask.cancel(true);

            return;

        }

        this.quarium.getLogger().info("===== tour " + this.cycleNumber + " ====");

        System.out.println("Number of sea weed on the aquarium " + this.quarium.getSeaWeeds().size());


        for (IFish fish : this.quarium.getFish()) {

            final String message = fish.isFemale() ? " is sis" : " is bro";

            System.out.println(fish.getName() + message);

        }

    }

    /**
     * Methode for get the cycling of the aquarium.
     *
     * @return cycleNumber Return the cycle count.
     */

    public final int getCycleNumber() {

        return this.cycleNumber;

    }

}
