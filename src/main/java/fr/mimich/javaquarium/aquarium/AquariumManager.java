/*
 * This file is part of JavaQuarium, licensed under the MIT License.
 *
 *  Copyright (c)  DenisMihuk
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

package fr.mimich.javaquarium.aquarium;

import fr.mimich.javaquarium.Quarium;
import fr.mimich.javaquarium.aquarium.fish.IFish;
import fr.mimich.javaquarium.aquarium.seaweed.ISeaWeed;
import fr.mimich.javaquarium.exception.FishEatHimException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public final class AquariumManager {

    private final Quarium quarium;

    /**
     * Constructor of AquariumManager class.
     *
     * @param quarium Return instance.
     */

    public AquariumManager(final Quarium quarium) {

        this.quarium = quarium;

    }

    /**
     * Add Fish on the Aquarium.
     *
     * @param fish The fish object to want add.
     * @throws NullPointerException If the Fish is null.
     */

    public final void addFish(final IFish fish) {

        Objects.requireNonNull(fish, "Sorry the SeaWeed is no correct.");

        this.quarium.getFish().add(fish);

    }

    /**
     * Add SeaWeed on the Aquarium
     *
     * @param seaWeed The SeaWeed object to want add.
     * @throws NullPointerException If the SeaWeed is null.
     */

    public final void addSeaweed(final ISeaWeed seaWeed) {

        Objects.requireNonNull(seaWeed, "Sorry the SeaWeed is no correct.");

        this.quarium.getSeaWeeds().add(seaWeed);

    }

    /**
     * Eat the Fish on the Aquarium.
     *
     * @param eaterFish The eater Fish.
     * @param eatedFish The eated Fish.
     * @throws NullPointerException If the eater fish is null.
     * @throws NullPointerException If the eated fish is null.
     */

    public final void eatFish(final IFish eaterFish, final IFish eatedFish) throws FishEatHimException {

        Objects.requireNonNull(eaterFish, "Sorry the eater fish is not correct.");

        Objects.requireNonNull(eatedFish, "Sorry the eated Fish is not correct");

        if (eatedFish == eaterFish) {

            throw new FishEatHimException();

        }

        this.quarium.getFish().remove(eatedFish);

        this.quarium.getLogger().info(eaterFish.getName() + " eat" + eatedFish.getName());

    }

    /**
     * Eat seaWeed on the Aquarium
     *
     * @param eaterFish    The eater Fish.
     * @param eatedSeaWeed The eated SeaWeed.
     * @throws NullPointerException If the eater fish is null.
     * @throws NullPointerException If the eated fish is null.
     */

    public final void eatSeaWeed(final IFish eaterFish, final ISeaWeed eatedSeaWeed) {

        if (!eaterFish.getFishType().isCarnivorous()) {

            this.quarium.getSeaWeeds().remove(eatedSeaWeed);

            this.quarium.getLogger().info(eaterFish.getName() + " eat a SeaWeed");

        }

    }

    /**
     * Eat random Fish or SeaWeed
     */

    public final void randomEating() {

        final Random random = new Random();

        final Set<IFish> checkedFish = new HashSet<>();

        final int fishCount = this.quarium.getFish().size();

        final int selectedFishNumber = random.nextInt(fishCount);

        IFish fish = null;

        if (this.quarium.getFish().size() <= 0) {

            return;
        }

        while (true) {


            if (fish.getFishType().isCarnivorous()) {

                if (this.quarium.getFish().size() >= 2) {

                }

            } else {

                if (this.quarium.getSeaWeeds().size() >= 1) {

                }

            }

        }


    }

    public final void life() {

    }

    private final IFish randomFish() {

        final int selectedFishNumber = new Random().nextInt(this.quarium.getFish().size());

        int i = 0;

        IFish finaleFish = null;

        for (IFish fish : this.quarium.getFish()) {

            if (i == selectedFishNumber) {

                finaleFish = fish;

                break;

            }

            i++;


        }

        return finaleFish;

    }


}
